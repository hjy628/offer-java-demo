package chap2.serial;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @auther: hjy
 * @Date: 2020/5/8 17:21
 * @Description:
 */

public class ObjectSerialTest {
    public static void main(String[] args) throws Exception{
        //序列化数据到磁盘
        FileOutputStream fos = new FileOutputStream("worker.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Worker testObject = new Worker();
        testObject.setName("hello");
        oos.writeObject(testObject);
        oos.flush();
        oos.close();
        //反序列化磁盘数据并解析数据状态
        FileInputStream fis = new FileInputStream("worker.out");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Worker deTest = (Worker) ois.readObject();
        System.out.println(deTest.getName());
    }
}
