package chap9.template;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 10:02
 * @Description:
 */

public class TakeMoney extends AbstractTemplate{


    private final static Logger logger = Logger.getLogger("SaveMoney");

    @Override
    public void handleBusiness() {
        logger.info("take money from bank.");
    }
}
