package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
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
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo JournalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry , String username){
        try{
            User user= userService.findByUserName(username);
            journalEntry.setDate((LocalDateTime.now()));
            JournalEntry saved=JournalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }

    public void saveEntry(JournalEntry journalEntry ){

        JournalEntryRepo.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return JournalEntryRepo.findAll();
    }

    public Optional<JournalEntry>getbyId(ObjectId id){
        return JournalEntryRepo.findById(id);
    }

    @Transactional
    public void deletebyid(ObjectId id, String userName){
        try {
            User user = userService.findByUserName(userName);
            boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveEntry(user);
                JournalEntryRepo.deleteById(id);
            }
        } catch (Exception e) {
            log.error("error is : ",e);
            throw new RuntimeException(e);
        }
    }




}
