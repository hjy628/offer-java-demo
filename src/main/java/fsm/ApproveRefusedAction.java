package fsm;

import java.text.MessageFormat;

/**
 * @auther: hjy
 * @Date: 2021/7/22 20:34
 * @Description:    审批拒绝动作的实现
 */

public class ApproveRefusedAction implements Action{
    @Override
    public boolean action(Context context) {

        System.out.println(MessageFormat.format("审批人{0}审批了价格，审批结果为拒绝",context.getParam()));
        //// TODO: 2021/7/22 将审批状态更新为已审核拒绝

        
        return true;
    }
}
