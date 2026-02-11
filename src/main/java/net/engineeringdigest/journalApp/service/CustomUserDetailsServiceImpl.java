package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserEntryRepo userEntryRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        net.engineeringdigest.journalApp.entity.User user=  userEntryRepo.findByUserName(username);

        if(user!=null){

            UserDetails userDetails=org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
            return userDetails;

        }
        throw new UsernameNotFoundException("user not found"+username);

    }
}
