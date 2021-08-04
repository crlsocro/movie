package no.kristiania.movie.site.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

@Service
public class DefaultDataInitializerService {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initialize(){
        String userOne = "Ola";
        String userTwo = "kari";
        String userThree = "Donald";

        attempt(() -> {
            return userService.createUser("ola123", userOne, "Nordmann", "ola.nordmann@kristiania.no", "123");
        });
        attempt(() -> {
            return userService.createUser("kari123", userTwo, "Nordmann", "kari.nordmann@kristiania.no", "123");
        });
        attempt(() -> {
            return userService.createUser("trump23", userThree, "Trump", "donald.trump@usa.com", "123");
        });


    }

    private  <T> T attempt(Supplier<T> lambda){
        try{
            return lambda.get();
        }catch (Exception e){
            return null;
        }
    }
}
