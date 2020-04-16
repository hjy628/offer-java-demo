package chap9.decorator;

/**
 * @auther: hjy
 * @Date: 2020/4/16 16:00
 * @Description:
 */

public class DecoratorDemo {
    public static void main(String[] args) {
        Sourceable source = new Source();
        Sourceable obj = new Decorator(source);
        obj.createComputer();
    }
}
