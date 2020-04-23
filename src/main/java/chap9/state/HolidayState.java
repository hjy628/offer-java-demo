package chap9.state;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 15:47
 * @Description:
 */

public class HolidayState extends  AbstractState {

    private final static Logger logger = Logger.getLogger("HolidayState");


    @Override
    public void action(Context context) {
        logger.info("state change to holiday state");
        logger.info(" holiday state action is travel, shoping,watch television....");
    }
}
