package chap9.proxy;

/**
 * @auther: hjy
 * @Date: 2020/4/16 16:26
 * @Description: 使用代理模式
 */

public class ProxyDemo {
    public static void main(String[] args) {
        Company company = new Proxy();
        company.findWorker("Java");
    }
}
