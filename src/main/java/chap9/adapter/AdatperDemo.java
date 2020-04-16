package chap9.adapter;

/**
 * @auther: hjy
 * @Date: 2020/4/16 15:35
 * @Description: 类适配器模式
 */

public class AdatperDemo {

    public static void main(String[] args) {
        Targetable targetable = new Adapter();
        targetable.editTextFile();
        targetable.editWordFile();
    }


}
