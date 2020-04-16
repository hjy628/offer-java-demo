package chap9.adapter;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/16 15:46
 * @Description:  按需实现editWordFile
 */

public class SourceSub2 extends AbstractAdapter {

    private final static Logger logger = Logger.getLogger("SourceSub2");

    @Override
    public void editWordFile() {
            logger.info("a word file editing");
    }

}
