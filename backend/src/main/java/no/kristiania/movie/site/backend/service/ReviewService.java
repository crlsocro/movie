package no.kristiania.movie.site.backend.service;

import no.kristiania.movie.site.backend.entity.Movie;
import no.kristiania.movie.site.backend.entity.Review;
import no.kristiania.movie.site.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private EntityManager em;

    public List<Review> getAllReviews(){
        TypedQuery<Review> query = em.createQuery("select r from Review r", Review.class);
        return query.getResultList();
    }

    public Long createReview(Long movieId, String reviewText, String username){
        Movie movie = em.find(Movie.class, movieId);
        User user = em.find(User.class, username);

        if(movie == null){
            throw new IllegalStateException("Movie not found");
        }
        if(user == null){
            throw new IllegalStateException("User not found");
        }
        Review review = new Review();
        review.setReviewText(reviewText);
        review.setUserName(user);
        review.setMovieInformation(movie);
        review.setReviewCreated(LocalDate.now());
        user.getReviewsByUsers().add(movie);
        em.persist(review);
        return review.getId();
    }
}
