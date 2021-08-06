package no.kristiania.movie.site.backend.service;

import no.kristiania.movie.site.backend.StubApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = BEFORE_CLASS)
public class DefaultDataInitializerServiceTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @Test
    public void testInit() {
        assertTrue(reviewService.getAllReviews().size() > 0);

        assertTrue(movieService.getAllMovies(true).stream()
                .mapToLong(t -> t.getAllReviews().size())
                .sum() > 0);
        assertTrue(movieService.getAllMovies(false).size() > 0);
    }
}
