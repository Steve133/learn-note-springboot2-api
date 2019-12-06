package my.master;

import my.master.Application;
import my.master.kafka.MsgProducer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Autowired
    private MsgProducer msgProducer;

    @Test
    public void test() throws Exception {

        msgProducer.sendMessage("topic-1", "topic--------1");
        msgProducer.sendMessage("topic-2", "topic--------2");
    }
}
