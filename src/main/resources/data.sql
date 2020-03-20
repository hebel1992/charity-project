INSERT INTO user (id, email, enabled, first_name, last_name, password, username, blocked) VALUES (1, 'b.kowal@wp.pl', 1, 'Bartosz', 'Kowalski', '$2a$10$vDXXSvQzoHKDvPwcP4cQt.IIA.GAA0raTAWPLMBrZ3/CfVW0vr4oC', 'testuser', false);
INSERT INTO user (id, email, enabled, first_name, last_name, password, username, blocked) VALUES (2, 'm.halas@o2.pl', 1, 'Michał', 'Hałaś', '$2a$10$h8DEAw7CVSellRjD7JwsBeBawdmcnveaVcfThbQ0Li1UZFA6/NQQi', 'testuser1', false);
INSERT INTO user (id, email, enabled, first_name, last_name, password, username, blocked) VALUES (3, 'g.mazur@gmail.com', 1, 'Grzegorz', 'Mazur', '$2a$10$pmwgKP4.p5ha80BFzH2Nk.r92oNDsN/s0/5uTX.Uo6rAPSEkMk6DO', 'testuser2', false);
INSERT INTO user (id, email, enabled, first_name, last_name, password, username, blocked) VALUES (4, 'n.krych@onet.pl', 1, 'Natalia', 'Krychowiak', '$2a$10$xiiBJgFIM8VURK5HNBUAnOr.2QLT7ex0GiKOXjPBM6OLuvN7FO1pC', 'testuser3', true);
INSERT INTO user (id, email, enabled, first_name, last_name, password, username, blocked) VALUES (5, 'e.korwin@onet.pl', 1, 'Emil', 'Korwin', '$2a$10$h8DEAw7CVSellRjD7JwsBeBawdmcnveaVcfThbQ0Li1UZFA6/NQQi', 'testuser4', false);

INSERT INTO role (id, name, description) VALUES (1, 'ROLE_ADMIN', 'Admin');
INSERT INTO role (id, name, description) VALUES (2, 'ROLE_USER', 'User');

INSERT INTO user_role (user_id, role_id) VALUES (1,2);
INSERT INTO user_role (user_id, role_id) VALUES (2,2);
INSERT INTO user_role (user_id, role_id) VALUES (3,2);
INSERT INTO user_role (user_id, role_id) VALUES (5,2);

INSERT INTO user (id, email, enabled, first_name, last_name, password, username, blocked) VALUES (6, 'i.sowa@o2.pl', 1, 'Ilona', 'Sowa', '$2a$10$ssLUf7WmudX9ZT9jzSBrUuS4jt/zXimoqDfqYI2S8PUiVOOlW1U16', 'testadmin', false);
INSERT INTO user (id, email, enabled, first_name, last_name, password, username, blocked) VALUES (7, 'm.gora@wp.pl', 1, 'Marta', 'Góra', '$2a$10$oLSTrxC5pP3tCryj3PazPeXao.7iFfVSsihzH5b3SdaA9WBz03lte', 'testadmin1', false);
INSERT INTO user (id, email, enabled, first_name, last_name, password, username, blocked) VALUES (8, 's.kopacz@onet.pl', 1, 'Szymon', 'Kopacz', '$2a$10$wyteC2CCTVYZFg87XIvKPetmVM2nhmYMjUtgThkfiEtlyggxpX6.a', 'testadmin2', false);
INSERT INTO user_role (user_id, role_id) VALUES (6,1);
INSERT INTO user_role (user_id, role_id) VALUES (7,1);
INSERT INTO user_role (user_id, role_id) VALUES (8,1);

INSERT INTO institutions (id, description, name, city, phone_number) VALUES (1, '"Pomoc dzieciom z ubogich rodzin."' ,'"Dbam o Zdrowie"', 'Warszawa', '764123890');
INSERT INTO institutions (id, description, name, city, phone_number) VALUES (2, '"Pomoc w wybudzaniu dzieci ze śpiączki."' ,'"A kogo"', 'Wrocław', '235283902');
INSERT INTO institutions (id, description, name, city, phone_number) VALUES (3, '"Pomoc osobom znajdującym się w trudnej sytuacji życiowej."' ,'"Dla dzieci"', 'Kraków', '159345019');
INSERT INTO institutions (id, description, name, city, phone_number) VALUES (4, '"Pomoc dla osób nie posiadających miejsca zamieszkania"' ,'"Bez domu"', 'Poznań', '578098123');
INSERT INTO institutions (id, description, name, city, phone_number) VALUES (5, '"Pomoc dzieciom z ciężkimi chorobami."' ,'"Małym dzieciom"', 'Rzeszów', '435312341');
INSERT INTO institutions (id, description, name, city, phone_number) VALUES (6, '"Pomoc ludziom bezdomnym."' ,'"Dla Biednych"', 'Krotoszyn', '654634523');
INSERT INTO institutions (id, description, name, city, phone_number) VALUES (7, '"Pomoc porzuconym dzieciom."' ,'"Pajacyk"', 'Opole', '6343523532');
INSERT INTO institutions (id, description, name, city, phone_number) VALUES (8, '"Pomoc osobom starszym"' ,'"Odwet dla Seniora"', 'Kielce', '1231543564');

INSERT INTO donations (id, status, created, city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, phone_number, user_id) VALUES (1, 1, DATE_ADD(NOW(),INTERVAL -3 DAY), 'Wrocław', DATE_ADD(NOW(),INTERVAL +1 DAY), '15:30', 3, 'ul. Mala 3', '32-500', 1, '324678421', 1);
INSERT INTO donations (id, status, actual_pick_up_date,created, city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, phone_number, user_id) VALUES (9, 0, DATE_ADD(NOW(),INTERVAL -6 DAY), DATE_ADD(NOW(),INTERVAL -12 DAY), 'Wrocław', DATE_ADD(NOW(),INTERVAL -6 DAY), '15:30', 3, 'ul. Mala 3', '32-500', 1, '324678421', 1);
INSERT INTO donations (id, status, created, city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, pick_up_comment, phone_number, user_id) VALUES (2, 1, DATE_ADD(NOW(),INTERVAL -4 DAY), 'Poznań', DATE_ADD(NOW(),INTERVAL +2 DAY), '15:45', 5, 'ul. Duza 4', '78-300', 2, 'Komentarz do odbioru', '3562453422', 2);
INSERT INTO donations (id, status, created, city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, pick_up_comment, phone_number, user_id) VALUES (3, 1, DATE_ADD(NOW(),INTERVAL -5 DAY), 'Gdańsk', DATE_ADD(NOW(),INTERVAL +9 DAY), '12:45', 12, 'ul. Tęczowa 12b', '99-103', 3, 'Komentarz do odbioru', '434352342', 2);
INSERT INTO donations (id, status, created, city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, phone_number, user_id) VALUES (4, 1, DATE_ADD(NOW(),INTERVAL -1 DAY), 'Warszawa', DATE_ADD(NOW(),INTERVAL +9 DAY), '11:30', 10, 'ul. Kamienna 1c', '11-020', 4, '35412312', 3);
INSERT INTO donations (id, status, created, city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, phone_number, user_id) VALUES (5, 1, DATE_ADD(NOW(),INTERVAL -7 DAY), 'Szczecin', DATE_ADD(NOW(),INTERVAL +5 DAY), '19:30', 7, 'ul. Miodowa 88', '11-321', 5, '345242342', 1);
INSERT INTO donations (id, status, actual_pick_up_date, created, city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, pick_up_comment, phone_number, user_id) VALUES (6, 0, DATE_ADD(NOW(),INTERVAL -4 DAY), DATE_ADD(NOW(),INTERVAL -8 DAY), 'Kielca', DATE_ADD(NOW(),INTERVAL +3 DAY), '12:45', 5, 'ul. Szara 77', '54-432', 8, 'Komentarz do odbioru', '5475786534', 4);
INSERT INTO donations (id, status, actual_pick_up_date, created, city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, pick_up_comment, phone_number, user_id) VALUES (7, 0, DATE_ADD(NOW(),INTERVAL -5 DAY), DATE_ADD(NOW(),INTERVAL -10 DAY), 'Białystok', DATE_ADD(NOW(),INTERVAL +10 DAY), '19:20', 3, 'ul. Fioletowa 128', '43-123', 7, 'Komentarz do odbioru', '12446353', 4);
INSERT INTO donations (id, status, created, city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id, phone_number, user_id) VALUES (8, 1, DATE_ADD(NOW(),INTERVAL -2 DAY), 'Lubin', DATE_ADD(NOW(),INTERVAL +15 DAY), '14:30', 13, 'ul. Złota 55', '64-645', 7, '344352342', 2);

INSERT INTO categories (id, name) VALUES (1, 'Odzież');
INSERT INTO categories (id, name) VALUES (2, 'Buty');
INSERT INTO categories (id, name) VALUES (3, 'Zabawki');
INSERT INTO categories (id, name) VALUES (4, 'Książki');
INSERT INTO categories (id, name) VALUES (5, 'Elektronika');
INSERT INTO categories (id, name) VALUES (6, 'Inne');

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
INSERT INTO donations_categories (donation_id, category_id) VALUES (9, 3);
INSERT INTO donations_categories (donation_id, category_id) VALUES (9, 2);






