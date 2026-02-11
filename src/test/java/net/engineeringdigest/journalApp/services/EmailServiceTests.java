package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.sendEmail("divyanshkhatikar@gmail.com","Utterly Confidential Task","SECRET TIME (59 7 * * SUN) call me");
    }
}
