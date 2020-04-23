package chap9.command;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 15:17
 * @Description:
 */

public class Receiver {

    private final static Logger logger = Logger.getLogger("Receiver");

    public void action(String command){ //接收并执行命令
        logger.info("command received,now execute command.");
    }


}
