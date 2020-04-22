package chap9.facade;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/22 15:54
 * @Description: 门面类
 */

public class Starter {

    private final static Logger logger = Logger.getLogger("Starter");

    private Dashboard dashboard;
    private Engine engine;
    private SelfCheck selfCheck;

    public Starter() {
        this.dashboard = new Dashboard();
        this.engine = new Engine();
        this.selfCheck = new SelfCheck();
    }

    public void startup(){
        logger.info("car begine startup");
        engine.startup();
        dashboard.startup();
        selfCheck.startupCheck();
        logger.info("car  startup finished");
    }


    public void shutdown(){
        logger.info("car begine shutdown");
        selfCheck.shutdownCheck();
        engine.shutdown();
        dashboard.shutdown();
        logger.info("car shutdown finished");
    }




}
