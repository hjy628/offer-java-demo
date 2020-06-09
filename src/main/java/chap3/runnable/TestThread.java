package chap3.runnable;

/**
 * @auther: hjy
 * @Date: 2020/6/4 21:11
 * @Description:
 */

public class TestThread {
    public static void main(String[] args) {
        final MyData data = new MyData();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data.add();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data.dec();
                }
            }).start();
        }
    }

}
