package chap9.iterator;

/**
 * @auther: hjy
 * @Date: 2020/4/23 10:48
 * @Description:
 */

public class ConcreteIterator implements Iterator {

    private Collection collection;
    private int pos = -1;   //当前迭代器遍历到的元素位置

    public ConcreteIterator(Collection collection) {
        this.collection = collection;
    }

    @Override
    public Object previous() {
        if (pos>0){
            pos--;
        }
        return collection.get(pos);
    }

    @Override
    public Object next() {
        if (pos < collection.size() -1){
            pos++;
        }
        return collection.get(pos);
    }

    @Override
    public boolean hasNext() {
        if (pos<collection.size()-1){
            return true;
        }else {
            return false;
        }
    }
}
