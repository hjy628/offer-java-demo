package chap9.chain;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 15:03
 * @Description:
 */

public class AuthHandler extends AbstractHandler implements Handler {


    private final static Logger logger = Logger.getLogger("AuthHandler");

    private String name;

    public AuthHandler(String name) {
        this.name = name;
    }

    @Override
    public void operator() {
            logger.info("user auth....");
            if (getHandler()!=null){    //执行责任链的下一个流程
                getHandler().operator();;
            }
    }
}
