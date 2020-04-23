package chap9.template;

/**
 * @auther: hjy
 * @Date: 2020/4/23 10:03
 * @Description:
 */

public class TemplateMethodTestDemo {

    public static void main(String[] args) {
        //办理取钱流程
        AbstractTemplate template1 = new TakeMoney();
        template1.templateMethod();

        //办理存储流程
        AbstractTemplate template2 = new SaveMoney();
        template2.templateMethod();
    }

}
