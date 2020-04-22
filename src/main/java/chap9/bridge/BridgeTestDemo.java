package chap9.bridge;

/**
 * @auther: hjy
 * @Date: 2020/4/22 16:17
 * @Description:
 */

public class BridgeTestDemo {
    public static void main(String[] args) {
        DriverManagerBridge driverManagerBridge = new MyDriverBridge();
        //设置MySQL驱动
        driverManagerBridge.setDriver(new MySqlDriver());
        driverManagerBridge.execute();
        //切换到Oracle驱动
        driverManagerBridge.setDriver(new OracleDriver());
        driverManagerBridge.execute();
    }
}
