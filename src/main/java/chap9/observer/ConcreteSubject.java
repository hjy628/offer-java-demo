package chap9.observer;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 10:24
 * @Description:
 */

public class ConcreteSubject extends Subject {

    private final static Logger logger = Logger.getLogger("ConcreteSubject");

    @Override
    public void notifyOnserver(String message) {
        for (Object obs :
                observers) {
            logger.info("notify observer " + message + "  change...");
            ((Observer)obs).dataChange(message);
        }

    }
}
