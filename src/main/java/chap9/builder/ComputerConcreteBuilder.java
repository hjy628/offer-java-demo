package chap9.builder;


import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/16 14:33
 * @Description:
 */

public class ComputerConcreteBuilder implements ComputerBuilder{
    Computer computer;
    private final static Logger logger = Logger.getLogger("ComputerConcreteBuilder");

    public ComputerConcreteBuilder() {
        this.computer = new Computer();
    }

    @Override
    public void buildCpu() {
        logger.info("buildCpu......");
        computer.setCpu("8core");
    }

    @Override
    public void buildMemory() {
        logger.info("buildMemory......");
        computer.setMemory("16G");
    }

    @Override
    public void buildDisk() {
        logger.info("buildDisk......");
        computer.setDisk("1TB");
    }

    @Override
    public Computer buildComputer() {
        return computer;
    }
}
