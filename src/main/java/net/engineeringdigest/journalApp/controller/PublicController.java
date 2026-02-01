package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;


    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok";
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createEntry(@RequestBody User myEntry){
        try{
            userService.savenewEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);

        }
    }

}
