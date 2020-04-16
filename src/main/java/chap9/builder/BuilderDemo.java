package chap9.builder;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/16 14:38
 * @Description:
 */

public class BuilderDemo {
    private final static Logger logger = Logger.getLogger("ComputerConcreteBuilder");

    public static void main(String[] args) {
        ComputerDirector computerDirector = new ComputerDirector();
        ComputerBuilder computerConcreteBuilder = new ComputerConcreteBuilder();
        Computer computer = computerDirector.constructComputer(computerConcreteBuilder);
        logger.info(computer.getCpu());
        logger.info(computer.getDisk());
        logger.info(computer.getMemory());

    }
}
