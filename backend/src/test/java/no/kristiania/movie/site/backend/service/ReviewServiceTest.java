package no.kristiania.movie.site.backend.service;

import no.kristiania.movie.site.backend.StubApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ReviewServiceTest extends ServiceTestBase{

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Test
    public void createReview(){
        userService.createUser("Foo", "Foo", "Bar", "foo.bar@kristiania.no", "123");
        Long movieId = movieService.createMovie("Foo", "Testing for fun", 20201);
        Long reviewId = reviewService.createReview(movieId, "Testing review text", "Foo");
        assertNotNull(reviewId);
    }
}
