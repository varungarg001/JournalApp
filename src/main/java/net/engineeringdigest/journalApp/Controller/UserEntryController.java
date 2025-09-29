package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.service.UserEntryService;
import net.engineeringdigest.journalApp.service.WeatherService;
import net.engineeringdigest.journalApp.entity.WeatherResponsePOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private WeatherService weatherService;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Users user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users userInDb = userEntryService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassWord(user.getPassWord());
        userEntryService.saveNewUser(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody Users user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userEntryService.deleteByUsername(userName);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> greetingUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponsePOJO response = weatherService.getWeather("mumbai");
        if(response!=null){
            return new ResponseEntity<>("Hi "+authentication.getName()+", weather feels like "+response.getCurrent().getFeelslike(),HttpStatus.OK);
        }
        return new ResponseEntity<>("Hi "+authentication.getName()+" ",HttpStatus.OK);

    }



}
