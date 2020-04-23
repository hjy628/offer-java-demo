package chap9.visitor;

import java.util.Date;
import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 17:25
 * @Description:
 */

public class CEOVisitor implements Visitor {

    private final static Logger logger = Logger.getLogger("CEOVisitor");

    @Override
    public void visit(ProjectElement element) {
        logger.info("CEO Visitor Element.");
        element.signature("CEO",new Date());
        logger.info(element.toString());
    }
}
