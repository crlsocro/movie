package no.kristiania.movie.site.backend.service;

import no.kristiania.movie.site.backend.entity.Movie;
import no.kristiania.movie.site.backend.entity.Review;
import no.kristiania.movie.site.backend.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
@Transactional
public class ResetService {

    @PersistenceContext
    private EntityManager em;

    public void resetDatabase(){
        //Have to use native SQL for ElementCollection
        Query query = em.createNativeQuery("delete from user_roles");
        query.executeUpdate();

        deleteEntities(Movie.class);
        deleteEntities(Review.class);
        deleteEntities(User.class);

    }

    private void deleteEntities(Class<?> entity){

        if(entity == null || entity.getAnnotation(Entity.class) == null){
            throw new IllegalArgumentException("Invalid non-entity class");
        }

        String name = entity.getSimpleName();
        Query query = em.createQuery("delete from " + name);
        query.executeUpdate();
    }

}