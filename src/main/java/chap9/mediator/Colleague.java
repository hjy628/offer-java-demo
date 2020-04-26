package chap9.mediator;

/**
 * @auther: hjy
 * @Date: 2020/4/26 16:46
 * @Description:
 */

public abstract class Colleague {

    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract boolean operation(String message);  //同事类的操作
}
