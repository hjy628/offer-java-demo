package chap9.observer;

/**
 * @auther: hjy
 * @Date: 2020/4/23 10:28
 * @Description:
 */

public class ObserverTestDemo {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer observer = new ConcreteObserver();
        subject.add(observer);
        subject.notifyOnserver("data1");

    }
}
