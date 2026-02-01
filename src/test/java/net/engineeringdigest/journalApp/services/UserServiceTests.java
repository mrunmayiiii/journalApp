package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserEntryRepo;
import net.engineeringdigest.journalApp.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserEntryRepo userEntryRepo;
    @Autowired
    private UserService userService;


//    @ValueSource(strings = {
//            "mrunmai",
//            "madhura",
//            "radhika",
//            "sham"
//    })
    @ParameterizedTest
    @ArgumentsSource(userArgsProvider.class)
    public void testfindByUsername(User user){
       //  assertEquals(4,2+2);
         //User user=userEntryRepo.findByUserName("mrunmai");
         //assertNotNull(userEntryRepo.findByUserName(name),"failed for "+name);
          assertTrue(userService.savenewEntry(user));
     }

     @Disabled
     @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3.9"
    })

    public void test(int a,int b,int expected){
         assertEquals(expected,a+b);
     }


}
