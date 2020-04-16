package chap9.prototype;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/16 15:02
 * @Description:使用原型模型
 */

public class CloneableDemo {

    private final static Logger logger = Logger.getLogger("CloneableDemo");

    public static void main(String[] args) {
        //浅复制
        Computer computer = new Computer("8core","16G","1TB");
        logger.info("before simple clone: "+computer.toString());
        Computer computerClone = (Computer)computer.clone();
        logger.info("after simple clone: "+computer.toString());


        //深复制
        Disk disk = new Disk("512G","2TB");
        ComputerDetail computerDetail = new ComputerDetail("8core","16G",disk);
        logger.info("before deep clone: "+computerDetail.toString());
        ComputerDetail ccomputerDetailClone = (ComputerDetail)computerDetail.clone();
        logger.info("after deep clone: "+ccomputerDetailClone.toString());

    }


}
