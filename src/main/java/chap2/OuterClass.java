package chap2;

/**
 * @auther: hjy
 * @Date: 2020/5/8 17:03
 * @Description: 静态内部类
 */

public class OuterClass {

    private static String className = "staticInnerClass";
    //定义一个静态内部类

    public static class StaticInnerClass{
        public void getClassName(){
            System.out.println("className:"+className);
        }
    }

    public static void main(String[] args) {
        //调用静态内部类
        OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();
        staticInnerClass.getClassName();
    }


}
