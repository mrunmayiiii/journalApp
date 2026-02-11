package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2
{
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<?>getAllJournalEntriesofUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();

        User user=userService.findByUserName(userName);
        List<JournalEntry>all=user.getJournalEntries();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);

        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);

   }


    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
       try{
           Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
           String userName=authentication.getName();
           System.out.println("USERNAME = " + userName);

           journalEntryService.saveEntry(myEntry,userName);
           return new ResponseEntity<>(myEntry, HttpStatus.OK);

       } catch (Exception e) {
           e.printStackTrace();

           return new ResponseEntity<>( HttpStatus.NOT_FOUND);

       }
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> getEntrybyid(@PathVariable ObjectId myid) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntry>collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            Optional<JournalEntry>journalEntry=journalEntryService.getbyId(myid);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

        @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myid ){
        /// ///gets deleted from journal entries but not from users-->cascade delete
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
       journalEntryService.deletebyid(myid,userName);
       return new ResponseEntity<>( HttpStatus.NOT_FOUND);

   }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateJournalEntry(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry newEntry

    ){
       // JournalEntry old= journalEntryService.getbyId(id).orElse(null);
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();

        User user=userService.findByUserName(userName);
        List<JournalEntry>collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            Optional<JournalEntry>journalEntry=journalEntryService.getbyId(id);
            if(journalEntry.isPresent()){
                JournalEntry old=journalEntry.get();
                old.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().equals("") ? newEntry.getTitle(): old.getTitle());
                old.setContent(newEntry.getContent() !=null && !newEntry.getContent().equals("") ? newEntry.getContent(): old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old,HttpStatus.OK);
            }

        }

        return new ResponseEntity<>( HttpStatus.NOT_FOUND);

    }


}
