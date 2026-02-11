package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import net.engineeringdigest.journalApp.repository.UserEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserEntryRepo UserRepo;



  //  private static final Logger logger= LoggerFactory.getLogger(UserService.class);


    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    public void saveEntry(User User1){
        UserRepo.save(User1);
    }
    public boolean savenewEntry(User User1){

      try {
          User1.setPassword(passwordEncoder.encode(User1.getPassword()));
          User1.setRoles(Arrays.asList("USER"));
          UserRepo.save(User1);
          return true;

      }catch (Exception e){
//          log.info("hahahahahahahah");
//          log.error("error");
//          log.warn("warn");
//          log.debug("debug");
//          log.trace("hahahahahahahah");


     return false;
      }

    }

    public void saveAdmin(User User1){
        User1.setPassword(passwordEncoder.encode(User1.getPassword()));
        User1.setRoles(Arrays.asList("USER","ADMIN"));
        UserRepo.save(User1);
    }

    public List<User> getAll(){
        return UserRepo.findAll();
    }

    public Optional<User> getbyId(ObjectId id){
        return UserRepo.findById(id);
    }

    public void deletebyid(ObjectId id){
        UserRepo.deleteById(id);
    }

    public User findByUserName(String userName){
        return UserRepo.findByUserName(userName);
    }



}

