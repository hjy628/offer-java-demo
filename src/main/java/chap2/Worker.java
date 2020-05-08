package chap2;

/**
 * @auther: hjy
 * @Date: 2020/5/8 17:10
 * @Description:
 */

public abstract class Worker {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract int workTime();
}
