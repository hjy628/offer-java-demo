package chap9.adapter;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/16 15:46
 * @Description:  按需实现editTextFile
 */

public class SourceSub1 extends AbstractAdapter {

    private final static Logger logger = Logger.getLogger("SourceSub1");

    @Override
    public void editTextFile() {
        logger.info("a text file editing");
    }
}
