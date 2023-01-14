package com.hmdp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.Result;
import com.hmdp.entity.SeckillVoucher;
import com.hmdp.entity.VoucherOrder;
import com.hmdp.mapper.VoucherOrderMapper;
import com.hmdp.service.ISeckillVoucherService;
import com.hmdp.service.IVoucherOrderService;
import com.hmdp.utils.RedisIdWorker;
import com.hmdp.utils.UserHolder;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService {

    @Resource
    private ISeckillVoucherService iSeckillVoucherService;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Override
    public Result seckillVoucher(Long voucherId) {
        // 1.查询优惠券
        SeckillVoucher voucher = iSeckillVoucherService.getById(voucherId);
        // 2.判断秒杀是否开始
        if (voucher.getBeginTime().isAfter(LocalDateTime.now())) {
            // 尚未开始
            return Result.fail("秒杀尚未开始！");
        }
        // 3.判断秒杀是否已经结束
        if (voucher.getEndTime().isBefore(LocalDateTime.now())) {
            // 尚未开始
            return Result.fail("秒杀已经结束！");
        }

        // 4.判断库存是否充足
        if (voucher.getStock() < 1) {
            //库存不足
            return Result.fail("库存不足");
        }

/*        Long userId = UserHolder.getUser().getId();
        synchronized (userId.toString().intern()) {
            // 8.返回订单id
            return this.createVoucherOrder(voucherId);
        }*/

        // 8.返回订单id
        Long userId = UserHolder.getUser().getId();
        synchronized (userId.toString().intern()) {
            // 获取当前对象的代理对象（事务相关的代理对象）
            IVoucherOrderService proxy = (IVoucherOrderService) AopContext.currentProxy();
            // 利用代理对象去调用createVoucherOrder()函数 （需要在IVoucherOrderService中添加createVoucherOrder()方法）
            return proxy.createVoucherOrder(voucherId);
            // 注意：还需要在pom.xml中aspectj依赖
        }
    }


    @Transactional
    @Override
    public Result createVoucherOrder(Long voucherId) {

        // 5.一人一单
        // 5.1 查询订单
        Long userId = UserHolder.getUser().getId();
        Integer count = query().eq("user_id", userId).eq("voucher_id", voucherId).count();
        if (count > 0) {
            // 用户已经购买过了
            return Result.fail("已经抢购成功，不可重复抢购！");
        }

/*
        // 6.扣减库存
        boolean isSuccess = iSeckillVoucherService.update()
                .setSql("stock = stock -1")
                .eq("voucher_id", voucherId).update();*/

/*        // 6.扣减库存(解决超卖问题)
        boolean isSuccess = iSeckillVoucherService.update()
                .setSql("stock = stock -1")  // set stock = stock -1
                .eq("voucher_id", voucherId)
                .eq("stock",voucher.getStock())  // where id = ? and stock = ?
                .update();*/

        // 6.扣减库存(解决超卖问题) 进一步优化——>提高抢购成功率
        boolean isSuccess = iSeckillVoucherService.update()
                .setSql("stock = stock -1")  // set stock = stock -1
                .eq("voucher_id", voucherId)
                .gt("stock", 0)  // where id = ? and stock > 0
                .update();

        if (!isSuccess) {
            // 扣减失败
            return Result.fail("库存扣减失败！");
        }

        // 7.创建订单(订单信息：订单id、用户id、代金券id)
        VoucherOrder voucherOrder = new VoucherOrder();

        // 订单id
        long orderId = redisIdWorker.nextId("order");
        voucherOrder.setId(orderId);

        // 用户id
        voucherOrder.setUserId(userId);

        // 代金券id
        voucherOrder.setVoucherId(voucherId);
        save(voucherOrder);
        // 7.返回订单id
        return Result.ok(orderId);

    }

}
