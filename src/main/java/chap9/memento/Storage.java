package chap9.memento;

/**
 * @auther: hjy
 * @Date: 2020/4/23 15:35
 * @Description:  备忘录管理者
 */

public class Storage {

    private Memento memento;

    public Storage(Memento memento) {
        this.memento = memento;
    }

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
