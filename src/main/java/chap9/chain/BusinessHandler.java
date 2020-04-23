package chap9.chain;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 15:03
 * @Description:
 */

public class BusinessHandler extends AbstractHandler implements Handler {


    private final static Logger logger = Logger.getLogger("BusinessHandler");

    private String name;

    public BusinessHandler(String name) {
        this.name = name;
    }

    @Override
    public void operator() {
            logger.info("business info handler....");
            if (getHandler()!=null){    //执行责任链的下一个流程
                getHandler().operator();;
            }
    }
}
