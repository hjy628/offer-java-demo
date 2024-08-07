package consistenthash;

/**
 * @auther: hjy
 * @Date: 2022/3/15 09:59
 * @Description: 一致性哈希算法接口类
 */

public interface IHashService {

    Long hash(String key);

}
