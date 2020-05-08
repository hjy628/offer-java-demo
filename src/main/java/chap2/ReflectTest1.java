package chap2;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @auther: hjy
 * @Date: 2020/5/8 16:34
 * @Description:
 */

public class ReflectTest1 {

    public static void main(String[] args) throws Exception{
        //1.获取Person类的Class对象
        Class clazz= Class.forName("chap2.Person");

        //2.获取Person类的所有方法的信息
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method:  methods) {
            System.out.println(method.toString());
        }

        //3.获取Person类的所有成员的属性信息
       Field[] fields = clazz.getDeclaredFields();
        for (Field f :
                fields) {
            System.out.println(f.toString());
        }

        //4.获取Person类的所有构造方法的信息
        Constructor[] constructor =  clazz.getDeclaredConstructors();
        for (Constructor c :
                constructor) {
            System.out.println(c.toString());
        }


        //创建对象两种方式
        //1. 使用newInstance方法创建对象
        Person p = (Person)clazz.newInstance();

        Constructor c = clazz.getDeclaredConstructor(int.class,String.class);

        //2.根据构造方法创建对象并设置属性
        Person p1 = (Person) c.newInstance(30,"hjy");

        System.out.println(p);
        System.out.println(p1);



        Method method = clazz.getMethod("setName",String.class);
        method.invoke(p,"Hello");


        System.out.println(p);



    }

}
