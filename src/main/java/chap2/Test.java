package chap2;

/**
 * @auther: hjy
 * @Date: 2020/5/8 17:11
 * @Description:
 */

public class Test {
    public void test(Worker worker){
        System.out.println(worker.getName() + " 工作时间: " + worker.workTime());
    }

    public static void main(String[] args) {
        Test test = new Test();
        //在方法中定义并使用匿名内部类
        test.test(new Worker() {
            @Override
            public int workTime() {
                return 8;
            }

            @Override
            public String getName() {
                return "alex";
            }
        });
    }
}
