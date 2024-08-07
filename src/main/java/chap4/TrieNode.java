package chap4;

/**
 * @auther: hjy
 * @Date: 2024/8/7 11:09
 * @Description: 前缀树
 *
 * 应用场景:
 * 1） 字符串检索
 * 事先将已知的一些字符串（字典）的有关信息保存到trie树里，查找另外一些未知字符串是否出现过或者出现频率。
 * 举例：
 * 1，给出N 个单词组成的熟词表，以及一篇全用小写英文书写的文章，请你按最早出现的顺序写出所有不在熟词表中的生词。
 * 2，给出一个词典，其中的单词为不良单词。单词均为小写字母。再给出一段文本，文本的每一行也由小写字母构成。判断文本中是否含有任何不良单词。例如，若rob是不良单词，那么文本problem含有不良单词。
 *
 * 3，1000万字符串，其中有些是重复的，需要把重复的全部去掉，保留没有重复的字符串。
 *
 *
 *
 * （2）文本预测、自动完成，see also，拼写检查
 *
 *
 *
 * （3）词频统计
 *
 * 1，有一个1G大小的一个文件，里面每一行是一个词，词的大小不超过16字节，内存限制大小是1M。返回频数最高的100个词。
 *
 * 2，一个文本文件，大约有一万行，每行一个词，要求统计出其中最频繁出现的前10个词，请给出思想，给出时间复杂度分析。
 *
 * 3，寻找热门查询：搜索引擎会通过日志文件把用户每次检索使用的所有检索串都记录下来，每个查询串的长度为1-255字节。假设目前有一千万个记录，这些查询串的重复度比较高，虽然总数是1千万，但是如果去除重复，不超过3百万个。一个查询串的重复度越高，说明查询它的用户越多，也就越热门。请你统计最热门的10个查询串，要求使用的内存不能超过1G。
 * (1) 请描述你解决这个问题的思路；
 * (2) 请给出主要的处理流程，算法，以及算法的复杂度。
 *
 * ==》若无内存限制：Trie + “k-大/小根堆”（k为要找到的数目）。
 *
 * 否则，先hash分段再对每一个段用hash（另一个hash函数）统计词频，再要么利用归并排序的某些特性（如partial_sort），要么利用某使用外存的方法。参考
 *
 * 　　“海量数据处理之归并、堆排、前K方法的应用：一道面试题” http://www.dataguru.cn/thread-485388-1-1.html。
 *
 * 　　“算法面试题之统计词频前k大” http://blog.csdn.net/u011077606/article/details/42640867
 *
 * 　　 算法导论笔记——第九章 中位数和顺序统计量 http://www.cnblogs.com/justinh/p/6518354.html
 *
 *
 *
 * （4）排序
 *
 * Trie树是一棵多叉树，只要先序遍历整棵树，输出相应的字符串便是按字典序排序的结果。
 * 比如给你N 个互不相同的仅由一个单词构成的英文名，让你将它们按字典序从小到大排序输出。
 *
 *
 *
 * （5）字符串最长公共前缀
 * Trie树利用多个字符串的公共前缀来节省存储空间，当我们把大量字符串存储到一棵trie树上时，我们可以快速得到某些字符串的公共前缀。
 * 举例：
 * 给出N 个小写英文字母串，以及Q 个询问，即询问某两个串的最长公共前缀的长度是多少？
 * 解决方案：首先对所有的串建立其对应的字母树。此时发现，对于两个串的最长公共前缀的长度即它们所在结点的公共祖先个数，于是，问题就转化为了离线（Offline）的最近公共祖先（Least Common Ancestor，简称LCA）问题。
 * 而最近公共祖先问题同样是一个经典问题，可以用下面几种方法：
 * 1. 利用并查集（Disjoint Set），可以采用采用经典的Tarjan 算法；
 * 2. 求出字母树的欧拉序列（Euler Sequence ）后，就可以转为经典的最小值查询（Range Minimum Query，简称RMQ）问题了；
 *
 *
 *
 * （6）字符串搜索的前缀匹配
 * trie树常用于搜索提示。如当输入一个网址，可以自动搜索出可能的选择。当没有完全匹配的搜索结果，可以返回前缀最相似的可能。
 * Trie树检索的时间复杂度可以做到n，n是要检索单词的长度，
 * 如果使用暴力检索，需要指数级O(n2)的时间复杂度。
 *
 *
 *
 * （7） 作为其他数据结构和算法的辅助结构
 * 如后缀树，AC自动机等
 *
 * 后缀树可以用于全文搜索
 *
 *
 *
 * 转一篇关于几种Trie速度比较的文章：http://www.hankcs.com/nlp/performance-comparison-of-several-trie-tree.html
 *
 * Trie树和其它数据结构的比较 http://www.raychase.net/1783
 *
 *
 *
 */

public class TrieNode {

    TrieNode root;

    int pass;
    int end;
    TrieNode[] nexts;

    public TrieNode() {
        pass = 0;
        end = 0;
        nexts = new TrieNode[26];
    }


    /**
     * 前缀树的节点添加
     * 往前缀树中插入一个单词。
     *
     * 这有三种情况。
     * 1、这个单词已经存在
     * 2、这个单词已经是前缀了
     * 3、这个单词不存在
     *
     * 对这三种情况，首先要做的都是遍历这棵树。
     * 如果存在，修改路径上每个节点的path和end值。
     * 如果是前缀，那就改成完整的单词。
     * 如果不存在，那就把缺少的字母补进去，并设为完整的单词。
     * @param word
     */
    public void insert(String word){    //加入一个字符串
        if (word == null){
            return;
        }
        char[] chars = word.toCharArray();  //将word转换为字符型的数组
        TrieNode node = root;   //node从根节点出发
        node.pass++;    //头节点++ //根节点的p值可以表示为添加了多少个字符串
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - 'a'; //index对应nexts数组的下标0,1,2, ‘a’-'a'=0,'b'-'a'=1
            if (node.nexts[index]==null){ //如果不存在该节点则创建对应的一个新节点
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index]; //按字符的顺序，指针移动到路径对应的节点
            node.pass++;//沿途每路过一个节点++
        }
    }


    /**
     * 前缀树的查询
     * 两种查找方法，第一种是查询某个字符word在前缀树中出现的次数；第二种是前缀树所有加入的字符串中，有几个是以pre这个字符串作为前缀的。
     *
     * 3.1 当前字符出现的次数
     * 遍历整个字符串，如果在遍历途中发现某个路径不存在（即路径的尾节点==null），则表示前缀树从未存储过该字符串。
     *
     * 经过遍历后，节点指针一定会来到最后一个字符路径的尾节点，这个节点的 end 记录了总共有多少个字符串以这个字符路径结尾，所以直接返回 end 即可。
     * @param word
     * @return
     */
    public int search(String word){
        if (word==null){
            return 0;
        }

        char[] chars = word.toCharArray();//将word转换为char型的数组
        TrieNode node = root;//node从根节点出发
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - 'a';//index对应nexts数组的下标0,1,2, ‘a’-'a'=0,'b'-'a'=1
            if (node.nexts[index]==null){//表示该字符串不存在，直接返回0
                return 0;
            }
            node = node.nexts[index];//移动节点指针
        }
        return node.end;//遍历结束后，节点指针来到字符串的尾节点，直接返回end统计值
    }


    /**
     * 3.2 查询pre前缀出现的次数
     * 前缀查找统计的逻辑和字符串查找的逻辑几乎完全一样，唯一不同的是，在最后返回时，返回的是字符串尾节点的 pass 值，它代表有多少个字符串经过了这个节点。
     * @param pre
     * @return
     */
    public int prefixNumber(String pre){
        if (pre == null){
            return 0;
        }
        char[] chars = pre.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - 'a';
            if (node.nexts[index]==null){
                return 0;
            }
            node = node.nexts[index];
        }
        return node.pass;
    }


    /**
     * 四、前缀树的删除
     * 1.在开始执行真正的删除逻辑之前，一定要先调用 search 方法判断是否存在该字符串。
     * 2、如果 node 的pass 属性-1 后是0，那么需要将节点引用置为 null，以便回收内存，同时也契合 insert、search等逻辑中 判断路径是否存在的方式。
     * 删除一个字符串(沿途p--.结尾end--即可)
     * @param word
     */
    public void delete(String word){
        if (search(word) !=0){//确定树中确实加入过word,才删除
            char[] chars = word.toCharArray();
            TrieNode node = root;
            node.pass--;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (--node.nexts[index].pass == 0){//特殊情况，如果发现某一个节点的p为0,说明该节点已经没有用了，删除该节点
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }

        }
    }




}
