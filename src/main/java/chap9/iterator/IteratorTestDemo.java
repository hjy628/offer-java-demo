package chap9.iterator;

import java.util.logging.Logger;

/**
 * @auther: hjy
 * @Date: 2020/4/23 10:51
 * @Description:
 */

public class IteratorTestDemo {

    private final static Logger logger = Logger.getLogger("IteratorTestDemo");

    public static void main(String[] args) {
        //定义集合
        Collection collection = new ListCollection();
        //向集合中添加数据
        collection.add("object1");
        collection.add("object2");
        collection.add("object3");
        collection.add("object4");
        //使用迭代器遍历集合
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            logger.info(iterator.next().toString());
        }
    }
}
