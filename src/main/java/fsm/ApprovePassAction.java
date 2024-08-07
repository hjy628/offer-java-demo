package fsm;

import java.text.MessageFormat;

/**
 * @auther: hjy
 * @Date: 2021/7/22 20:34
 * @Description:    审批通过动作的实现
 */

public class ApprovePassAction implements Action{
    @Override
    public boolean action(Context context) {

        System.out.println(MessageFormat.format("审批人{0}审批了价格，审批结果为通过",context.getParam()));
        //// TODO: 2021/7/22 将审批状态更新为已审核通过


        return true;
    }
}
