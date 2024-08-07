package fsm;

import lombok.Data;

/**
 * @auther: hjy
 * @Date: 2021/7/22 20:29
 * @Description:
 */
@Data
public class ActionMapping {

    /**
     * 当前状态
     */
    private State currentState;

    /**
     * 次态
     */
    private State nextState;

    /**
     * 动作
     */
    private Action action;

    /**
     * 事件
     */
    private Event event;


    public static ActionMapping ofMap(State currentState, Event event, State nextState, Action action){
        return new ActionMapping(currentState,event, nextState, action);
    }

    private ActionMapping(State currentState, Event event, State nextState, Action action) {
        this.currentState = currentState;
        this.nextState = nextState;
        this.action = action;
        this.event = event;
    }
}
