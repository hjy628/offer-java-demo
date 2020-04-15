package chap9.absfactory;


/**
 * @auther: hjy
 * @Date: 2020/4/15 15:46
 * @Description: 电脑工厂类
 */

public class ComputerFactory extends AbstractFactory{

    @Override
    public Phone createPhone(String brand){
          return null;
    }

    @Override
    public Computer createComputer(String brand) {
        if ("HuaWei".equals(brand)){
            return new ComputerHuaWei();
        } else if ("Apple".equals(brand)){
            return new ComputerApple();
        }else {
            return null;
        }
    }
}
