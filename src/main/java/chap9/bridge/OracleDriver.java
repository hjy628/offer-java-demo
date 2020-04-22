package chap9.bridge;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/22 16:13
 * @Description:
 */

public class OracleDriver implements Driver{

    private final static Logger logger = Logger.getLogger("OracleDriver");


    @Override
    public void executeSQL() {
        logger.info("execute sql by oracle driver");
    }
}
