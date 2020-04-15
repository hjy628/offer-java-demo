package chap9.absfactory;


import chap9.facroty.HuaWei;
import chap9.facroty.Iphone;

import javax.jws.Oneway;

/**
 * @auther: hjy
 * @Date: 2020/4/15 15:45
 * @Description: 手机工厂类
 */

public class PhoneFactory extends AbstractFactory{

    @Override
    public Phone createPhone(String brand){
      if ("HuaWei".equals(brand)){
          return new PhoneHuaWei();
      } else if ("Apple".equals(brand)){
          return new PhoneApple();
      }else {
          return null;
      }
    }

    @Override
    public Computer createComputer(String brand) {
        return null;
    }
}
