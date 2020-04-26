package chap9.mediator;

/**
 * @auther: hjy
 * @Date: 2020/4/26 16:55
 * @Description:
 */

public class MediatorTestDemo {

    public static void main(String[] args) {
        //定义房客同事类
        Colleague colleagueTenant = new ColleagueTenant();
        //定义房客同事类
        Colleague colleagueLandlord = new ColleagueLandlord();
        //创建一个具体的中间者，这里可以将其理解为房屋中介
        ConcreteMediator concreteMediator = new ConcreteMediator(colleagueTenant,colleagueLandlord);
        boolean resoult = concreteMediator.notifyColleagueTenant("想租两室一厅的整套吗？");
        if (resoult){
            concreteMediator.notifyColleagueLandlord("租客对面积满意");
        }else {
            concreteMediator.notifyColleagueLandlord("租客对面积不满意");
        }

    }

}
