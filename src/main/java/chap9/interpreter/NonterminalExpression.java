package chap9.interpreter;

/**
 * @auther: hjy
 * @Date: 2020/4/26 17:13
 * @Description:
 */

public class NonterminalExpression implements Expression{
    private Expression left;
    private Expression right;

    public NonterminalExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void interpret() {
        //递归调用每一个组成部分的interpret()
        //在递归调用时指定组成部分的连接方式，即非终结符的功能
    }
}
