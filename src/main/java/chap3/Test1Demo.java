package chap3;

/**
 * @auther: hjy
 * @Date: 2020/6/4 21:04
 * @Description:
 */

public class Test1Demo {

    public static void main(String[] args) {
        MyData data = new MyData();
        Runnable add = new AddRunnable(data);
        Runnable dec = new DecRunnable(data);
        for (int i = 0; i < 2; i++) {
            new Thread(add).start();
            new Thread(dec).start();
        }
    }


}
