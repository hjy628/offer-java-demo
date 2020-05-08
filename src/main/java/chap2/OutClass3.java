package chap2;

/**
 * @auther: hjy
 * @Date: 2020/5/8 17:08
 * @Description:
 */

public class OutClass3 {
    private static int a;
    private int b;

    public void partClassTest(final int c){
        final int d = 1;
        //在partClassTest方法中定义一个局部内部类PastClass
        class PastClass{
            public void print(){
                System.out.println(c);
            }
        }
    }
}
