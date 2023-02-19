package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.mapper.UserMapper;
import com.hmdp.service.IUserService;
import com.hmdp.utils.RegexUtils;
import com.hmdp.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;
import static com.hmdp.utils.SystemConstants.USER_NICK_NAME_PREFIX;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送验证码
     *
     * @param phone
     * @param session
     * @return
     */
    @Override
    public Result sendCode(String phone, HttpSession session) {

        // 1.校验手机号(利用正则表达式校验手机号格式)，如果不符合就返回错误信息，如果符合就生成验证码
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 不符合则返回错误信息
            return Result.fail("手机号格式错误");
        }   // 符合:

/*没用redis改造之前：
        2.生成验证码并保存验证码到session
        String code = RandomUtil.randomNumbers(6);
        session.setAttribute("code", code);*/

        //2.保存验证码到reids   set key value ex 2
        String code = RandomUtil.randomNumbers(6);
        //stringRedisTemplate.opsForValue().set("login:code" + phone,code,2, TimeUnit.MINUTES);

        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);
        // 3.发送验证码(此处并不实现，仅模拟。一般公司中会有一个发送验证码的服务，只需调用即可)
        log.info("发送短信验证码成功，验证码：{}", code);

        // 4.整个流程成功则返回ok
        return Result.ok();
    }

    @Override
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        // 1.校验手机号（每个请求都要校验）
        String phone = loginForm.getPhone();
        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.fail("手机号格式错误");
        }
/*没有redis改造前：
        // 2.校验验证码，不一致就报错，一致则根据手机号查询用户
        Object cashecode = session.getAttribute("code");
        String code = loginForm.getCode();
        if (cashecode == null || (!cashecode.toString().equals(code))) {
            return Result.fail("验证码错误");
        }*/
//利用redis改造后：
        //2.从redis获取验证码并校验
        String cashecode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
        String code = loginForm.getCode();
        if (cashecode == null || (!cashecode.equals(code))) {
            return Result.fail("验证码错误");
        }

        /* 3.根据手机号查询用户(select * from tb_user where phone = ?) , 用户不存在就创建用户并保存
               UserServiceImpl extends ServiceImpl<UserMapper, User>
               UserServiceImpl 继承ServiceImpl<UserMapper, User>后 自带 query()方法 */
        User user = query().eq("phone", phone).one();

        if (user == null) {
            // 如果用户不存在，创建用户并保存
            user = createUserWithPhone(phone);
        }
/*没有redis改造前：
        // 4.保存用户信息到session中（用户存不存在都要做）
        *//*利用BeanUtil拷贝user的属性到UserDto中
               UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);*//*
        session.setAttribute("user", BeanUtil.copyProperties(user, UserDTO.class));*/
        /*session.setAttribute("user",user);*/
        // 直接返回登录成功而不用返回用户凭证，因为session自带sessionID，起到凭证的作用
//利用redis代替session实现登录：
        // 4.保存用户信息到redis中（用户存不存在都要做）
        // 4.1 随机生成token,作为登录令牌
        String token = UUID.randomUUID().toString(true);
        // 4.2 将User对象转为Hash(Java里的HashMap)存储
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        // Map<String, Object> userMap = BeanUtil.beanToMap(userDTO); 此行还需改为如下代码，否则报错
        // 在存入redis之前把userMap中的value都转换成String类型
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        // 4.3 存储token到redis,并设置有效期
        String tokenKey = LOGIN_USER_KEY + token;
        stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);
        stringRedisTemplate.expire(tokenKey, LOGIN_USER_TTL, TimeUnit.MINUTES);
        // 返回token
        return Result.ok(token);
    }

    /**
     * 根据手机号创建用户
     *
     * @param phone
     * @return
     */
    private User createUserWithPhone(String phone) {
        // 1.创建用户
        User user = new User();
        user.setPhone(phone);
        user.setNickName(USER_NICK_NAME_PREFIX + RandomUtil.randomString(8));
        // 2.利用MyBatis-plus提供的save()方法 保存用户
        save(user);
        return user;
    }

    @Override
    public Result sign() {
        // 1.获取当前登录用户
        Long userId = UserHolder.getUser().getId();
        // 2.获取日期
        LocalDateTime now = LocalDateTime.now();
        // 3.拼接key
        String keySuffix = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = USER_SIGN_KEY + userId + keySuffix;
        // 4.获取今天是本月的第几天
        int dayOfMonth = now.getDayOfMonth();
        // 5.写入Redis SETBIT key offset 1
        stringRedisTemplate.opsForValue().setBit(key, dayOfMonth - 1, true);
        return Result.ok();
    }

    @Override
    public Result signCount() {
        // 1.获取当前登录用户
        Long userId = UserHolder.getUser().getId();
        // 2.获取日期
        LocalDateTime now = LocalDateTime.now();
        // 3.拼接key
        String keySuffix = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = USER_SIGN_KEY + userId + keySuffix;
        // 4.获取今天是本月的第几天
        int dayOfMonth = now.getDayOfMonth();
        // 5.获取本月截止今天为止的所有的签到记录，返回的是一个十进制的数字 BITFIELD sign:5:202203 GET u14 0
        List<Long> result = stringRedisTemplate.opsForValue()
                .bitField(
                        key,
                        BitFieldSubCommands.create()
                                .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth)).valueAt(0)
                );
        if (result == null || result.isEmpty()) {
            // 没有任何签到结果
            return Result.ok(0);
        }
        Long num = result.get(0);
        if (num == null || num == 0) {
            return Result.ok(0);
        }
        // 6.循环遍历
        int count = 0;
        while (true) {
            // 6.1.让这个数字与1做与运算，得到数字的最后一个bit位  // 判断这个bit位是否为0
            if ((num & 1) == 0) {
                // 如果为0，说明未签到，结束
                break;
            } else {
                // 如果不为0，说明已签到，计数器+1
                count++;
            }
            // 把数字右移一位，抛弃最后一个bit位，继续下一个bit位
            num >>>= 1;
        }
        return Result.ok(count);
    }
}
