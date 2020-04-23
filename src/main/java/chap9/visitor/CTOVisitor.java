package chap9.visitor;

import java.util.Date;
import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 17:25
 * @Description:
 */

public class CTOVisitor implements Visitor {

    private final static Logger logger = Logger.getLogger("CTOVisitor");

    @Override
    public void visit(ProjectElement element) {
        logger.info("CTO Visitor Element.");
        element.signature("CTO",new Date());
        logger.info(element.toString());
    }
}
