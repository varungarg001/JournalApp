package net.engineeringdigest.journalApp.EmailSenderScheduler;


import net.engineeringdigest.journalApp.Repository.UserRepoImpl;
import net.engineeringdigest.journalApp.entity.JournalPOJO;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.enums.Sentiment;
import net.engineeringdigest.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SendingMailScheduler {

    @Autowired
    private UserRepoImpl userRepo;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void getUserAndSendMail(){

        List<Users> users = userRepo.getUserSA();
        for(Users user:users){
            List<JournalPOJO> journalEntries = user.getJournalPOJOList();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment!=null){
                emailService.mailSender(user.getEmail(),"your sentiment for last 7 days ",mostFrequentSentiment.toString());
            }


        }

    }
}
