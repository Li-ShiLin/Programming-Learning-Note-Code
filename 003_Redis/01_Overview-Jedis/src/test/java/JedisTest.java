import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp(){
        // 1.��������(������ �˿ں�)
        jedis = new Jedis("192.168.54.133",6379);

        // 2.��������
        jedis.auth("123456");

        // 3.ѡ���
        jedis.select(0);
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
            jedis.close();
        }
    }
}
