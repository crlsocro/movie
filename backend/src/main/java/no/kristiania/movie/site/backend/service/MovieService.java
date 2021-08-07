package no.kristiania.movie.site.backend.service;

import no.kristiania.movie.site.backend.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@Transactional
public class MovieService {
    @Autowired
    private EntityManager em;

    public Long createMovie(
            String title,
            String directorName,
            Integer yearOfRelease
    ){
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDirectorName(directorName);
        movie.setYearOfRelease(yearOfRelease);
        em.persist(movie);
        return movie.getId();
    }

    public Movie getMovie(Long movieId,boolean withReviews){
        Movie movie = em.find(Movie.class, movieId);

        if(movie == null){
            throw new IllegalStateException("Movie not found in database");
        }
        if(withReviews){
            movie.getAllReviews().size();
        }
        return movie;
    }

    public void deleteMovie(Long movieId){
        Movie movieRemove = em.find(Movie.class, movieId);

        if(movieRemove == null){
            throw new IllegalStateException("No movie found in database");
        }
        em.remove(movieRemove);
    }
    public List<Movie> getAllMovies(boolean withReviews){
        TypedQuery<Movie> query = em.createQuery("select m from Movie m", Movie.class);

        List<Movie> allMovies = query.getResultList();
        if(withReviews){
            allMovies.forEach(u -> u.getAllReviews().size());
        }
        return allMovies;
    }
    public List<Movie> filterByDirectorName(String directorName){
        TypedQuery<Movie> query = em.createQuery("select m from Movie m where m.directorName = ?1 order by m.yearOfRelease ASC", Movie.class);
        query.setParameter(1, directorName);
        return query.getResultList();
    }
}
