package chap9.decorator;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/16 15:57
 * @Description:  装饰者类
 */

public class Decorator implements Sourceable{

    private Sourceable source;

    private final static Logger logger = Logger.getLogger("Decorator");

    public Decorator(Sourceable source) {
        super();
        this.source = source;
    }

    @Override
    public void createComputer() {
        source.createComputer();
        //在创建完电脑后给电脑装上系统
        logger.info("make system.");
    }
}
