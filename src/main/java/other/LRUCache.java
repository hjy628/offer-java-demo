package other;

import java.util.HashMap;

/**
 * @auther: hjy
 * @Date: 2021/10/27 17:22
 * @Description: LRUCache实现
 */

public class LRUCache {

    private int cacheSize;

    private int currentSize;

    private CacheNode head;

    private CacheNode tail;

    private HashMap<Integer, CacheNode> nodes;


    class CacheNode{
        CacheNode prev;
        CacheNode next;

        int key;

        int value;
    }

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        currentSize = 0;
        nodes = new HashMap<>(cacheSize);

    }

    public void set(Integer key,Integer value){
        if (nodes.get(key)==null){  //添加新元素

            CacheNode node = new CacheNode();
            node.key = key;
            node.value = value;

            nodes.put(key,node);

            //进行LRU操作
            if (currentSize>cacheSize){
                removeTail();
            }else {
                currentSize++;
            }
        }else { //更新元素值
            CacheNode node = nodes.get(key);
            //移到表头
            moveToHead(node);
            node.value = value;
        }
    }


    private void removeTail(){
        if (tail!=null){
            nodes.remove(tail.key);
            if (tail.prev!=null){
                tail.prev.next = null;
            }
            tail = tail.prev;
        }
    }

    private void moveToHead(CacheNode node){
        //链表中间的元素
        if (node.prev!=null){
            node.prev.next=node.next;
        }
        if (node.next!=null){
            node.next.prev = node.prev;
        }

        //移动到表头
        node.prev = null;
        if (head==null){
            head = node;
        }else {
            node.next = head;
            head.prev = node;
        }

        head = node;

        //更新tail

        //node就是尾部元素
        if (tail==node){
            //下移一位
            tail = tail.prev;
        }
        //缓存里就一个元素

        if(tail==null){
            tail=node;
        }
    }

    public int get(int key){
        if (nodes.get(key)!=null){
            CacheNode node = nodes.get(key);
            moveToHead(node);
            return node.value;
        }
        return 0;
    }


}
