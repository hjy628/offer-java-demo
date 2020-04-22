package chap9.facade;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/22 15:52
 * @Description: 汽车自检器
 */

public class SelfCheck {

    private final static Logger logger = Logger.getLogger("SelfCheck");

    public void startupCheck(){
        logger.info("startup check finished.");
    }
    public void shutdownCheck(){
        logger.info("shutdown check finished.");
    }

}
