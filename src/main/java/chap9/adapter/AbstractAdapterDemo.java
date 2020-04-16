package chap9.adapter;

/**
 * @auther: hjy
 * @Date: 2020/4/16 15:48
 * @Description:  接口适配器模式及
 */

public class AbstractAdapterDemo {

    public static void main(String[] args) {
        Sourceable source1 = new SourceSub1();
        Sourceable source2 = new SourceSub2();
        source1.editTextFile();
        source2.editWordFile();

    }


}
