package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.repository.UserEntryRepo;
import net.engineeringdigest.journalApp.service.CustomUserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import net.engineeringdigest.journalApp.entity.User;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class CustomUserDetailsServiceImplTests {

    @InjectMocks
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @Mock
    private UserEntryRepo userEntryRepo;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void loadUserByUsernameTest(){
        when(userEntryRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("mrunmai").password("jdjjfkfn").roles(new ArrayList<>()).build());

        UserDetails user=customUserDetailsService.loadUserByUsername("mrunmai");
        Assertions.assertNotNull(user);
    }
}
