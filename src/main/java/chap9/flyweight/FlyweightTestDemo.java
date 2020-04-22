package chap9.flyweight;

/**
 * @auther: hjy
 * @Date: 2020/4/22 17:04
 * @Description:
 */

public class FlyweightTestDemo {
    public static void main(String[] args) {
        //首次获取内存，将创建一个内存
        Memory memory = MemoryFactory.getMemory(32);
        //在使用后释放内存
        MemoryFactory.releaseMemory(memory.getId());
        //重新获取内存
        MemoryFactory.getMemory(32);
        MemoryFactory.getMemory(32);
    }
}
