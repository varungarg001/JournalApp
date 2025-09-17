package net.engineeringdigest.journalApp.Controller;
import net.engineeringdigest.journalApp.entity.JournalPOJO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

    private Map<Long,JournalPOJO> journalEntries=new HashMap<>();

    @GetMapping
    public List<JournalPOJO> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalPOJO myEntry){
//        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("id/{myID}")
    public JournalPOJO getElementByID(@PathVariable long myID){
        return journalEntries.get(myID);
    }

    @PutMapping("id/{myid}")
    public JournalPOJO updateJournalByID(@PathVariable long myid,@RequestBody JournalPOJO myEntry){
        return journalEntries.put(myid,myEntry);
    }

    @DeleteMapping("id/{myid}")
    public JournalPOJO deleteJournalByID(@PathVariable long myid){
        return journalEntries.remove(myid);
    }
}
