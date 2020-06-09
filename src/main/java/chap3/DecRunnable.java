package chap3;

/**
 * @auther: hjy
 * @Date: 2020/6/4 21:02
 * @Description:
 */

public class DecRunnable implements Runnable {

    MyData data;
//    step2: 线程使用该类的对象并调用类的方法对数据进行操作


    public DecRunnable(MyData data) {
        this.data = data;
    }

    @Override
    public void run() {
        data.dec();
    }
}
