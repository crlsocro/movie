package no.kristiania.movie.site.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

@Service
public class DefaultDataInitializerService {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @PostConstruct
    public void initialize(){
        String userName1 = "ola123";
        String userName2 = "Kari900";
        String userName3 = "Musk001";

        attempt(() -> {
            return userService.createUser(userName1, "Ola", "Nordmann", "ola.nordmann@kristiania.no", "123");
        });
        attempt(() -> {
            return userService.createUser(userName2, "Kari", "Nordmann", "kari.nordmann@kristiania.no", "123");
        });
        attempt(() -> {
            return userService.createUser(userName3, "Elon", "Musk", "elon.musk@spacex.com", "456");
        });

        Long Movie1 = attempt(() -> movieService.createMovie("The Shawshank Redemption", "Frank Darabont", 1994));
        Long Movie2 = attempt(() -> movieService.createMovie("The Godfather", "Francis Ford Coppola", 1972));
        Long Movie3 = attempt(() -> movieService.createMovie("The Dark Knight", "Christopher Nolan", 2008));
        Long Movie4 = attempt(() -> movieService.createMovie("Pulp Fiction", "Quentin Tarantino", 1994));
        Long Movie5 = attempt(() -> movieService.createMovie("Inception", "Christopher Nolan", 2010));

        attempt(() -> reviewService.createReview(Movie1, "One of the best",userName1));
        attempt(() -> reviewService.createReview(Movie1, "Very good movie",userName2));
        attempt(() -> reviewService.createReview(Movie3, "One of the best",userName3));
        attempt(() -> reviewService.createReview(Movie2, "Not so good",userName1));
        attempt(() -> reviewService.createReview(Movie2, "Boring",userName2));
        attempt(() -> reviewService.createReview(Movie5, "I liked this movie",userName3));
        attempt(() -> reviewService.createReview(Movie4, "Very fun",userName1));

    }

    private  <T> T attempt(Supplier<T> lambda){
        try{
            return lambda.get();
        }catch (Exception e){
            return null;
        }
    }
}
