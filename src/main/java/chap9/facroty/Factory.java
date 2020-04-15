package chap9.facroty;

/**
 * @auther: hjy
 * @Date: 2020/4/15 15:18
 * @Description: 工厂类
 */

public class Factory {

    public Phone createPhone(String phoneName){
      if ("HuaWei".equals(phoneName)){
          return new HuaWei();
      } else if ("Apple".equals(phoneName)){
          return new Iphone();
      }else {
          return null;
      }
    }
}
