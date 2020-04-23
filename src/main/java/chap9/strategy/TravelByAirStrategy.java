package chap9.strategy;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 09:40
 * @Description:
 */

public class TravelByAirStrategy implements TravelStrategy{


    private final static Logger logger = Logger.getLogger("TravelByAirStrategy");


    @Override
    public void travelMode() {
        logger.info("travel by air");
    }
}
