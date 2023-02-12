## Redis实战篇�?��?�分布式�?-redission

## 5、分布式�?-redission

官网地址: https://redisson.org

GitHub地址: https://github.com/redisson/redisson

### 5.1 分布式锁-redission功能介绍

基于setnx实现的分布式锁存在下面的问题�?

**重入问题**：重入问题是�? 获得锁的线程可以再次进入到相同的锁的代码块中，可重入锁的意义在于防止死锁，比如HashTable这样的代码中，他的方法都是使用synchronized修饰的，假如他在�?个方法内，调用另�?个方法，那么此时如果是不可重入的，不就死锁了吗？�?以可重入锁他的主要意义是防止死锁，我们的synchronized和Lock锁都是可重入�?

**不可重试**：是指目前的分布式只能尝试一次，我们认为合理的情况是：当线程在获得锁失败后，他应该能再次尝试获得�?

**超时释放**�? 我们在加锁时增加了过期时间，这样的我们可以防止死锁，但是如果卡顿的时间超长，虽然我们采用了lua表达式防止删锁的时�?�，误删别人的锁，但是毕竟没有锁住，有安全隐�?

**主从�?致�?�：** 如果Redis提供了主从集群，当我们向集群写数据时，主机需要异步的将数据同步给从机，�?�万�?在同步过去之前，主机宕机了，就会出现死锁问题

![1653546070602](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301141338605.png)



那么�?么是Redission�?

Redisson是一个在Redis的基�?上实现的Java驻内存数据网格（In-Memory Data Grid）�?�它不仅提供了一系列的分布式的Java常用对象，还提供了许多分布式服务，其中就包含了各种分布式锁的实现�?

Redission提供了分布式锁的多种多样的功�?

![1653546736063](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301141337360.png)



### 5.2 分布式锁-Redission快�?�入�?

引入依赖�?

```java
<dependency>
	<groupId>org.redisson</groupId>
	<artifactId>redisson</artifactId>
	<version>3.13.6</version>
</dependency>
```

配置Redisson客户�?(建议通过下方配置来配置Redisson，�?�不是在yaml中配置，yaml中配置会和spring配置混淆)�?

```java
package com.hmdp.config;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(){
        // 配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.54.134:6379")
                .setPassword("123456");
        // 创建RedissonClient对象
        return Redisson.create(config);
    }
}
```

如何使用Redission的分布式锁�?��?�直接注�? RedissonClient即可

```java
package com.hmdp;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
@SpringBootTest
public class TestRedissionClient {

    @Resource
    private RedissonClient redissonClient;

    @Test
    void testRedisson() throws Exception {
        // 获取�?(可重�?)，指定锁的名�?
        RLock lock = redissonClient.getLock("anyLock");
        // 尝试获取锁，参数分别是：获取锁的�?大等待时�?(期间会重�?)，锁自动释放时间，时间单�?
        boolean isLock = lock.tryLock(1, 10, TimeUnit.SECONDS);
        // 判断获取锁成�?
        if (isLock) {
            try {
                System.out.println("执行业务");
            } finally {
                //释放�?
                lock.unlock();
            }

        }
    }
}
```

�? VoucherOrderServiceImpl中使用RedissonClient替换原来的锁�?

```java
// 注入RedissonClient
@Resource
private RedissonClient redissonClient;

@Override
public Result seckillVoucher(Long voucherId) {
        // 1.查询优惠�?
        SeckillVoucher voucher = seckillVoucherService.getById(voucherId);
        // 2.判断秒杀是否�?�?
        if (voucher.getBeginTime().isAfter(LocalDateTime.now())) {
            // 尚未�?�?
            return Result.fail("秒杀尚未�?始！");
        }
        // 3.判断秒杀是否已经结束
        if (voucher.getEndTime().isBefore(LocalDateTime.now())) {
            // 尚未�?�?
            return Result.fail("秒杀已经结束�?");
        }
        // 4.判断库存是否充足
        if (voucher.getStock() < 1) {
            // 库存不足
            return Result.fail("库存不足�?");
        }
        Long userId = UserHolder.getUser().getId();
        //创建锁对�? 这个代码不用了，因为我们现在要使用分布式�?
        //SimpleRedisLock lock = new SimpleRedisLock("order:" + userId, stringRedisTemplate);
        // 使用redissonClient
        RLock lock = redissonClient.getLock("lock:order:" + userId);
        //获取锁对�?
        boolean isLock = lock.tryLock();
       
		//加锁失败
        if (!isLock) {
            return Result.fail("不允许重复下�?");
        }
        try {
            //获取代理对象(事务)
            IVoucherOrderService proxy = (IVoucherOrderService) AopContext.currentProxy();
            return proxy.createVoucherOrder(voucherId);
        } finally {
            //释放�?
            lock.unlock();
        }
 }
```

### 5.3 分布式锁-redission可重入锁原理

在Lock锁中，他是�?�助于底层的�?个voaltile的一个state变量来记录重入的状�?�的，比如当前没有人持有这把锁，那么state=0，假如有人持有这把锁，那么state=1，如果持有这把锁的人再次持有这把锁，那么state就会+1 ，如果是对于synchronized而言，他在c语言代码中会有一个count，原理和state类似，也是重入一次就加一，释放一次就-1 ，直到减少成0 时，表示当前这把锁没有被人持有�?? 

在redission中，我们的也支持支持可重入锁

**Redisson可重入锁原理图：**

![1653548087334](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301141336252.png)

采用hash结构用来存储锁，其中大key表示表示这把锁是否存在，用小key表示当前这把锁被哪个线程持有,value用来表示重入的次�?

在分布式锁中，他**采用hash结构用来存储锁，其中大key表示表示这把锁是否存在，用小key表示当前这把锁被哪个线程持有**，所以接下来我们�?起分析一下当前的这个lua表达�?

这个地方�?共有3个参�?

```
KEYS[1] �? 锁名�?

ARGV[1]�? 锁失效时�?

ARGV[2]�? id + ":" + threadId; 锁的小key
```

exists: 判断数据是否存在  name：是lock是否存在,如果==0，就表示当前这把锁不存在

redis.call('hset', KEYS[1], ARGV[2], 1);此时他就�?始往redis里边去写数据 ，写成一个hash结构

```
Lock{

  id + ":" + threadId : 1

}
```

如果当前这把锁存在，则第�?个条件不满足，再判断

redis.call('hexists', KEYS[1], ARGV[2]) == 1

此时�?要�?�过大key+小key判断当前这把锁是否是属于自己的，如果是自己的，则进行

redis.call('hincrby', KEYS[1], ARGV[2], 1)

将当前这个锁的value进行+1 ，redis.call('pexpire', KEYS[1], ARGV[1]); 然后再对其设置过期时间，如果以上两个条件都不满足，则表示当前这把锁抢锁失败，�?后返回pttl，即为当前这把锁的失效时�?

**获取锁的lua脚本�?**

![image-20230115212353760](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301152123663.png)

**释放锁的lua脚本�?**

![image-20230115215344486](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301152153720.png)

**Redisson可重入锁测试�?**

```java
package com.hmdp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 22418
 * @version 1.0
 * @description: TODO
 * @date 2023/1/15 22:12
 */
@Slf4j
@SpringBootTest
class RedissonTest {

    @Resource
    private RedissonClient redissonClient;

    private RLock lock;

    @BeforeEach
    void setUp() {
        lock = redissonClient.getLock("order");
    }

    @Test
    void method1() throws InterruptedException {
        // 尝试获取�?
        boolean isLock = lock.tryLock(1L, TimeUnit.SECONDS);
        if (!isLock) {
            log.error("获取锁失�? .... 1");
            return;
        }
        try {
            log.info("获取锁成�? .... 1");
            method2();
            log.info("�?始执行业�? ... 1");
        } finally {
            log.warn("准备释放�? .... 1");
            lock.unlock();
        }
    }

    void method2() {
        // 尝试获取�?
        boolean isLock = lock.tryLock();
        if (!isLock) {
            log.error("获取锁失�? .... 2");
            return;
        }
        try {
            log.info("获取锁成�? .... 2");
            log.info("�?始执行业�? ... 2");
        } finally {
            log.warn("准备释放�? .... 2");
            lock.unlock();
        }
    }
    
                        /*    日志打印�?

                                    获取锁成�? .... 1
                                    获取锁成�? .... 2
                                    �?始执行业�? ... 2
                                    准备释放�? .... 2
                                    �?始执行业�? ... 1
                                    准备释放�? .... 1
                            */
}

```

在上面的测试中点进去查看lock.tryLock()的源码， 会发现他去判断当前这个方法的返回值是否为null，如果是null，则对应则前两个if对应的条件，�?出抢锁�?�辑，如果返回的不是null，即走了第三个分支，在源码处会进行while(true)的自旋抢锁�?�且源码中含有如下一段lua脚本�?

```lua
"if (redis.call('exists', KEYS[1]) == 0) then " +
                  "redis.call('hset', KEYS[1], ARGV[2], 1); " +
                  "redis.call('pexpire', KEYS[1], ARGV[1]); " +
                  "return nil; " +
              "end; " +
              "if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then " +
                  "redis.call('hincrby', KEYS[1], ARGV[2], 1); " +
                  "redis.call('pexpire', KEYS[1], ARGV[1]); " +
                  "return nil; " +
              "end; " +
              "return redis.call('pttl', KEYS[1]);"
```

### 5.4 分布式锁-redission锁重试和WatchDog机制

![image-20230117215232165](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301172152355.png)



**Redisson分布式锁原理:**

- 可重�?:利用hash结构记录线程id和重入次�?
- 可重�?:利用信号量和PubSub功能实现等待、唤醒，获取锁失败的重试机制
- 超时续约:利用watchDog，每隔一段时�?( releaseTime/ 3)，重置超时时�?



**说明**：由于课程中已经说明了有关tryLock的源码解析以及其看门狗原理，�?以笔者在这里给大家分析lock()方法的源码解析，希望大家在学习过程中，能够掌握更多的知识

抢锁过程中，获得当前线程，�?�过tryAcquire进行抢锁，该抢锁逻辑和之前�?�辑相同

1、先判断当前这把锁是否存在，如果不存在，插入�?把锁，返回null

2、判断当前这把锁是否是属于当前线程，如果是，则返回null

�?以如果返回是null，则代表�?当前这哥们已经抢锁完毕，或�?�可重入完毕，但是如果以上两个条件都不满足，则进入到第三个条件，返回的是锁的失效时间，同学们可以自行�?下翻�?点点，你能发现有个while( true) 再次进行tryAcquire进行抢锁

```java
long threadId = Thread.currentThread().getId();
Long ttl = tryAcquire(-1, leaseTime, unit, threadId);
// lock acquired
if (ttl == null) {
    return;
}
```

接下来会有一个条件分支，因为lock方法有重载方法，�?个是带参数，�?个是不带参数，如果带带参数传入的值是-1，如果传入参数，则leaseTime是他本身，所以如果传入了参数，此时leaseTime != -1 则会进去抢锁，抢锁的逻辑就是之前说的那三个�?�辑

```java
if (leaseTime != -1) {
    return tryLockInnerAsync(waitTime, leaseTime, unit, threadId, RedisCommands.EVAL_LONG);
}
```

如果是没有传入时间，则此时也会进行抢锁， 而且抢锁时间是默认看门狗时间 commandExecutor.getConnectionManager().getCfg().getLockWatchdogTimeout()

ttlRemainingFuture.onComplete((ttlRemaining, e) 这句话相当于对以上抢锁进行了监听，也就是说当上边抢锁完毕后，此方法会被调用，具体调用的�?�辑就是去后台开启一个线程，进行续约逻辑，也就是看门狗线�?

```java
RFuture<Long> ttlRemainingFuture = tryLockInnerAsync(waitTime,
                                        commandExecutor.getConnectionManager().getCfg().getLockWatchdogTimeout(),
                                        TimeUnit.MILLISECONDS, threadId, RedisCommands.EVAL_LONG);
ttlRemainingFuture.onComplete((ttlRemaining, e) -> {
    if (e != null) {
        return;
    }

    // lock acquired
    if (ttlRemaining == null) {
        scheduleExpirationRenewal(threadId);
    }
});
return ttlRemainingFuture;
```

此�?�辑就是续约逻辑，注意看commandExecutor.getConnectionManager().newTimeout（） 此方�?

Method(  **new** TimerTask() {},参数2 ，参�?3  )

指的是：通过参数2，参�?3 去描述什么时候去做参�?1的事情，现在的情况是�?10s之后去做参数�?的事�?

因为锁的失效时间�?30s，当10s之后，此时这个timeTask 就触发了，他就去进行续约，把当前这把锁续约成30s，如果操作成功，那么此时就会递归调用自己，再重新设置�?个timeTask()，于是再�?10s后又再设置一个timerTask，完成不停的续约

那么大家可以想一想，假设我们的线程出现了宕机他还会续约吗？当然不会，因为没有人再去调用renewExpiration这个方法，所以等到时间之后自然就释放了�??

```java
private void renewExpiration() {
    ExpirationEntry ee = EXPIRATION_RENEWAL_MAP.get(getEntryName());
    if (ee == null) {
        return;
    }
    
    Timeout task = commandExecutor.getConnectionManager().newTimeout(new TimerTask() {
        @Override
        public void run(Timeout timeout) throws Exception {
            ExpirationEntry ent = EXPIRATION_RENEWAL_MAP.get(getEntryName());
            if (ent == null) {
                return;
            }
            Long threadId = ent.getFirstThreadId();
            if (threadId == null) {
                return;
            }
            
            RFuture<Boolean> future = renewExpirationAsync(threadId);
            future.onComplete((res, e) -> {
                if (e != null) {
                    log.error("Can't update lock " + getName() + " expiration", e);
                    return;
                }
                
                if (res) {
                    // reschedule itself
                    renewExpiration();
                }
            });
        }
    }, internalLockLeaseTime / 3, TimeUnit.MILLISECONDS);
    
    ee.setTimeout(task);
}
```

### 5.5 分布式锁-redission锁的MutiLock原理

为了提高redis的可用�?�，我们会搭建集群或者主从，现在以主从为�?

此时我们去写命令，写在主机上�? 主机会将数据同步给从机，但是假设在主机还没有来得及把数据写入到从机去的时候，此时主机宕机，哨兵会发现主机宕机，并且�?�举�?个slave变成master，�?�此时新的master中实际上并没有锁信息，此时锁信息就已经丢掉了

 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301172200894.png" >  <b>正常</b> </td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301141335401.png" > <b>master宕机</b></td>
    </tr>
    </table>



为了解决这个问题，redission提出来了MutiLock锁，使用这把锁咱们就不使用主从了，每个节点的地位都是�?样的�? 这把锁加锁的逻辑�?要写入到每一个主丛节点上，只有所有的服务器都写入成功，此时才是加锁成功，假设现在某个节点挂了，那么他去获得锁的时候，只要有一个节点拿不到，都不能算是加锁成功，就保证了加锁的可靠性�??

![1653554055048](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301141334390.png)

那么MutiLock 加锁原理是什么呢？笔者画了一幅图来说�?

当我们去设置了多个锁时，redission会将多个锁添加到�?个集合中，然后用while循环去不停去尝试拿锁，但是会有一个�?�共的加锁时间，这个时间是用�?要加锁的个数 * 1500ms ，假设有3个锁，那么时间就�?4500ms，假设在�?4500ms内，�?有的锁都加锁成功�? 那么此时才算是加锁成功，如果�?4500ms有线程加锁失败，则会再次去进行重�?.

![1653553093967](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301141333132.png)



**MutiLock测试**

```java
package com.hmdp.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 22418
 * @version 1.0
 * @description: TODO
 * @date 2023/1/15 16:01
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(){
        // 配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.54.134:6379")
                .setPassword("123456");
        // 创建RedissonClient对象
        return Redisson.create(config);
    }

    @Bean
    public RedissonClient redissonClient2(){
        // 配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.54.134:6381")
                .setPassword("123456");
        // 创建RedissonClient对象
        return Redisson.create(config);
    }

    @Bean
    public RedissonClient redissonClient3(){
        // 配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.54.134:6382")
                .setPassword("123456");
        // 创建RedissonClient对象
        return Redisson.create(config);
    }
}

```



```java
package com.hmdp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
class RedissionMutiLockTest {

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RedissonClient redissonClient2;

    @Resource
    private RedissonClient redissonClient3;

    private RLock lock;

    @BeforeEach
    void setUp() {
        // 三个独立节点对应三个独立的锁
        RLock lock1 = redissonClient.getLock("order");
        RLock lock2 = redissonClient2.getLock("order");
        RLock lock3 = redissonClient3.getLock("order");

        // 创建联锁 multiLock
        lock = redissonClient.getMultiLock(lock1,lock2,lock3);
    }

    @Test
    void method1() throws InterruptedException {
        // 尝试获取�?
        boolean isLock = lock.tryLock(1L, TimeUnit.SECONDS);
        if (!isLock) {
            log.error("获取锁失�? .... 1");
            return;
        }
        try {
            log.info("获取锁成�? .... 1");
            method2();
            log.info("�?始执行业�? ... 1");
        } finally {
            log.warn("准备释放�? .... 1");
            lock.unlock();
        }
    }

    void method2() {
        // 尝试获取�?
        boolean isLock = lock.tryLock();
        if (!isLock) {
            log.error("获取锁失�? .... 2");
            return;
        }
        try {
            log.info("获取锁成�? .... 2");
            log.info("�?始执行业�? ... 2");
        } finally {
            log.warn("准备释放�? .... 2");
            lock.unlock();
        }
    }
/*
    日志打印�?

            获取锁成�? .... 1
            获取锁成�? .... 2
            �?始执行业�? ... 2
            准备释放�? .... 2
            �?始执行业�? ... 1
            准备释放�? .... 1
    */

}
```



### 5.6 分布式锁总结

**1)不可重入Redis分布式锁:**

- 原理:利用setnx的互斥�??;利用ex避免死锁;释放锁时判断线程标示
- 缺陷:不可重入、无法重试�?�锁超时失效

**2)可重入的Redis分布式锁:**

- 原理:利用hash结构，记录线程标示和重入次数;利用watchDog延续锁时�?;利用信号量控制锁重试等待
- 缺陷:redis宕机引起锁失效问�?

**3 ) Redisson的multiLock:**

- 原理:多个独立的Redis节点，必须在�?有节点都获取重入锁，才算获取锁成�?
- 缺陷:运维成本高�?�实现复�?

## 6、秒�?优化

### 6.1 秒杀优化-异步秒杀思路

我们来回顾一下下单流�?

当用户发起请求，此时会请求nginx，nginx会访问到tomcat，�?�tomcat中的程序，会进行串行操作，分成如下几个步�?

1、查询优惠卷

2、判断秒�?库存是否足够

3、查询订�?

4、校验是否是�?人一�?

5、扣减库�?

6、创建订�?

在这六步操作中，又有很多操作是要去操作数据库的，而且还是�?个线程串行执行， 这样就会导致我们的程序执行的很慢，所以我们需要异步程序执行，那么如何加�?�呢�?

在这里笔者想给大家分享一下课程内没有的�?�路，看看有没有小伙伴这么想，比如，我们可以不可以使用异步编排来做，或�?�说我开启N多线程，N多个线程，一个线程执行查询优惠卷，一个执行判断扣减库存，�?个去创建订单等等，然后再统一做返回，这种做法和课程中有哪种好呢？答案是课程中的好，因为如果你采用我刚说的方式，如果访问的人很多，那么线程池中的线程可能一下子就被消�?�完了，而且你使用上述方案，�?大的特点在于，你觉得时效性会非常重要，但是你想想是吗？并不是，比如我只要确定他能做这件事，然后我后边慢慢做就可以了，我并不需要他�?口气做完这件事，�?以我们应当采用的是课程中，类似消息队列的方式来完成我们的�?求，而不是使用线程池或�?�是异步编排的方式来完成这个�?�?

![1653560986599](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301141331332.png)





优化方案：我们将耗时比较短的逻辑判断放入到redis中，比如是否库存足够，比如是否一人一单，这样的操作，只要这种逻辑可以完成，就意味�?我们是一定可以下单完成的，我们只�?要进行快速的逻辑判断，根本就不用等下单�?�辑走完，我们直接给用户返回成功�? 再在后台�?�?个线程，后台线程慢慢的去执行queue里边的消息，这样程序不就超级快了吗？而且也不用担心线程池消�?�殆尽的问题，因为这里我们的程序中并没有手动使用任何线程池，当然这里边有两个难点

第一个难点是我们怎么在redis中去快�?�校验一人一单，还有库存判断

第二个难点是由于我们校验和tomct下单是两个线程，那么我们如何知道到底哪个单他�?后是否成功，或�?�是下单完成，为了完成这件事我们在redis操作完之后，我们会将�?些信息返回给前端，同时也会把这些信息丢到异步queue中去，后续操作中，可以�?�过这个id来查询我们tomcat中的下单逻辑是否完成�?

![1653561657295](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301141329902.png)



我们现在来看看整体�?�路：当用户下单之后，判断库存是否充足只�?要导redis中去根据key找对应的value是否大于0即可，如果不充足，则直接结束，如果充足，继续在redis中判断用户是否可以下单，如果set集合中没有这条数据，说明他可以下单，如果set集合中没有这条记录，则将userId和优惠卷存入到redis中，并且返回0，整个过程需要保证是原子性的，我们可以使用lua来操�?

当以上判断�?�辑走完之后，我们可以判断当前redis中返回的结果是否�?0 ，如果是0，则表示可以下单，则将之前说的信息存入到到queue中去，然后返回，然后再来个线程异步的下单，前端可以�?�过返回的订单id来判断是否下单成功�??

![1653562234886](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301141329753.png)



### 6.2 秒杀优化-Redis完成秒杀资格判断

�?求：

* 新增秒杀优惠券的同时，将优惠券信息保存到Redis�?

* 基于Lua脚本，判断秒�?库存、一人一单，决定用户是否抢购成功

* 如果抢购成功，将优惠券id和用户id封装后存入阻塞队�?

* �?启线程任务，不断从阻塞队列中获取信息，实现异步下单功�?

  

![1656080546603](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301141328502.png)



VoucherServiceImpl

```java
@Override
@Transactional
public void addSeckillVoucher(Voucher voucher) {
    // 保存优惠�?
    save(voucher);
    // 保存秒杀信息
    SeckillVoucher seckillVoucher = new SeckillVoucher();
    seckillVoucher.setVoucherId(voucher.getId());
    seckillVoucher.setStock(voucher.getStock());
    seckillVoucher.setBeginTime(voucher.getBeginTime());
    seckillVoucher.setEndTime(voucher.getEndTime());
    seckillVoucherService.save(seckillVoucher);
    // 保存秒杀库存到Redis�?
    //SECKILL_STOCK_KEY 这个变量定义在RedisConstans�?
    //private static final String SECKILL_STOCK_KEY ="seckill:stock:"
    stringRedisTemplate.opsForValue().set(SECKILL_STOCK_KEY + voucher.getId(), voucher.getStock().toString());
}
```

seckill_01.lua完整lua表达�?

```lua
-- 1.参数列表
-- 1.1.优惠券id
local voucherId = ARGV[1]
-- 1.2.用户id
local userId = ARGV[2]
-- 1.3.订单id
local orderId = ARGV[3]

-- 2.数据key
-- 2.1.库存key   �?..拼接字符�?
local stockKey = 'seckill:stock:' .. voucherId
-- 2.2.订单key
local orderKey = 'seckill:order:' .. voucherId

-- 3.脚本业务
-- 3.1.判断库存是否充足 get stockKey     tonumber()函数将字符串转为数字
if(tonumber(redis.call('get', stockKey)) <= 0) then
    -- 3.2.库存不足，返�?1
    return 1
end
-- 3.2.判断用户是否下单 SISMEMBER orderKey userId
if(redis.call('sismember', orderKey, userId) == 1) then
    -- 3.3.存在，说明是重复下单，返�?2
    return 2
end
-- 3.4.扣库�? incrby stockKey -1
redis.call('incrby', stockKey, -1)
-- 3.5.下单（保存用户）sadd orderKey userId
redis.call('sadd', orderKey, userId)
return 0
```

当以上lua表达式执行完毕后，剩下的就是根据步骤3,4来执行我们接下来的任务了

**VoucherOrderServiceImpl**

```java
package com.hmdp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.Result;
import com.hmdp.entity.VoucherOrder;
import com.hmdp.mapper.VoucherOrderMapper;
import com.hmdp.service.ISeckillVoucherService;
import com.hmdp.service.IVoucherOrderService;
import com.hmdp.utils.RedisIdWorker;
import com.hmdp.utils.UserHolder;
import org.redisson.api.RedissonClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * <p>
 * 服务实现�?
 * </p>
 */
@Service
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService {

    @Resource
    private ISeckillVoucherService iSeckillVoucherService;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redissonClient;


    private static final DefaultRedisScript<Long> SECKILL_SCRIPT;

    static {
        SECKILL_SCRIPT = new DefaultRedisScript<>();
        SECKILL_SCRIPT.setLocation(new ClassPathResource("seckill_01.lua"));
        SECKILL_SCRIPT.setResultType(Long.class);
    }


    // 方式二：用lua脚本判断秒杀资格   (方式�?见下面注�?)
    @Override
    public Result seckillVoucher(Long voucherId) {
        // 获取用户
        Long userId = UserHolder.getUser().getId();

        // 1.执行lua脚本
        Long result = stringRedisTemplate.execute(
                SECKILL_SCRIPT,
                Collections.emptyList(),
                voucherId.toString(),
                userId.toString()
        );

        // 2.判断结果是否�?0
        int r = result.intValue();
        if (r != 0) {
            // 2.1 不为0，代表没有购买资�?
            return Result.fail(r == 1 ? "库存不足" : "不能重复下单");
        }
        // 2.2 �?0，有购买资格，把下单信息保存到阻塞队�?
        long orderId = redisIdWorker.nextId("order");


        // 3.返回订单id
        return Result.ok(orderId);
    }


    @Transactional
    @Override
    public Result createVoucherOrder(Long voucherId) {

        // 5.�?人一�?
        // 5.1 查询订单
        Long userId = UserHolder.getUser().getId();
        Integer count = query().eq("user_id", userId).eq("voucher_id", voucherId).count();
        if (count > 0) {
            // 用户已经购买过了
            return Result.fail("已经抢购成功，不可重复抢购！");
        }


        // 6.扣减库存(解决超卖问题) 进一步优化�?��??>提高抢购成功�?
        boolean isSuccess = iSeckillVoucherService.update()
                .setSql("stock = stock -1")  // set stock = stock -1
                .eq("voucher_id", voucherId)
                .gt("stock", 0)  // where id = ? and stock > 0
                .update();

        if (!isSuccess) {
            // 扣减失败
            return Result.fail("库存扣减失败�?");
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


    // 方式�?：用Java代码判断秒杀资格�?
    // 加锁解决并发问题
//    @Override
//    public Result seckillVoucher(Long voucherId) {
//        // 1.查询优惠�?
//        SeckillVoucher voucher = iSeckillVoucherService.getById(voucherId);
//        // 2.判断秒杀是否�?�?
//        if (voucher.getBeginTime().isAfter(LocalDateTime.now())) {
//            // 尚未�?�?
//            return Result.fail("秒杀尚未�?始！");
//        }
//        // 3.判断秒杀是否已经结束
//        if (voucher.getEndTime().isBefore(LocalDateTime.now())) {
//            // 尚未�?�?
//            return Result.fail("秒杀已经结束�?");
//        }
//
//        // 4.判断库存是否充足
//        if (voucher.getStock() < 1) {
//            //库存不足
//            return Result.fail("库存不足");
//        }
//
//
//        // 版本四：分布式锁redission，解决集群环境下的并发问�?
//        Long userId = UserHolder.getUser().getId();
//        //创建锁对�?(新增代码)
//        //SimpleRedisLock lock = new SimpleRedisLock("order:" + userId, stringRedisTemplate);
//        RLock lock = redissonClient.getLock("lock:order:" + userId);
//
//        //获取锁对�?
//        boolean isLock = lock.tryLock();
//        //加锁失败
//        if (!isLock) {
//            return Result.fail("不允许重复下�?");
//        }
//        try {
//            //获取代理对象(事务)
//            IVoucherOrderService proxy = (IVoucherOrderService) AopContext.currentProxy();
//            return proxy.createVoucherOrder(voucherId);
//        } finally {
//            //释放�?
//            lock.unlock();
//        }
//
//
//    }
}

```

**测试�?**

1.利用postman发�?�请求`http://localhost:8081/voucher/seckill`,json格式的body如下�?

```json
{
    "shopId":1,
    "title":"100元代金券",
    "subTitle":"周一至周五均可用",
    "rules":"全场通用\\n无需预约\\n可无限叠加\\不兑现�?�不找零\\n仅限堂食",
    "payValue":8000,
    "actualValue":10000,
    "type":1,
    "stock":100,
    "beginTime":"2023-01-13T10:09:17",
    "endTime":"2023-01-26T12:09:04"
}
```

响应结果�?(18为订单id)�?

```json
{
    "success": true,
    "data": 18
}
```

2.利用postman发�?�请求`http://localhost:8080/api/voucher-order/seckill/18`,在Header中携带响应`authorization=166699cae51c47bebce623aa454e9632`(登录后可在redis 中找到token `login:token:7b97927035cf4a5685c865e8ab84fa43`),响应结果为：

```json
{
    "success": true,
    "data": 150956409553420289
}
```



### 6.3 秒杀优化-基于阻塞队列实现秒杀优化

**VoucherOrderServiceImpl**

修改下单动作，现在我们去下单时，是�?�过lua表达式去原子执行判断逻辑，如果判断我出来不为0 ，则要么是库存不足，要么是重复下单，返回错误信息，如果是0，则把下单的逻辑保存到队列中去，然后异步执行

```java
package com.hmdp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.Result;
import com.hmdp.entity.VoucherOrder;
import com.hmdp.mapper.VoucherOrderMapper;
import com.hmdp.service.ISeckillVoucherService;
import com.hmdp.service.IVoucherOrderService;
import com.hmdp.utils.RedisIdWorker;
import com.hmdp.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * 服务实现�?
 * </p>
 */
@Service
@Slf4j
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService {

    @Resource
    private ISeckillVoucherService iSeckillVoucherService;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redissonClient;

    private IVoucherOrderService proxy;


    private static final DefaultRedisScript<Long> SECKILL_SCRIPT;

    static {
        SECKILL_SCRIPT = new DefaultRedisScript<>();
        SECKILL_SCRIPT.setLocation(new ClassPathResource("seckill_01.lua"));
        SECKILL_SCRIPT.setResultType(Long.class);
    }

    // 阻塞队列
    private BlockingQueue<VoucherOrder> orderTasks = new ArrayBlockingQueue<>(1024 * 1024);

    private static final ExecutorService SECKILL_ORDER_EXECUTOR = Executors.newSingleThreadExecutor();

    @PostConstruct
    private void init() {
        SECKILL_ORDER_EXECUTOR.submit(new VoucherOrderHandler());
    }

    private class VoucherOrderHandler implements Runnable {

        @Override
        public void run() {
            while (true) {
                // 1.获取队列中的订单信息
                try {
                    VoucherOrder voucherOrder = orderTasks.take();
                    // 2.创建订单
                    handleVoucherOrder(voucherOrder);
                } catch (Exception e) {
                    log.error("处理订单异常", e);
                }


            }

        }
    }

    private void handleVoucherOrder(VoucherOrder voucherOrder) {
        // 1.获取用户id
        Long userId = voucherOrder.getUserId();

        // 2.创建锁对�?(新增代码)
        RLock lock = redissonClient.getLock("lock:order:" + userId);

        // 3.获取锁对�?
        boolean isLock = lock.tryLock();

        //加锁失败
        if (!isLock) {
            // 获取锁失�?
            log.error("不允许重复下�?");
            return;
        }
        try {

            proxy.createVoucherOrder(voucherOrder);
        } finally {
            //释放�?
            lock.unlock();
        }
    }

    // 方式二：用lua脚本判断秒杀资格   (方式�?见下面注�?)
    @Override
    public Result seckillVoucher(Long voucherId) {
        // 获取用户
        Long userId = UserHolder.getUser().getId();

        // 1.执行lua脚本
        Long result = stringRedisTemplate.execute(
                SECKILL_SCRIPT,
                Collections.emptyList(),
                voucherId.toString(),
                userId.toString()
        );

        // 2.判断结果是否�?0
        int r = result.intValue();
        if (r != 0) {
            // 2.1 不为0，代表没有购买资�?
            return Result.fail(r == 1 ? "库存不足" : "不能重复下单");
        }

        // 2.2 �?0，有购买资格，把下单信息保存到阻塞队�?
        VoucherOrder voucherOrder = new VoucherOrder();

        // 订单id
        long orderId = redisIdWorker.nextId("order");
        voucherOrder.setId(orderId);

        // 用户id
        voucherOrder.setUserId(userId);

        // 代金券id
        voucherOrder.setVoucherId(voucherId);

        // 放入阻塞队列
        orderTasks.add(voucherOrder);

        // 获取代理对象(事务)
        proxy = (IVoucherOrderService) AopContext.currentProxy();

        // 3.返回订单id
        return Result.ok(orderId);
    }

    @Transactional
    @Override
    public void createVoucherOrder(VoucherOrder voucherOrder) {

        // 5.�?人一�?
        // 5.1 查询订单
        Long userId = voucherOrder.getVoucherId();
        Integer count = query().eq("user_id", userId).eq("voucher_id", voucherOrder.getVoucherId()).count();
        if (count > 0) {
            // 用户已经购买过了
            log.error("用户已经购买过了");
            return;
        }


        // 6.扣减库存(解决超卖问题) 进一步优化�?��??>提高抢购成功�?
        boolean isSuccess = iSeckillVoucherService.update()
                .setSql("stock = stock -1")  // set stock = stock -1
                .eq("voucher_id", voucherOrder.getVoucherId())
                .gt("stock", 0)  // where id = ? and stock > 0
                .update();

        if (!isSuccess) {
            // 扣减失败
            log.error("库存扣减失败�?");
            return;
        }

        // 7.创建订单(订单信息：订单id、用户id、代金券id)
        save(voucherOrder);
    }

}
```

测试方法同上

**小�?�结�?**

秒杀业务的优化�?�路是什么？

* 先利用Redis完成库存余量、一人一单判断，完成抢单业务
* 再将下单业务放入阻塞队列，利用独立线程异步下�?
* 基于阻塞队列的异步秒�?存在哪些问题�?
  * 内存限制问题
  * 数据安全问题
