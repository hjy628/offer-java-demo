package chap9.mediator;

/**
 * @auther: hjy
 * @Date: 2020/4/26 16:47
 * @Description: 抽象中介者
 */

public abstract class Mediator {
    protected Colleague colleagueTenant;
    protected Colleague colleagueLandlord;

    public Mediator(Colleague colleagueTenant, Colleague colleagueLandlord) {
        this.colleagueTenant = colleagueTenant;
        this.colleagueLandlord = colleagueLandlord;
    }

    public abstract boolean notifyColleagueTenant(String message);
    public abstract boolean notifyColleagueLandlord(String message);
}
