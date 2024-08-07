package chap4;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: hjy
 * @Date: 2024/8/2 18:04
 * @Description:前缀树实现
 */

public class Trie {

    private Map<Character,Trie> next;
    private boolean isEnd;

    public Trie() {
        this.next = new HashMap<>();
        this.isEnd = false;
    }

    public void insert(String word){
        //获得根节点
        Trie trie = this;
        for (char c : word.toCharArray()){
            if (trie.next.get(c) == null){ //   当前节点不存在
                    trie.next.put(c,new Trie());    //创建当前节点
            }
            trie = trie.next.get(c); //得到字符c的节点，继续向下遍历
        }
        trie.isEnd = true;
    }


    /**
     * 查找字符串
     * @param word
     * @return
     */
    public boolean search(String word){
        //获得根节点
        Trie trie = this;
        for (char c : word.toCharArray()){
            if (trie.next.get(c) == null){  //当前节点不存在
                return false;
            }
            trie = trie.next.get(c); //得到字符c的节点，继续向下遍历
        }
        return trie.isEnd;
    }

    public boolean startWith(String prefix){
        //获得根节点
        Trie trie = this;
        for (char c : prefix.toCharArray()){
            if (trie.next.get(c) == null){  //当前节点不存在
                return false;
            }
            trie = trie.next.get(c); //得到字符c的节点，继续向下遍历
        }
        return true;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("helloworld");
        trie.insert("abcd");
        trie.insert("/ab/cd");
        trie.insert("/ab/c");


        System.out.println(trie.next);
        System.out.println(trie.search("ab"));
        System.out.println(trie.search("abcd"));
        System.out.println(trie.search("/ab/c"));
        System.out.println(trie.startWith("/ab/c"));
        System.out.println(trie.startWith("/ab"));
        System.out.println(trie.startWith("/ac"));

    }

}
