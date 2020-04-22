package chap9.facade;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/22 15:50
 * @Description: 仪表盘
 */

public class Dashboard {

    private final static Logger logger = Logger.getLogger("Dashboard");

    public void startup(){
        logger.info("dashboard startup........");
    }
    public void shutdown(){
        logger.info("dashboard shutdown........");
    }



}
