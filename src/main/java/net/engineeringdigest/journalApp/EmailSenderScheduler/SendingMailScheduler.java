package net.engineeringdigest.journalApp.EmailSenderScheduler;


import net.engineeringdigest.journalApp.Repository.UserRepoImpl;
import net.engineeringdigest.journalApp.entity.JournalPOJO;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.service.EmailService;
import net.engineeringdigest.journalApp.service.SentimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SendingMailScheduler {

    @Autowired
    private UserRepoImpl userRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SentimentService sentimentService;


    @Scheduled(cron = "0 0 9 * * SUN")
    public void getUserAndSendMail(){

        List<Users> users = userRepo.getUserSA();
        for(Users user:users){
            List<JournalPOJO> journalEntries = user.getJournalPOJOList();
            List<String> filtered = journalEntries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(x -> x.getContent())
                    .collect(Collectors.toList());

            String join = String.join(" ", filtered);
            String sentiment = sentimentService.getSentiment(join);
            emailService.mailSender(user.getEmail(), "your sentiments for last 7 days", sentiment);

        }

    }
}
