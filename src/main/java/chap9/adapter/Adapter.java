package chap9.adapter;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/16 15:33
 * @Description:  继承并实现  类适配器模式
 */

public class Adapter extends Source implements Targetable {

    private final static Logger logger = Logger.getLogger("Adapter");


    @Override
    public void editWordFile() {
            logger.info("a word file editing");
    }
}
