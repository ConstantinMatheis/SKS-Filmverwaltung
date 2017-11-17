
DELETE FROM t_actor;
DELETE FROM t_films;
DELETE FROM t_studios;
DELETE FROM film_actor;

SET DATESTYLE TO DMY;
INSERT INTO t_actor (birthday, first_name, gender, last_name) VALUES (to_date('12.12.1965','DD.MM.YYYY'), 'Ted', 'MALE', 'Ericson');

INSERT INTO t_actor (birthday, first_name, gender, last_name) VALUES (to_date('23.04.1977','DD.MM.YYYY'), 'Jack', 'MALE', 'Buttle');

INSERT INTO t_actor (birthday, first_name, gender, last_name) VALUES (to_date('11.08.1989','DD.MM.YYYY'), 'Li', 'MALE', 'Chon');

INSERT INTO t_actor (birthday, first_name, gender, last_name) VALUES (to_date('16.07.1997','DD.MM.YYYY'), 'Anna', 'FEMALE', 'Grumb');

INSERT INTO t_actor (birthday, first_name, gender, last_name) VALUES (to_date('26.10.1958','DD.MM.YYYY'), 'Mia', 'FEMALE', 'Trump');

INSERT INTO t_studios (countrycode, founded_year, headquarters, name, postcode) VALUES ('US', 1983, 'Boston', 'Boston Productions', '23432');
INSERT INTO t_studios (countrycode, founded_year, headquarters, name, postcode) VALUES ('DE', 1934, 'Berlin', 'Berlin Works', '4565');
INSERT INTO t_studios (countrycode, founded_year, headquarters, name, postcode) VALUES ('AU', 1922, 'Bregenz', 'Bregenz Pro Studios', '6532');

--muss angezeigt werden
INSERT INTO t_films (budget, description, genre, language, release_year, running_time, title, fk_studio_id) VALUES (23423000, 'Some trashy blockbuster', 'ACTION', 'english', 1999, 123, 'Britannic', 1);

--actors not set -> wird nicht angezeigt
INSERT INTO t_films (budget, description, genre, language, release_year, running_time, title, fk_studio_id) VALUES (9600000, 'Super funny comedy', 'COMEDY', 'german', 2015, 188, 'Wurst attacks', 2);

--studio not set on purpose -> wird nicht angezeigt
INSERT INTO t_films (budget, description, genre, language, release_year, running_time, title) VALUES (15615000, 'Very scary', 'HORROR', 'english', 2016, 147, 'Eat');


INSERT INTO film_actor(fk_film_id, fk_actor_id) VALUES (1, 1);
INSERT INTO film_actor(fk_film_id, fk_actor_id) VALUES (1, 2);
INSERT INTO film_actor(fk_film_id, fk_actor_id) VALUES (1, 4);

INSERT INTO film_actor(fk_film_id, fk_actor_id) VALUES (3, 2);
INSERT INTO film_actor(fk_film_id, fk_actor_id) VALUES (3, 3);


