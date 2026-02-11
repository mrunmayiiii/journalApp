package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.apiResponse.weatherResponse;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserEntryRepo;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserControllerV2
{
    @Autowired
    private UserService userService;

    @Autowired
    private UserEntryRepo userEntryRepo;

    @Autowired
    private WeatherService weatherService;


    @PutMapping
    public ResponseEntity<?> updateUserEntry( @RequestBody User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User userInDb=userService.findByUserName(userName);
        if(userInDb!=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.savenewEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserEntry( @RequestBody User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        userEntryRepo.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping
    public ResponseEntity<?> quotesgreeting() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        weatherResponse res = weatherService.getAPI("Mumbai");

        String greeting = "";

        if (res != null && res.getCurrent() != null) {
            greeting = " | Weather in Mumbai: "
                    + res.getCurrent().getTemperature() + "°C, "
                    + res.getCurrent().getWeatherDescriptions();
        }

        return new ResponseEntity<>(
                "hiii " + authentication.getName() + greeting,
                HttpStatus.OK
        );
    }


}
