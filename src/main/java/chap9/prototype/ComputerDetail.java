package chap9.prototype;

/**
 * @auther: hjy
 * @Date: 2020/4/16 14:58
 * @Description: 深复制类型代码
 */

public class ComputerDetail implements Cloneable {
    private String cpu;
    private String memory;
    private Disk disk;

    public ComputerDetail(String cpu, String memory, Disk disk) {
        this.cpu = cpu;
        this.memory = memory;
        this.disk = disk;
    }

    @Override
    public Object clone(){  //深复制
        try {
            ComputerDetail computerDetail =  (ComputerDetail)super.clone();
            computerDetail.disk = (Disk)this.disk.clone();
            return computerDetail;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "ComputerDetail{" +
                "cpu='" + cpu + '\'' +
                ", memory='" + memory + '\'' +
                ", disk=" + disk +
                '}';
    }
}
