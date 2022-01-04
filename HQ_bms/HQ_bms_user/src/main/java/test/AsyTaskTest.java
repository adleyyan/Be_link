package test;

import com.hq.bms.UserBootstrap;
import com.hq.bms.task.AsyncExecutorTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserBootstrap.class)
public class AsyTaskTest {

    @Resource
    private AsyncExecutorTask task;

    @Test
    public void test(){
        log.info("springboot 测试单元执行------------------------！");
    }
    @Test
    public void test2() throws Exception {
        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();
    }


}
