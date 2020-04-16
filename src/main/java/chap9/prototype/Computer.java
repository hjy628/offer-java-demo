package chap9.prototype;

/**
 * @auther: hjy
 * @Date: 2020/4/16 14:56
 * @Description: 浅复制类型代码
 */

public class Computer implements Cloneable{
    private String cpu;
    private String memory;
    private String disk;

    public Computer(String cpu, String memory, String disk) {
        this.cpu = cpu;
        this.memory = memory;
        this.disk = disk;
    }

    @Override
    public Object clone(){  //浅复制
        try {
            return (Computer)super.clone();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", memory='" + memory + '\'' +
                ", disk='" + disk + '\'' +
                '}';
    }
}
