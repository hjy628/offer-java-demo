package fsm;

/**
 * @auther: hjy
 * @Date: 2021/7/22 20:18
 * @Description:    事件枚举
 */

public enum Event {

    /**
     * 审批通过
     */
    APPROVE_PASS,


    /**
     * 审批拒绝
     */
    APPROVE_REFUSED,

    /**
     * 复核通过
     */
    RECHECK_PASS,

    /**
     * 复核不通过
     */
    RECHECK_REFUSED;


}
