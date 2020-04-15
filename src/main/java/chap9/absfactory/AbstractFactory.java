package chap9.absfactory;

/**
 * @auther: hjy
 * @Date: 2020/4/15 15:40
 * @Description:抽象工厂
 */

public abstract class AbstractFactory {
    public abstract Phone createPhone(String brand);
    public abstract Computer createComputer(String brand);
}
