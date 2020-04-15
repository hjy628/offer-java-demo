package chap9.singleton;

/**
 * @auther: hjy
 * @Date: 2020/4/15 16:22
 * @Description: 饿汉模式指在类中直接定义全局的静态对象的实例并初始化，然后提供一个方法获取该实例对象。
 * 其与懒汉模式最大不同在于，懒汉模式在类中定义了单例但是并未实例化，实例化的过程是在获取单例对象的方法中实现的，也就是说，在第一次调用懒汉模式时，该对象一定为空。
 * 然后去实例化对象并赋值，这样下次就能直接获取对象了。
 * 而饿汉模式是在定义单例对象的同时将其实例化的，直接使用便可。在Class Loader完成后该类的实例便已经存在于JVM中了。
 */

public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();
    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return instance;
    }


}
