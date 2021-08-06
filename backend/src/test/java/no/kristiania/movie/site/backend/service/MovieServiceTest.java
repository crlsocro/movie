package no.kristiania.movie.site.backend.service;


import no.kristiania.movie.site.backend.StubApplication;
import no.kristiania.movie.site.backend.entity.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MovieServiceTest extends ServiceTestBase{

    @Autowired
    private MovieService movieService;

    @Test
    public void testCreateMovie(){
        Long id = movieService.createMovie("Test title", "director testing", 2012);
        assertNotNull(id);
    }
    @Test
    public void testDeleteMovie(){
        Long id = movieService.createMovie("Test title", "director testing", 2012);
        assertNotNull(id);
        movieService.deleteMovie(id);
    }
    @Test
    public void testGetAllMovies(){
        Long firstMovie = movieService.createMovie("Test title", "director testing", 2012);
        Long secondMovie = movieService.createMovie("Testing movie", "testing 123", 2005);
        assertNotNull(firstMovie);
        assertNotNull(secondMovie);

        List<Movie> allMovies = movieService.getAllMovies(false);
        assertEquals(2, allMovies.size());
    }
    @Test
    public void filterByYearReleased(){
        Long firstMovie = movieService.createMovie("Test title", "director testing", 2012);
        Long secondMovie = movieService.createMovie("Testing movie", "Nolan", 2005);
        Long thirdMovie = movieService.createMovie("Samurai", "Nolan", 2205);
        assertNotNull(firstMovie);
        assertNotNull(secondMovie);
        assertNotNull(thirdMovie);

        List<Movie> earliest = movieService.filterByDirectorName("director testing");
        List<Movie> latest = movieService.filterByDirectorName("Nolan");

        assertEquals(1, earliest.size());
        assertEquals(2, latest.size());
    }
}
