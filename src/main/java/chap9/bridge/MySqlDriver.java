package chap9.bridge;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/22 16:13
 * @Description:
 */

public class MySqlDriver implements Driver{

    private final static Logger logger = Logger.getLogger("MySqlDriver");


    @Override
    public void executeSQL() {
        logger.info("execute sql by mysql driver");
    }
}
