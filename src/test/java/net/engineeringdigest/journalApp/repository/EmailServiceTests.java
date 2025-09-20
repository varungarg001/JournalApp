package net.engineeringdigest.journalApp.repository;

import lombok.AllArgsConstructor;
import net.engineeringdigest.journalApp.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    void send(){
        emailService.mailSender("vlg952012@gmail.com",
                "testing java mail sender",
                "hello, how are you buddy!");
    }
}
