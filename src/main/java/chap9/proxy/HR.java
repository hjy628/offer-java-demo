package chap9.proxy;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/16 16:20
 * @Description:
 */

public class HR implements Company {

    private final static Logger logger = Logger.getLogger("HR");

    @Override
    public void findWorker(String title) {
        logger.info("i need find a worker,title is:  "+title);
    }
}
