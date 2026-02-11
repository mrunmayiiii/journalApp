package net.engineeringdigest.journalApp.cache;

import net.engineeringdigest.journalApp.entity.ConfigJournal;
import net.engineeringdigest.journalApp.repository.ConfigJournalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/// ////////////////////// ------ WORKS AS IN MEMORY CACHE

@Component
public class appCache {

    @Autowired
    private ConfigJournalRepo configJournalRepo;


    public Map<String,String>APPCACHE = new HashMap<>();

   @PostConstruct
   public void init(){
       APPCACHE=new HashMap<>();
       List<ConfigJournal>all=configJournalRepo.findAll();

        for(ConfigJournal configJournal : all){
            APPCACHE.put(configJournal.getKey(), configJournal.getValue());
        }
    }
}
