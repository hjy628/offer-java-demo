package other;

import java.util.LinkedList;

/**
 * @auther: hjy
 * @Date: 2020/5/13 11:13
 * @Description:  滑动窗口（计数器） 限流
 */

public class Counter {
    //服务访问次数，可以放在Redis中，实现分布式系统的访问计数
    Long counter = 200L;
    //使用LinkedList来记录滑动窗口的10个格子。
    LinkedList<Long> ll = new LinkedList<Long>();

    public static void main(String[] args) throws Exception
    {
        Counter counter = new Counter();

        counter.doCheck();
    }

    private void doCheck() throws Exception
    {
        while (true)
        {
            ll.addLast(counter);

            if (ll.size() > 10)
            {
                ll.removeFirst();
            }

            //比较最后一个和第一个，两者相差一秒
            if ((ll.peekLast() - ll.peekFirst()) > 100)
            {
                //To limit rate
                System.out.println("开始限流");
            }

            Thread.sleep(100);
        }
    }





}
