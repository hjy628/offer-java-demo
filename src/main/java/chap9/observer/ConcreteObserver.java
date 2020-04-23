package chap9.observer;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 10:26
 * @Description:
 */

public class ConcreteObserver implements Observer {

    private final static Logger logger = Logger.getLogger("ConcreteObserver");

    @Override
    public void dataChange(String message) {
        logger.info("receive message: "+message);
    }
}
