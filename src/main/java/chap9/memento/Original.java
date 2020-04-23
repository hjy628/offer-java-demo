package chap9.memento;

/**
 * @auther: hjy
 * @Date: 2020/4/23 15:33
 * @Description:  原始数据  发起人
 */

public class Original {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public Original(String value) {
        this.value = value;
    }

    public Memento createMemento(){
        return new Memento(value);
    }

    public void restoreMemento(Memento memento){
        this.value = memento.getValue();
    }



}
