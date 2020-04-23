package chap9.template;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 10:02
 * @Description:
 */

public class SaveMoney extends AbstractTemplate{


    private final static Logger logger = Logger.getLogger("SaveMoney");

    @Override
    public void handleBusiness() {
        logger.info("save money from bank.");
    }
}
