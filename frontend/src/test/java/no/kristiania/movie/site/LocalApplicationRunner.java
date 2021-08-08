package no.kristiania.movie.site;


import org.springframework.boot.SpringApplication;

import javax.faces.application.Application;

public class LocalApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, "--spring.profiles.active=test");
    }
}