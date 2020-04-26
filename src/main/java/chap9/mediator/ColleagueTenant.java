package chap9.mediator;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/26 16:52
 * @Description:    Colleague的实现类，代表房东
 */

public class ColleagueTenant extends Colleague {


    private final static Logger logger = Logger.getLogger("ColleagueTenant");

    @Override
    public boolean operation(String message) {
        logger.info("landlord receive a message from mediator: "+message);
        return true;
    }
}
