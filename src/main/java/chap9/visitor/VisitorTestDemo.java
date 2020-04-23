package chap9.visitor;

/**
 * @auther: hjy
 * @Date: 2020/4/23 17:28
 * @Description:
 */

public class VisitorTestDemo {
    public static void main(String[] args) {
        Element element = new ProjectElement("mobike","share bicycle");
        element.accept(new CTOVisitor());
        element.accept(new CEOVisitor());
    }
}
