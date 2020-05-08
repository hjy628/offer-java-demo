package chap2.serial;

import java.io.Serializable;

/**
 * @auther: hjy
 * @Date: 2020/5/8 17:19
 * @Description: 通过实现Serializable接口定义可序列化的Worker类
 */

public class Worker implements Serializable {
    //定义序列化的ID
    private static final long serialVersionUID = 1239028923L;
    //name属性将被序列化
    private String name;
    //transient修饰的变量不会被序列化
    private transient int salary;
    //静态变量属于类信息，不属于对象的状态，因此不会被序列化
    static int age = 100;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
