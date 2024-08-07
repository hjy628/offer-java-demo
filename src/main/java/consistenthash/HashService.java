package consistenthash;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/**
 * @auther: hjy
 * @Date: 2022/3/15 10:00
 * @Description: 一致性哈希算法实现类
 */

public class HashService implements IHashService{


    /**
     * MurMurHash算法，性能高，碰撞率低
     * @param key String
     * @return Long
     */

    @Override
    public Long hash(String key) {
        ByteBuffer buffer = ByteBuffer.wrap(key.getBytes(StandardCharsets.UTF_8));

        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buffer.order();
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buffer.remaining() * m);

        long k;
        while (buffer.remaining() >= 8){
            k = buffer.getLong();

            k*=m;
            k^= k >>> r;
            k*=m;

            h^=k;
            h*=m;

        }

        if (buffer.remaining() >0){
            ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
            finish.put(buffer).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buffer.order(byteOrder);

        return h;
    }
}
