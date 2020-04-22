package chap9.facade;

/**
 * @auther: hjy
 * @Date: 2020/4/22 15:58
 * @Description:  使用门面模式的客户端
 */

public class FacadeTestDemo {
    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.startup();
        System.out.println("****************************************************************************");
        starter.shutdown();
    }
}
