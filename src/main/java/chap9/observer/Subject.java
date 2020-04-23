package chap9.observer;

import java.util.ArrayList;
import java.util.List;

/**
 *   抽象目标类
 *
 * */
public abstract class Subject {

    protected List<Observer> observers = new ArrayList<Observer>();
    //增加观察者
    public void add(Observer observer){
        observers.add(observer);
    }

    //删除观察者
    public void remove(Observer observer){
        observers.remove(observer);
    }

    public abstract void  notifyOnserver(String message);   //通知观察者的抽象方法。


}
