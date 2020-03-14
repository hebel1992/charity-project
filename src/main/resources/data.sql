INSERT INTO institutions (description, name, city, phone_number) VALUES ('"Pomoc dzieciom z ubogich rodzin."' ,'"Dbam o Zdrowie"', 'Warszawa', '764123890');
INSERT INTO institutions (description, name, city, phone_number) VALUES ('"Pomoc w wybudzaniu dzieci ze śpiączki."' ,'"A kogo"', 'Wrocław', '235283902');
INSERT INTO institutions (description, name, city, phone_number) VALUES ('"Pomoc osobom znajdującym się w trudnej sytuacji życiowej."' ,'"Dla dzieci"', 'Kraków', '159345019');
INSERT INTO institutions (description, name, city, phone_number) VALUES ('"Pomoc dla osób nie posiadających miejsca zamieszkania"' ,'"Bez domu"', 'Poznań', '578098123');
INSERT INTO institutions (description, name, city, phone_number) VALUES ('"Pomoc dzieciom z ciężkimi chorobami."' ,'"Małym dzieciom"', 'Rzeszów', '435312341');
INSERT INTO institutions (description, name, city, phone_number) VALUES ('"Pomoc ludziom bezdomnym."' ,'"Dla Biednych"', 'Krotoszyn', '654634523');
INSERT INTO institutions (description, name, city, phone_number) VALUES ('"Pomoc porzuconym dzieciom."' ,'"Pajacyk"', 'Opole', '6343523532');
INSERT INTO institutions (description, name, city, phone_number) VALUES ('"Pomoc osobom starszym"' ,'"Odwet dla Seniora"', 'Kielce', '1231543564');

INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, phone_number) VALUES ('Wrocław', DATE_ADD(NOW(),INTERVAL +1 DAY), '15:30', 3, 'ul. Mala 3', '32-500', 1, '324678421');
INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, pick_up_comment, phone_number) VALUES ('Poznań', DATE_ADD(NOW(),INTERVAL +2 DAY), '15:45', 5, 'ul. Duza 4', '78-300', 2, 'Komentarz do odbioru', '3562453422');
INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, pick_up_comment, phone_number) VALUES ('Gdańsk', DATE_ADD(NOW(),INTERVAL +9 DAY), '12:45', 12, 'ul. Tęczowa 12b', '99-103', 3, 'Komentarz do odbioru', '434352342');
INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, phone_number) VALUES ('Warszawa', DATE_ADD(NOW(),INTERVAL +9 DAY), '11:30', 10, 'ul. Kamienna 1c', '11-020', 4, '35412312');
INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, phone_number) VALUES ('Szczecin', DATE_ADD(NOW(),INTERVAL +5 DAY), '19:30', 7, 'ul. Miodowa 88', '11-321', 5, '345242342');
INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, pick_up_comment, phone_number) VALUES ('Kielca', DATE_ADD(NOW(),INTERVAL +3 DAY), '12:45', 5, 'ul. Szara 77', '54-432', 8, 'Komentarz do odbioru', '5475786534');
INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, pick_up_comment, phone_number) VALUES ('Białystok', DATE_ADD(NOW(),INTERVAL +10 DAY), '19:20', 3, 'ul. Fioletowa 128', '43-123', 7, 'Komentarz do odbioru', '12446353');
INSERT INTO donations (city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, phone_number) VALUES ('Lubin', DATE_ADD(NOW(),INTERVAL +15 DAY), '14:30', 13, 'ul. Złota 55', '64-645', 7, '344352342');

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

INSERT INTO donations_categories (donation_id, category_id) VALUES (5, 5);
INSERT INTO donations_categories (donation_id, category_id) VALUES (5, 6);
INSERT INTO donations_categories (donation_id, category_id) VALUES (6, 1);
INSERT INTO donations_categories (donation_id, category_id) VALUES (6, 3);
INSERT INTO donations_categories (donation_id, category_id) VALUES (7, 2);
INSERT INTO donations_categories (donation_id, category_id) VALUES (8, 3);
INSERT INTO donations_categories (donation_id, category_id) VALUES (8, 2);

INSERT INTO role (id, name, description) VALUES (1, 'ROLE_ADMIN', 'Admin');
INSERT INTO role (id, name, description) VALUES (2, 'ROLE_USER', 'User');

INSERT INTO user (email, enabled, first_name, last_name, password, username) VALUES ('b.kowal@wp.pl', 1, 'Bartosz', 'Kowalski', '$2a$10$vDXXSvQzoHKDvPwcP4cQt.IIA.GAA0raTAWPLMBrZ3/CfVW0vr4oC', 'testuser');
INSERT INTO user_role (user_id, role_id) VALUES (1,2);

INSERT INTO user (email, enabled, first_name, last_name, password, username) VALUES ('m.nowak@o2.pl', 1, 'Marek', 'Nowak', '$2a$10$ssLUf7WmudX9ZT9jzSBrUuS4jt/zXimoqDfqYI2S8PUiVOOlW1U16', 'testadmin');
INSERT INTO user_role (user_id, role_id) VALUES (2,1);
INSERT INTO user_role (user_id, role_id) VALUES (2,2);


