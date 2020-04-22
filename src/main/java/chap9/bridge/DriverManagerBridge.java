package chap9.bridge;

/**
 * @auther: hjy
 * @Date: 2020/4/22 16:14
 * @Description:
 */

public abstract class DriverManagerBridge {
    private Driver driver;

    public void execute(){
        this.driver.executeSQL();
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
