import me.zrxjava.common.component.redis.RedisLockUtil;
import me.zrxjava.system.ShellApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author void
 * @create 2021-01-15
 */
@SpringBootTest(classes = ShellApplication.class)
public class RedisLockTest {

    @Resource
    private RedisLockUtil redisLockUtil;

    @Test
    void test(){
        Boolean flag = redisLockUtil.tryLock("hello","13",12344);
        System.out.println(flag);
    }

    @Test
    public void test1(){

    }
}
