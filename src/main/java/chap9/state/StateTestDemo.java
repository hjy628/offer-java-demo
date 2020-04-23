package chap9.state;

/**
 * @auther: hjy
 * @Date: 2020/4/23 15:50
 * @Description:
 */

public class StateTestDemo {
    public static void main(String[] args) {
        //定义当前状态为工厂状态
        Context context = new Context(new WorkState());
        context.action();
        //切换当前状态为修改状态
        context.setState(new HolidayState());
        context.action();
    }

}
