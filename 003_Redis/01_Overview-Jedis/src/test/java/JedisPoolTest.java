import com.lsl.code.util.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisPoolTest {
    private Jedis jedis;

    @BeforeEach
    void setUp(){
        // ��ȡ����
        jedis = JedisConnectionFactory.getJedis();
    }

    @Test
    void testString(){
        // ��������
        String result = jedis.set("name","lisi");
        System.out.println("result = " + result);  // result = OK

        // ��ȡ����
        String name = jedis.get("name");
        System.out.println("name = " + name); // name = lisi
    }

    @Test
    void testHash(){
        // ����hash����
        jedis.hset("user:1","name","Jack");
        jedis.hset("user:1","age","21");

        // ��ȡ����
        Map<String,String> map = jedis.hgetAll("user:1");
        System.out.println(map);  // {name=Jack, age=21}
    }

    @AfterEach
    void tearDown(){
        if(jedis != null){
            jedis.close();  // ���������Ĺرգ����ǽ����ӹ黹�����ӳ�
        }
    }

/* jedis.close() Դ�룺
      public void close() {
        if (this.dataSource != null) {
            JedisPoolAbstract pool = this.dataSource;
            this.dataSource = null;
            if (this.isBroken()) {
                pool.returnBrokenResource(this);
            } else {
                pool.returnResource(this);
            }
        } else {
            super.close();
        }
    }*/
}
