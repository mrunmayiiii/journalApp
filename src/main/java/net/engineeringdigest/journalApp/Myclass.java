package net.engineeringdigest.journalApp;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/// ///// controller api


@RestController
public class Myclass {
    @GetMapping("/myproject/abc")
    public String sayHello(){
        return "hello ";
    }

}
