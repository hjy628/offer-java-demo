package chap9.memento;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 15:36
 * @Description:
 */

public class MementoTestDemo {


    private final static Logger logger = Logger.getLogger("MementoTestDemo");

    public static void main(String[] args) {
        //创建原始类
        Original original = new Original("张三");
        //设置备忘录
        Storage storage = new Storage(original.createMemento());
        //修改原始类的状态
        logger.info("original value: "+original.getValue());
        original.setValue("李四");
        logger.info("upodate value: "+original.getValue());
        //回复原始类的状态
        original.restoreMemento(storage.getMemento());
        logger.info("restore value: "+original.getValue());

    }
}
