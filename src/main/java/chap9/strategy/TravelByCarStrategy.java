package chap9.strategy;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 09:40
 * @Description:
 */

public class TravelByCarStrategy implements TravelStrategy{


    private final static Logger logger = Logger.getLogger("TravelByCarStrategy");


    @Override
    public void travelMode() {
        logger.info("travel by car");
    }
}
