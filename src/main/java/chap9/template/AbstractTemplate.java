package chap9.template;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 09:57
 * @Description: 模板类
 */

public abstract class AbstractTemplate {

    private final static Logger logger = Logger.getLogger("AbstractTemplate");

    public void templateMethod(){ //模板方法，用于核心流程和算法的定义
        checkNumber();
        queueUp();
        handleBusiness();
        serviceEvaluation();
    }

    public void checkNumber(){  //1:抽号
        logger.info("checkNumber.....");
    }


    public void queueUp(){  //2:排队
        logger.info("queueUp.....");
    }

    public abstract void  handleBusiness(); //3:业务办理

    public void serviceEvaluation(){  //4:服务评价
        logger.info("business finished,servic evaluation.....");
    }



}
