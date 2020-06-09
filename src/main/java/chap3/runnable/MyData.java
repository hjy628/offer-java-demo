package chap3.runnable;

/**
 * @auther: hjy
 * @Date: 2020/6/4 21:08
 * @Description:
 */

public class MyData {
    private int j = 0;
    public synchronized void add(){
        j++;
        System.out.println("线程"+Thread.currentThread().getName()+"j为: "+j);
    }


    public synchronized void dec(){
        j--;
        System.out.println("线程"+Thread.currentThread().getName()+"j为: "+j);
    }

    public int getData(){
        return j;
    }

}
