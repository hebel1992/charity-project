INSERT INTO institutions (description, name, city, phone_number) VALUES ('"Pomoc dzieciom z ubogich rodzin."' ,'"Dbam o Zdrowie"', 'Warszawa', '764123890');
INSERT INTO institutions (description, name, city, phone_number) VALUES ('"Pomoc wybudzaniu dzieci ze śpiączki."' ,'"A kogo"', 'Wrocław', '235283902');
INSERT INTO institutions (description, name, city, phone_number) VALUES ('"Pomoc osobom znajdującym się w trudnej sytuacji życiowej."' ,'"Dla dzieci"', 'Kraków', '159345019');
INSERT INTO institutions (description, name, city, phone_number) VALUES ('"Pomoc dla osób nie posiadających miejsca zamieszkania"' ,'"Bez domu"', 'Poznań', '578098123');

INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, phone_number) VALUES ('Wrocław', '2020-03-14', '15:30', 3, 'ul. Mala 3', '32-500', 1, '324678421');
INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, pick_up_comment, phone_number) VALUES ('Poznań', '2020-03-16', '15:45', 5, 'ul. Duza 4', '78-300', 2, 'Komentarz do odbioru', '3562453422');
INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, pick_up_comment, phone_number) VALUES ('Gdańsk', '2020-03-18', '12:45', 12, 'ul. Tęczowa 12b', '99-103', 3, 'Komentarz do odbioru', '434352342');
INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, phone_number) VALUES ('Warszawa', '2020-04-20', '11:30', 4, 'ul. Kamienna 1c', '11-020', 4, '354123124');

INSERT INTO categories (name) VALUES ('Odzież');
INSERT INTO categories (name) VALUES ('Buty');
INSERT INTO categories (name) VALUES ('Zabawki');
INSERT INTO categories (name) VALUES ('Książki');
INSERT INTO categories (name) VALUES ('Elektronika');
INSERT INTO categories (name) VALUES ('Inne');

INSERT INTO donations_categories (donation_id, category_id) VALUES (1, 1);
INSERT INTO donations_categories (donation_id, category_id) VALUES (1, 2);
INSERT INTO donations_categories (donation_id, category_id) VALUES (2, 3);
INSERT INTO donations_categories (donation_id, category_id) VALUES (3, 3);
INSERT INTO donations_categories (donation_id, category_id) VALUES (3, 4);
INSERT INTO donations_categories (donation_id, category_id) VALUES (4, 1);
INSERT INTO donations_categories (donation_id, category_id) VALUES (4, 5);

INSERT INTO role (id, name, description) VALUES (1, 'ROLE_ADMIN', 'Admin');
INSERT INTO role (id, name, description) VALUES (2, 'ROLE_USER', 'User');

INSERT INTO user (email, enabled, first_name, last_name, password, username) VALUES ('b.kowal@wp.pl', 1, 'Bartosz', 'Kowalski', '$2a$10$vDXXSvQzoHKDvPwcP4cQt.IIA.GAA0raTAWPLMBrZ3/CfVW0vr4oC', 'testuser');
INSERT INTO user_role (user_id, role_id) VALUES (1,2);

INSERT INTO user (email, enabled, first_name, last_name, password, username) VALUES ('m.nowak@o2.pl', 1, 'Marek', 'Nowak', '$2a$10$ssLUf7WmudX9ZT9jzSBrUuS4jt/zXimoqDfqYI2S8PUiVOOlW1U16', 'testadmin');
INSERT INTO user_role (user_id, role_id) VALUES (2,1);
INSERT INTO user_role (user_id, role_id) VALUES (2,2);


