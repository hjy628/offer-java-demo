package chap9.singleton;

/**
 * @auther: hjy
 * @Date: 2020/4/15 16:17
 * @Description: 懒汉模式，定义一个私有的静态对象instance,之所以定义instance为静态，是因为静态属性或方法是属于类的，能够很好地保障单例对象的唯一性;
 * 然后定义一个加锁的静态方法获取该对象，如果该对象为null,则定义一个对象实例并将其赋值给instance,这样下次再获取该对象时便能直接获取了。
 * 懒汉模式在获取对象实例时做了加锁操作，因此是线程安全的。
 */

public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton(){}

    public static synchronized LazySingleton getInstance(){
        if (instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }



}
