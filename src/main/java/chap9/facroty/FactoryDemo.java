package chap9.facroty;

import lombok.extern.slf4j.Slf4j;

/**
 * @auther: hjy
 * @Date: 2020/4/15 15:20
 * @Description: 使用工厂模式
 */
@Slf4j
public class FactoryDemo {
    public static void main(String[] args) {
        Factory factory = new Factory();
        Phone huawei = factory.createPhone("HuaWei");
        Phone iphone = factory.createPhone("Apple");
        System.out.println(huawei.brand());
        System.out.println(iphone.brand());
    }
}
