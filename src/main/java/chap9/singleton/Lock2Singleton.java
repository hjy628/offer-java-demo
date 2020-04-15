package chap9.singleton;

/**
 * @auther: hjy
 * @Date: 2020/4/15 17:33
 * @Description:  双重校验锁
 *
 */

public class Lock2Singleton {

    private volatile static Lock2Singleton singleton; //1:对象锁
    private Lock2Singleton(){}

    public static Lock2Singleton getSingleton(){
        if (singleton == null){
            synchronized (Singleton.class){ //2:synchronized方法锁
                if (singleton == null){
                    singleton = new Lock2Singleton();
                }
            }
        }
        return singleton;
    }


}
