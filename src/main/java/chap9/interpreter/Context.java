package chap9.interpreter;

import java.util.HashMap;

/**
 * @auther: hjy
 * @Date: 2020/4/26 17:11
 * @Description:
 */

public class Context {
    private HashMap map = new HashMap();
    public void assign(String key,String value){
        //在环境类中设值
        map.putIfAbsent(key,value);
    }

    public String get(String key){
        //获取存储在环境类中的值
        return "";
    }
}
