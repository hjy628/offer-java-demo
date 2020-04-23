package chap9.chain;

/**
 * @auther: hjy
 * @Date: 2020/4/23 11:06
 * @Description:
 */

public abstract class AbstractHandler {
    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
