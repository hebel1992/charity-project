INSERT INTO institutions (description, name) VALUES ('Cel i misja: Pomoc dzieciom z ubogich rodzin.' ,'"Dbam o Zdrowie"');
INSERT INTO institutions (description, name) VALUES ('Cel i misja: Pomoc wybudzaniu dzieci ze śpiączki.' ,'"A kogo"');
INSERT INTO institutions (description, name) VALUES ('Cel i misja: Pomoc osobom znajdującym się w trudnej sytuacji życiowej.' ,'"Dla dzieci"');
INSERT INTO institutions (description, name) VALUES ('Cel i misja: Pomoc dla osób nie posiadających miejsca zamieszkania' ,'"Bez domu"');

INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES ('Wrocław', '2020-03-14', '15:30', 3, 'ul. Mala 3', '32-500', 1);
INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, pick_up_comment) VALUES ('Poznań', '2020-03-16', '15:45', 5, 'ul. Duza 4', '78-300', 2, 'Komentarz do odbioru');
INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, pick_up_comment) VALUES ('Gdańsk', '2020-03-18', '12:45', 12, 'ul. Tęczowa 12b', '99-103', 3, 'Komentarz do odbioru');
INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES ('Warszawa', '2020-04-20', '11:30', 4, 'ul. Kamienna 1c', '11-020', 4);

INSERT INTO categories (name) VALUES ('Odzież');
INSERT INTO categories (name) VALUES ('Buty');
INSERT INTO categories (name) VALUES ('Zabawki');
INSERT INTO categories (name) VALUES ('Książki');
INSERT INTO categories (name) VALUES ('Elektronika');
INSERT INTO categories (name) VALUES ('Inne');

INSERT INTO donations_categories (donation_id, categories_id) VALUES (1, 1);
INSERT INTO donations_categories (donation_id, categories_id) VALUES (1, 2);
INSERT INTO donations_categories (donation_id, categories_id) VALUES (2, 3);
INSERT INTO donations_categories (donation_id, categories_id) VALUES (3, 3);
INSERT INTO donations_categories (donation_id, categories_id) VALUES (3, 4);
INSERT INTO donations_categories (donation_id, categories_id) VALUES (4, 1);
INSERT INTO donations_categories (donation_id, categories_id) VALUES (4, 5);


