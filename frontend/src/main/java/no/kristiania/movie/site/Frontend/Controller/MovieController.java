package no.kristiania.movie.site.Frontend.Controller;


import no.kristiania.movie.site.backend.entity.Movie;
import no.kristiania.movie.site.backend.entity.Review;
import no.kristiania.movie.site.backend.service.MovieService;
import no.kristiania.movie.site.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class MovieController implements Serializable {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    private boolean hasReviews = false;
    private Long movieId;
    private Movie selectedMovie;

    private List<Review> Reviews = new ArrayList<>();
    private String reviewText = "";

    public List<Movie> getMovies(int numberOfMovies){
        return movieService.getAllMovies(true).stream().limit(numberOfMovies).collect(Collectors.toList());
    }

    public String selectMovie(Movie movie){

        selectedMovie = movie;
        if(selectedMovie!= null){
            hasReviews = !Reviews.isEmpty();
        }
        return "/movie.jsf?faces-redirect=true";
    }

    public Movie getSelectedMovie() {
        return selectedMovie;
    }

    public String getMovieRedirectLink(Long movieId){
        this.movieId = movieId;
        return "/details.jsf?tripID=" + movieId + "&faces-redirect=true";
    }
    public Movie getMovie(Long id){
        return movieService.getMovie(id, true);
    }
    public String makeReview(String username, String reviewText){
        if(isNotMadeAreview(movieId, username)){
            reviewService.createReview(movieId, reviewText,username);
            return "details?movieId=" + movieId + "&isPurchased=true&faces-redirect=true";
        }else{
            return "details?movieId=" + movieId + "&isPurchased=false&faces-redirect=true";
        }
    }

    private boolean isNotMadeAreview(Long movieId, String username) {
        return false;
    }


}
