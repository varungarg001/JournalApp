package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Repository.UserEntryRepo;
import net.engineeringdigest.journalApp.entity.JournalPOJO;
import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@Slf4j
public class UserEntryService {
    @Autowired
    private UserEntryRepo userEntryRepo;



    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public List<Users> getAll(){
        return userEntryRepo.findAll();
    }

    public void saveNewUser(Users user){
        try{
            user.setPassWord(passwordEncoder.encode(user.getPassWord()));
            user.setRoles(Arrays.asList("USER"));
            userEntryRepo.save(user);
        }catch(Exception e){
            log.error("hahahah");
            log.debug("hahahah");
        }
    }

    public void saveUser(Users user){
        userEntryRepo.save(user);
    }

    public Optional<Users> ElementById(ObjectId id){
        return userEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        userEntryRepo.deleteById(id);
    }

    public Users findByUserName(String username){
        return userEntryRepo.findByUserName(username);
    }

    public void deleteByUsername(String username){
        Users user = userEntryRepo.findByUserName(username);
        ObjectId id = user.getId();
        userEntryRepo.deleteById(id);
    }

    public List<JournalPOJO> isIdOwnByUser(Users user,ObjectId myid){
        return user
                .getJournalPOJOList()
                .stream()
                .filter(x->x.getId().equals(myid))
                .collect(Collectors.toList());
    }

    public Users saveAdmin(Users user){
        user.setPassWord(passwordEncoder.encode(user.getPassWord()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userEntryRepo.save(user);
        return user;

    }

}


//controller --> service --> repository -->
