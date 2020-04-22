package chap9.facade;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/22 15:52
 * @Description:
 */

public class Engine {


    private final static Logger logger = Logger.getLogger("Engine");

    public void startup(){
        logger.info("engine startup........");
    }
    public void shutdown(){
        logger.info("engine shutdown........");
    }

}
