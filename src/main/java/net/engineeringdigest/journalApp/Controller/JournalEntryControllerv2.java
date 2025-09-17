package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.entity.JournalPOJO;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping
    public ResponseEntity<?> getAllEntriesByUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users user = userEntryService.findByUserName(userName);
        List<JournalPOJO>all = user.getJournalPOJOList();

        if( all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalPOJO myEntry){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myid}")
    public ResponseEntity<JournalPOJO> getElementByID(@PathVariable ObjectId myid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users user = userEntryService.findByUserName(userName);
        List<JournalPOJO> collector = userEntryService.isIdOwnByUser(user, myid);
        if(!collector.isEmpty()){
            return new ResponseEntity<>(collector.get(0),HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myid}")
    public ResponseEntity<?> updateJournalByID(@PathVariable ObjectId myid,@RequestBody JournalPOJO newEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users user = userEntryService.findByUserName(userName);
        List<JournalPOJO> collector = userEntryService.isIdOwnByUser(user, myid);
        if(!collector.isEmpty()){
            JournalPOJO oldEntry=collector.get(0);
            oldEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ?newEntry.getTitle():oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//      return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deleteJournalByID(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users user = userEntryService.findByUserName(userName);
        List<JournalPOJO> collector = userEntryService.isIdOwnByUser(user, myid);
        if(!collector.isEmpty()){
            journalEntryService.deleteById(myid,user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
