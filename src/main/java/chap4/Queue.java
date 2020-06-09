package chap4;

/**
 * @auther: hjy
 * @Date: 2020/6/5 22:27
 * @Description:
 */

public class Queue<E> {
    private Object[] data = null;
    private int maxSize; //队列的容量
    private int front; //队列头，允许删除
    private int rear; //队列尾，允许插入

    //构造函数，默认的队列大小为10


    public Queue() {
        this(10);
    }

    public Queue(int initialSize) {
        if (initialSize>=0){
            this.maxSize = initialSize;
            data = new Object[initialSize];
            front = rear = 0;
        }else {
            throw new RuntimeException("初始化大小不能小于0: "+initialSize);
        }
    }

    //在队列的尾部插入数据
    public boolean add(E e){
        if (rear == maxSize){
            throw new RuntimeException("队列已满，无法出入新的元素!");
        }else {
            data[rear++] = e;
            return true;
        }
    }

    //删除队列头部的元素;出队
    public E poll(){
        if (empty()){
            throw new RuntimeException("空队列异常!");
        }else {
            E value = (E) data[front];  //临时保存队列front端的元素的值
            data[front++] = null;   //释放队列front端的元素
            return value;
        }
    }

    //取出队列头部的元素，但不删除
    public E peek(){
        if (empty()){
            throw new RuntimeException("空队列异常!");
        }else {
            return (E) data[front];
        }
    }

    private boolean empty(){
        return front >= rear;
    }


    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();
        queue.add(1);
        System.out.println(queue.rear+"=="+queue.front);
        queue.add(2);
        System.out.println(queue.rear+"=="+queue.front);
        queue.add(3);
        System.out.println(queue.rear+"=="+queue.front);
        queue.add(4);
        System.out.println(queue.rear+"=="+queue.front);
        queue.add(5);
        System.out.println(queue.rear+"=="+queue.front);
        queue.add(6);
        System.out.println(queue.rear+"=="+queue.front);
        queue.add(7);
        System.out.println(queue.rear+"=="+queue.front);
        queue.add(8);
        System.out.println(queue.rear+"=="+queue.front);
        queue.poll();
        System.out.println(queue.rear+"=="+queue.front);
        queue.poll();
        System.out.println(queue.rear+"=="+queue.front);
        queue.poll();
        System.out.println(queue.rear+"=="+queue.front);
        queue.poll();
        System.out.println(queue.rear+"=="+queue.front);
        queue.poll();
        System.out.println(queue.rear+"=="+queue.front);
        queue.poll();
        System.out.println(queue.rear+"=="+queue.front);
        queue.poll();
        System.out.println(queue.rear+"=="+queue.front);
        queue.poll();
        System.out.println(queue.rear+"=="+queue.front);
        queue.poll();
        System.out.println(queue.rear+"=="+queue.front);
        queue.poll();
        System.out.println(queue.rear+"=="+queue.front);
    }

}
