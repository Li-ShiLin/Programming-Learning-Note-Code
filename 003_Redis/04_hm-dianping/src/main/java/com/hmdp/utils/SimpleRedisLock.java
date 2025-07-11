package com.hmdp.utils;

import cn.hutool.core.lang.UUID;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;


/**
 * @author 22418
 * @version 1.0
 * @description: TODO
 * @date 2023/1/14 16:57
 */
public class SimpleRedisLock implements ILock {


    private String name;

    private StringRedisTemplate stringRedisTemplate;

    public SimpleRedisLock(String name, StringRedisTemplate stringRedisTemplate) {
        this.name = name;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    //    private static final String KEY_PREFIX = "lock:";
    private static final String KEY_PREFIX = "lock:";
    private static final String ID_PREFIX = UUID.randomUUID().toString(true) + "-";

    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;
    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("unlock.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }


/*

    // 版本一
    @Override
    public boolean tryLock(long timeoutSec) {
        // 获取线程标识
        long threadId = Thread.currentThread().getId();

        // 获取锁
        Boolean success = stringRedisTemplate.opsForValue()
                .setIfAbsent(KEY_PREFIX + name, threadId + "", timeoutSec, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(success);
    }

    @Override
    public void unlock() {
        stringRedisTemplate.delete(KEY_PREFIX + name);
    }
*/

    /*

        // 版本二: 改造 tryLock() 和  unlock() ，解决 版本一 中存在的Redis分布式锁误删问题
        @Override
        public boolean tryLock(long timeoutSec) {
            // 获取线程标示
            String threadId = ID_PREFIX + Thread.currentThread().getId();
            // 获取锁
            Boolean success = stringRedisTemplate.opsForValue()
                    .setIfAbsent(KEY_PREFIX + name, threadId, timeoutSec, TimeUnit.SECONDS);
            return Boolean.TRUE.equals(success);
        }

        @Override
        public void unlock() {
            // 获取线程标示
            String threadId = ID_PREFIX + Thread.currentThread().getId();
            // 获取锁中的标示
            String id = stringRedisTemplate.opsForValue().get(KEY_PREFIX + name);
            // 判断标示是否一致
            if(threadId.equals(id)) {
                // 释放锁
                stringRedisTemplate.delete(KEY_PREFIX + name);
            }
        }
    */


    // 版本三: 改造unlock() -> 调用Lua脚本改造分布式锁
    @Override
    public boolean tryLock(long timeoutSec) {
        // 获取线程标示
        String threadId = ID_PREFIX + Thread.currentThread().getId();
        // 获取锁
        Boolean success = stringRedisTemplate.opsForValue()
                .setIfAbsent(KEY_PREFIX + name, threadId, timeoutSec, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(success);
    }

    @Override
    public void unlock() {
        // 调用lua脚本
        stringRedisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(KEY_PREFIX + name),
                ID_PREFIX + Thread.currentThread().getId());
    }


}
