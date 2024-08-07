package consistenthash;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @auther: hjy
 * @Date: 2022/3/15 17:48
 * @Description:  使用TreeMap实现一致性hash算法
 */

public class consistenthash {

    /**
     * 虚拟节点数量
     */
    private static final int VIRTUAL_NODE_SIZE = 4;

    /**
     * 定义treeMap
     */
    private TreeMap<Long, String> tree = new TreeMap<>();

    /**
     * 在服务器列表中根据key定位节点
     * @param values 服务器列表
     * @param key token
     * @return
     */
    public String choose(List<String> values, String key) {
        for (String value: values) {
            //value作为key，hash值作为value
            add(hash(value), value);
        }

        return selectNode(key);
    }

    /**
     * hash落环，并加入虚拟节点
     * @param key
     * @param value
     */
    private void add(long key, String value) {
        tree.clear();
        //虚拟节点
        for (int i = 0; i < VIRTUAL_NODE_SIZE; i++) {
            Long hash = this.hash("vir" + key + i);
            tree.put(hash, value);
        }
        tree.put(key, value);
    }

    /**
     * 在环中根据传入的值找到第一个server
     * 使用tailMap特性模拟hash环，如果tailMap返回空，则表示已经到了hash环的末尾，那么需要使用第一个key
     * @param value
     * @return
     */
    private String selectNode(String value) {
        long hash = hash(value);
        SortedMap<Long, String> after = tree.tailMap(hash);
        if (after != null && !after.isEmpty()) {

            String server = after.get(after.firstKey());
            System.out.println("路由成功：value: " + value + ", route server: " + server );
            return server;
        }
        return tree.firstEntry().getValue();
    }


    /**
     * hash计算
     * @param value
     * @return
     */
    private Long hash(String value) {
        byte[] digest = DigestUtils.md5(value);

        // hash code, Truncate to 32-bits
        long hashCode = ((long) (digest[3] & 0xFF) << 24) | ((long) (digest[2] & 0xFF) << 16) | ((long) (digest[1] & 0xFF) << 8) | (digest[0] & 0xFF);

        long truncateHashCode = hashCode & 0xffffffffL;
        return truncateHashCode;
    }



}
