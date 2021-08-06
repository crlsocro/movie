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

CREATE TABLE movie(
    id bigint not null,
    title varchar(255) not null,
    director_name varchar(255) not null,
    year_of_release integer not null,
    primary key(id)
);
CREATE TABLE review(
    id bigint not null,
    review_text varchar(3000),
    review_created date not null,
    movie_information_id bigint not null,
    author varchar(255) not null,
    author_username varchar(255),
    rating integer not null check (rating <= 5 AND rating >= 1),
    primary key(id)
);
create table users_reviews_by_users(
    all_reviews_username varchar(255) not null,
    reviews_by_users_id bigint not null
);
create table user_roles (
    user_username varchar(255) not null,
    roles varchar(255)
);
alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table review add constraint FKex4wa959mkii4fwx3s210ug24 foreign key (author) references users;
alter table review add constraint FK3tv91lsph9bbpk4lpdu231yn0 foreign key (movie_information_id) references movie;
alter table users_reviews_by_users add constraint FKqsn5ohuprxkk1s7cd348e8btl foreign key (reviews_by_users_id) references movie;
alter table users_reviews_by_users add constraint FK2d6a73d4l6qaq1th7ni5po6rj foreign key (all_reviews_username) references users;
alter table user_roles add constraint FKnqgxij5udu4xrsqju9dtbc8pr foreign key (user_username) references users;