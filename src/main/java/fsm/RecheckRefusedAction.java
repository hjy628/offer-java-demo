package fsm;

import java.text.MessageFormat;

/**
 * @auther: hjy
 * @Date: 2021/7/22 20:43
 * @Description: 复核通过动作的实现
 */

public class RecheckRefusedAction implements Action{


    @Override
    public boolean action(Context context) {

        System.out.println(MessageFormat.format("审批人{0}对审批通过的价格进行复核，复核结果为拒绝",context.getParam()));
        //// TODO: 2021/7/22 将审批状态更新为审核拒绝

        return true;
    }
}
