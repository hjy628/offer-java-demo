package chap9.decorator;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/16 15:56
 * @Description: 定义Sourceable接口的实现类Source
 */

public class Source implements Sourceable {

    private final static Logger logger = Logger.getLogger("Source");

    @Override
    public void createComputer() {
        logger.info("create computer by Source");
    }
}
