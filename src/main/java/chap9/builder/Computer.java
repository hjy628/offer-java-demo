package chap9.builder;

/**
 * @auther: hjy
 * @Date: 2020/4/16 14:30
 * @Description: product角色
 */

public class Computer {
    private String cpu;
    private String memory;
    private String disk;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }
}
