package chap2;

/**
 * @auther: hjy
 * @Date: 2020/5/8 16:50
 * @Description:
 */

public class Apple {
    //2.使用注解接口
    @FruitProvider(id = 1,name = "河南红太阳集团",address = "河南省郑州市")
    private String appleProvider;

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }
}
