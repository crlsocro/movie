create sequence hibernate_sequence start with 1 increment by 1;

CREATE TABLE users(
    username varchar(255) not null,
    name varchar(255) not null,
    surname varchar(255) not null,
    email varchar(300) not null,
    password varchar(255) not null,
    enabled boolean not null,
    primary key (username)
);

CREATE TABLE movies(
    id bigint not null,
    title varchar(255) not null,
    director_name varchar(255) not null,
    year_of_release date not null,
    primary key(id)
);
CREATE TABLE reviews(
    id bigint not null,
    review varchar(3000),
    review_created date not null,
    movie_information_id bigint not null,
    author varchar(255) not null,
    rating integer not null check (rating <= 5 AND rating >= 1),
    primary key(id)
);