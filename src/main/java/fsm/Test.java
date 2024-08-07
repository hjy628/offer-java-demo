package fsm;

import static fsm.Event.*;
import static fsm.State.*;

/**
 * @auther: hjy
 * @Date: 2021/7/22 21:02
 * @Description:    状态机测试
 */

public class Test {
    public static void main(String[] args) {
        Machine machine = new Machine();
        machine.transform(APPROVE,APPROVE_REFUSED,new Context("张磊"));
        machine.transform(REFUSED,RECHECK_REFUSED,new Context("张磊"));
        machine.transform(REFUSED,RECHECK_PASS,new Context("里诶"));
    }


}
