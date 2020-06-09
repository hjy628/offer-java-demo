package chap3;

/**
 * @auther: hjy
 * @Date: 2020/6/4 20:59
 * @Description:
 */

public class MyData {
//    step1: 将数据抽象成MyData类，并将数据的操作(add、dec方法)作为类的方法
    private int j = 0;

    public synchronized void add(){
        j++;
        System.out.println("线程"+Thread.currentThread().getName()+"j为: "+j);
    }


    public synchronized void dec(){
        j--;
        System.out.println("线程"+Thread.currentThread().getName()+"j为: "+j);
    }




}
