package chap9.composite;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/22 16:37
 * @Description:
 */

public class CompositeTestDemo {

    private final static Logger logger = Logger.getLogger("CompositeTestDemo");

    public static void main(String[] args) {
        TreeNode nodeA = new TreeNode("A");
        TreeNode nodeB = new TreeNode("B");
        nodeA.add(nodeB);
        logger.info(nodeA.toString());
    }
}
