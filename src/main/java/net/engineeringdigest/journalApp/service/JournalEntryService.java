package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepo;
import net.engineeringdigest.journalApp.entity.JournalPOJO;
import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserEntryService userEntryService;



    @Transactional
    public void saveEntry(JournalPOJO journalEntry, String username){
        try{
            Users user = userEntryService.findByUserName(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalPOJO saved = journalEntryRepo.save(journalEntry);
            user.getJournalPOJOList().add(saved);
            userEntryService.saveUser(user);
        } catch (Exception e) {
            System.out.println("an error occured while saving the entry");
            throw new RuntimeException("An error occured");
        }
    }

    public void saveEntry(JournalPOJO journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalPOJO> getAllEntry(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalPOJO> ElementById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    @Transactional
    public void deleteById(ObjectId id,Users user){
        try{
            user
                    .getJournalPOJOList()
                    .removeIf((x)->x.getId().equals(id));
            userEntryService.saveUser(user);
            journalEntryRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred: "+e);
        }
    }

}


//controller --> service --> repository -->
