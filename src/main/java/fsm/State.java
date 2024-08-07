package fsm;

/**
 * @auther: hjy
 * @Date: 2021/7/22 20:21
 * @Description: 状态枚举
 */

public enum State {

    /**
     * 待审核
     */
    APPROVE,


    /**
     * 拒绝
     */
    REFUSED,

    /**
     * 同意
     */
    PASS,
}
