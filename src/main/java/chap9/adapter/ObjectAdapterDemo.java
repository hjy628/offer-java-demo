package chap9.adapter;

/**
 * @auther: hjy
 * @Date: 2020/4/16 15:41
 * @Description:  对象适配器模式
 */

public class ObjectAdapterDemo {

    public static void main(String[] args) {
        Source source = new Source();
        Targetable targete = new ObjectAdapter(source);
        targete.editTextFile();
        targete.editWordFile();
    }


}
