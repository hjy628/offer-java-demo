package chap9.bridge;

/**
 * @auther: hjy
 * @Date: 2020/4/22 16:16
 * @Description:
 */

public class MyDriverBridge extends DriverManagerBridge{

    @Override
    public void execute(){
        getDriver().executeSQL();
    }
}
