package chap4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @auther: hjy
 * @Date: 2024/8/5 09:45
 * @Description:
 *
 * 词典中最长的单词
 * 给出一个字符串数组words 组成的一本英语词典。返回words 中最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。
 *
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
 *
 *
 * 问题分析
 * 这是一道典型的前缀树的问题,但是这一题有一些特殊的要求,返回的答案是:
 *
 * 1.最长的单词
 *
 * 2.这个单词由其他单词逐步构成
 *
 * 3.长度相同返回字典序小的
 *
 * 因此我们需要对前缀树的相关代码进行修改,把字符串一一插入的代码还是不改变的,
 * 主要修改的是查找的代码,应该在 trie.next.get(c) == null在增加一个判断为false的条件,
 * 就是每一个结点都应该有一个标志true,表示每个节点都存在一个单词,最终一步步构成最长的单词(叶子结点的单词)
 *
 *
 */

public class DictQuestion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       args = scanner.next().split(",");
        Solution solution = new DictQuestion().new Solution();
        System.out.printf("longest:{%s}", solution.longestWord(args));
    }


    public class Solution{

        public  String longestWord(String[] words){
            Trie trie = new Trie();
            for (String word : words) {
                trie.insert(word);
            }
            String longest = "";
            for (String word : words) {
                if (trie.search(word)){
                    if (word.length() > longest.length() || ((word.length() == longest.length()) && (word.compareTo(longest)<0))){
                        longest  = word;
                    }
                }
            }
            return longest;
        }
    }


    class Trie {

        private Map<Character, Trie> next;
        private boolean isEnd;

        public Trie() {
            this.next = new HashMap<>();
            this.isEnd = false;
        }

        public void insert(String word) {
            //获得根节点
            Trie trie = this;
            for (char c : word.toCharArray()) {
                if (trie.next.get(c) == null) { //   当前节点不存在
                    trie.next.put(c, new Trie());    //创建当前节点
                }
                trie = trie.next.get(c); //得到字符c的节点，继续向下遍历
            }
            trie.isEnd = true;
        }


        /**
         * 查找字符串
         *
         * @param word
         * @return
         */
        public boolean search(String word) {
            //获得根节点
            Trie trie = this;
            for (char c : word.toCharArray()) {
                if (trie.next.get(c) == null || !trie.next.get(c).isEnd) {  //当前节点不存在
                    return false;
                }
                trie = trie.next.get(c); //得到字符c的节点，继续向下遍历
            }
            return trie.isEnd;
        }

    }



}
