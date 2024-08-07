package fsm;

import java.util.ArrayList;
import java.util.List;

import static fsm.Event.*;
import static fsm.State.*;

/**
 * @auther: hjy
 * @Date: 2021/7/22 20:32
 * @Description:    状态机
 */

public class Machine {

    /**
     * 状态转移表
     */
    private List<ActionMapping> mappings = new ArrayList<>();

    {
        /**
         * F(APPROVE,APPROVE_PASS) -> (PASS,ApprovePassAction)
         */
        mappings.add(ActionMapping.ofMap(APPROVE,APPROVE_PASS,PASS,new ApprovePassAction()));

        /**
         * F(APPROVE,APPROVE_REFUSED) -> (REFUSED,ApproveRefusedAction)
         */
        mappings.add(ActionMapping.ofMap(APPROVE,APPROVE_REFUSED,REFUSED,new ApproveRefusedAction()));

        /**
         * F(REFUSED,RECHECK_PASS) -> (PASS,RecheckPassAction)
         */
        mappings.add(ActionMapping.ofMap(REFUSED,RECHECK_PASS,PASS,new RecheckPassAction()));

        /**
         * F(REFUSED,RECHECK_REFUSED) -> (REFUSED,RecheckRefusedAction)
         */
        mappings.add(ActionMapping.ofMap(REFUSED,RECHECK_REFUSED,REFUSED,new RecheckRefusedAction()));

    }

    public boolean transform(State currentState,Event event,Context context){
            ActionMapping actionMapping = getMapping(currentState, event);
            if (null==actionMapping){
                throw new RuntimeException("未找到相应的映射");
            }
            Action action = actionMapping.getAction();
            action.action(context);
            return true;

    }


    private ActionMapping getMapping(State currentState,Event event){
        if (mappings.size()>0){
            for (ActionMapping m:   mappings) {
                if (m.getCurrentState().equals(currentState) && m.getEvent().equals(event)){
                    return m;
                }
            }
        }
        return null;
    }


}
