package chap9.chain;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 15:03
 * @Description:
 */

public class ResponseHandler extends AbstractHandler implements Handler {


    private final static Logger logger = Logger.getLogger("ResponseHandler");

    private String name;

    public ResponseHandler(String name) {
        this.name = name;
    }

    @Override
    public void operator() {
            logger.info("message response....");
            if (getHandler()!=null){    //执行责任链的下一个流程
                getHandler().operator();;
            }
    }
}
