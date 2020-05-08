package chap2;

/**
 * @auther: hjy
 * @Date: 2020/5/8 17:05
 * @Description:  成员内部类
 */

public class OuterClass2 {
    private static int a;
    private int b;
    //定义一个成员内部类
    public class MemberInnerClass{
        public void print(){
            System.out.println(a);
            System.out.println(b);
        }
    }


    public static void main(String[] args) {
        a = 10;
        //调用静态内部类
        OuterClass2.MemberInnerClass memberInnerClass = new OuterClass2().new MemberInnerClass();
        memberInnerClass.print();
    }

}
