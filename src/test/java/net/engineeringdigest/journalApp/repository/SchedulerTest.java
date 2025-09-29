package net.engineeringdigest.journalApp.repository;


import net.engineeringdigest.journalApp.EmailSenderScheduler.SendingMailScheduler;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SchedulerTest {

    @Autowired
    private SendingMailScheduler scheduler;

    @Test
    @Disabled
    void mail(){
        scheduler.getUserAndSendMail();
    }
}
