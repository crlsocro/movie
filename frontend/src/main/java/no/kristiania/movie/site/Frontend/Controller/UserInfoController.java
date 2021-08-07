package no.kristiania.movie.site.Frontend.Controller;

import no.kristiania.movie.site.backend.entity.User;
import no.kristiania.movie.site.backend.service.ReviewService;
import no.kristiania.movie.site.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class UserInfoController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    public String getUserName(){
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

   /* public User getUser() {
        return userService.findUserByUserName(getUserName());
    }

    public List<Purchase> listPurchases() {
        return purchaseService.filterPurchasesByUser(getUserName());
    } */
}
