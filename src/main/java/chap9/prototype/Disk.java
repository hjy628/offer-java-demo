package chap9.prototype;

/**
 * @auther: hjy
 * @Date: 2020/4/16 15:00
 * @Description:  应用对象深复制
 */

public class Disk implements Cloneable{
    private String ssd;
    private String hdd;

    public Disk(String ssd, String hdd) {
        this.ssd = ssd;
        this.hdd = hdd;
    }


    @Override
    public Object clone(){  //浅复制
        try {
            return (Disk)super.clone();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String toString() {
        return "Disk{" +
                "ssd='" + ssd + '\'' +
                ", hdd='" + hdd + '\'' +
                '}';
    }
}
