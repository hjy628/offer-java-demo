package chap9.singleton;

/**
 * @auther: hjy
 * @Date: 2020/4/15 17:19
 * @Description: 静态内部类
 * 通过在类中定义一个静态内部类，将对象实例的定义和初始化放在内部类中完成
 * 我们在获取对象时要通过静态内部类调用其单例对象。
 * 类的静态内部类在JVM中是唯一的，这很好地保障了单例对象的唯一性。
 */

public class Singleton {

    private static class SingletonHolder{
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton(){}

    public static final Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }


}
