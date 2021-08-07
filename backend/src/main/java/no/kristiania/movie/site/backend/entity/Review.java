package no.kristiania.movie.site.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(max = 3000)
    private String reviewText;

    @OneToOne
    @NotNull
    private User userName;

    @ManyToOne
    @NotNull
    private Movie movieInformation;

    @NotNull
    private LocalDate reviewCreated;

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public User getUserName() {
        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }

    public Movie getMovieInformation() {
        return movieInformation;
    }

    public void setMovieInformation(Movie movieInformation) {
        this.movieInformation = movieInformation;
    }

    public LocalDate getReviewCreated() {
        return reviewCreated;
    }

    public void setReviewCreated(LocalDate reviewCreated) {
        this.reviewCreated = reviewCreated;
    }

}
