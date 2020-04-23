package chap9.memento;

/**
 * @auther: hjy
 * @Date: 2020/4/23 15:33
 * @Description:  备忘录
 */

public class Memento {
    private String value;

    public Memento(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
