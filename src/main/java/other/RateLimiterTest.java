package other;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @auther: hjy
 * @Date: 2020/5/13 14:47
 * @Description: 平滑突发限流(SmoothBursty)和平滑预热限流(SmoothWarmingUp)实现。
 */

public class RateLimiterTest {

    public static void main(String[] args) {
        new RateLimiterTest().smoothWarmingUp();
    }

    public void test()
    {
        /**
         * 创建一个限流器，设置每秒放置的令牌数：2个。速率是每秒可以2个的消息。
         * 返回的RateLimiter对象可以保证1秒内不会给超过2个令牌，并且是固定速率的放置。达到平滑输出的效果
         */
        RateLimiter r = RateLimiter.create(2);

        while (true)
        {
            /**
             * acquire()获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
             * acquire(N)可以获取多个令牌。
             */
            System.out.println(r.acquire());
        }
    }


    public void test1() //突发流量
    {
        /**
         * 创建一个限流器，设置每秒放置的令牌数：2个。速率是每秒可以2个的消息。
         * 返回的RateLimiter对象可以保证1秒内不会给超过2个令牌，并且是固定速率的放置。达到平滑输出的效果
         */
        RateLimiter r = RateLimiter.create(2);

        while (true)
        {
            /**
             * acquire()获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
             * acquire(N)可以获取多个令牌。
             */
            System.out.println(r.acquire(2));
            System.out.println(r.acquire(1));
            System.out.println(r.acquire(1));
            System.out.println(r.acquire(1));
            System.out.println(r.acquire(1));
        }
    }


    public void test2() //突发流量
    {
        /**
         * 创建一个限流器，设置每秒放置的令牌数：2个。速率是每秒可以2个的消息。
         * 返回的RateLimiter对象可以保证1秒内不会给超过2个令牌，并且是固定速率的放置。达到平滑输出的效果
         */
        RateLimiter r = RateLimiter.create(2);

        while (true)
        {
            /**
             * acquire()获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
             * acquire(N)可以获取多个令牌。
             */
            System.out.println(r.acquire(1));
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e){}
            System.out.println(r.acquire(1));
            System.out.println(r.acquire(1));
            System.out.println(r.acquire(1));
        }
    }

    public void smoothWarmingUp() //有一定缓冲的流量输出方案
    {
        /**
         * 创建一个限流器，设置每秒放置的令牌数：2个。速率是每秒可以210的消息。
         * 返回的RateLimiter对象可以保证1秒内不会给超过2个令牌，并且是固定速率的放置。达到平滑输出的效果
         * 设置缓冲时间为3秒
         */
        RateLimiter r = RateLimiter.create(2,3,TimeUnit.SECONDS);

        while (true) {
            /**
             * acquire()获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
             * acquire(N)可以获取多个令牌。
             */
            System.out.println(r.acquire(1));
            System.out.println(r.acquire(1));
            System.out.println(r.acquire(1));
            System.out.println(r.acquire(1));
        }
    }


}
