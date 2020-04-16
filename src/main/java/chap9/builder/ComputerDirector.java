package chap9.builder;

/**
 * @auther: hjy
 * @Date: 2020/4/16 14:36
 * @Description:
 */

public class ComputerDirector {
    public Computer constructComputer(ComputerBuilder computerBuilder){
        computerBuilder.buildMemory();
        computerBuilder.buildCpu();
        computerBuilder.buildDisk();
        return computerBuilder.buildComputer();
    }


}
