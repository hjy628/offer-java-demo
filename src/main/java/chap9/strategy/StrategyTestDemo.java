package chap9.strategy;

/**
 * @auther: hjy
 * @Date: 2020/4/23 09:43
 * @Description:
 */

public class StrategyTestDemo {

    public static void main(String[] args) {
        Context context = new Context();
        TravelStrategy travelByAirStrategy = new TravelByAirStrategy();
        //设置出行策略为飞机
        context.setTravelStrategy(travelByAirStrategy);
        context.travelMode();
        //设置出行策略为自驾
        TravelStrategy travelByCarStrategy = new TravelByCarStrategy();
        context.setTravelStrategy(travelByCarStrategy);
        context.travelMode();


    }

}
