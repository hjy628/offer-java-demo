package chap9.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: hjy
 * @Date: 2020/4/23 10:46
 * @Description:
 */

public class ListCollection implements Collection {
    public List list = new ArrayList(); //list用于数据的存储


    @Override
    public Iterator iterator() {
        return new ConcreteIterator(this);
    }

    @Override
    public Object get(int i) {
        return list.get(i);
    }

    @Override
    public boolean add(Object object) {
        list.add(object);
        return true;
    }

    @Override
    public int size() {
        return list.size();
    }
}
