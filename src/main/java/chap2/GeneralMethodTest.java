package chap2;

import java.util.Date;

/**
 * @auther: hjy
 * @Date: 2020/5/8 17:14
 * @Description:
 */

public class GeneralMethodTest {
    public static void main(String[] args) {


    }


    //定义范型方法generalMethod, printArray为范型参数列表
    public static <T> void generalMethod(T... inputArray){
        for (T element: inputArray) {
            if (element instanceof  Integer){
                System.out.println("处理Integer类型数据中...");
            }else if  (element instanceof  String){
                System.out.println("处理String类型数据中...");
            }else if  (element instanceof  Double){
                System.out.println("处理Double类型数据中...");
            }else if  (element instanceof  Float){
                System.out.println("处理Float类型数据中...");
            }else if  (element instanceof  Long){
                System.out.println("处理Long类型数据中...");
            }else if  (element instanceof  Boolean){
                System.out.println("处理Boolean类型数据中...");
            }else if  (element instanceof Date){
                System.out.println("处理Date类型数据中...");
            }else if  (element instanceof  Worker) {
                System.out.println("处理Worker类型数据中...");
            }
        }
    }
}
