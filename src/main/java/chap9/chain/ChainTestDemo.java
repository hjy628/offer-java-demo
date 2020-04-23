package chap9.chain;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 15:06
 * @Description:
 */

public class ChainTestDemo {

    private final static Logger logger = Logger.getLogger("ChainTestDemo");

    public static void main(String[] args) {
        AuthHandler authHandler = new AuthHandler("auth");
        BusinessHandler businessHandler = new BusinessHandler("business");
        ResponseHandler responseHandler = new ResponseHandler("response");

        authHandler.setHandler(businessHandler);
        businessHandler.setHandler(responseHandler);
        authHandler.operator();

    }
}
