package chap9.absfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @auther: hjy
 * @Date: 2020/4/15 15:20
 * @Description: 使用抽象工厂模式
 */
@Slf4j
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        AbstractFactory phoneFactory = new PhoneFactory();
        Phone phoneHuaWei = phoneFactory.createPhone("HuaWei");
        Phone phoneIphone = phoneFactory.createPhone("Apple");
        System.out.println(phoneHuaWei.call());
        System.out.println(phoneIphone.call());

        AbstractFactory computerFactory = new ComputerFactory();
        Computer computerHuaWei = computerFactory.createComputer("HuaWei");
        Computer computerIphone = computerFactory.createComputer("Apple");
        System.out.println(computerHuaWei.internet());
        System.out.println(computerIphone.internet());
    }
}
