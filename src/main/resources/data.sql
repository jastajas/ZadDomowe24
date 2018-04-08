INSERT INTO housing_cooperative (idhc, adress_code, adress_no, adress_street, city, name) VALUES (1, '55-095', '12a', 'Grunwaldzka', 'WROCLAW', 'Eureka');
INSERT INTO housing_cooperative (idhc, adress_code, adress_no, adress_street, city, name) VALUES (2, '66-295', '5', 'Matejki', 'WARSZAWA', 'Cztery Pory Roku');
INSERT INTO housing_cooperative (idhc, adress_code, adress_no, adress_street, city, name) VALUES (3, '43-323', '44', 'Piękna', 'POZNAN', 'Mała Wenecja');

INSERT INTO flat (idf, flat_area, flat_number, residents_number, housing_cooperative_idhc) VALUES (1, 55.2, '23', 3, 1);
INSERT INTO flat (idf, flat_area, flat_number, residents_number, housing_cooperative_idhc) VALUES (2, 65.3, '12a', 1, 1);
INSERT INTO flat (idf, flat_area, flat_number, residents_number, housing_cooperative_idhc) VALUES (3, 120.5, '5', 2, 2);
INSERT INTO flat (idf, flat_area, flat_number, residents_number, housing_cooperative_idhc) VALUES (4, 88.4, '21c', 1, 3);
INSERT INTO flat (idf, flat_area, flat_number, residents_number, housing_cooperative_idhc) VALUES (5, 56.6, '9', 1, 3);

INSERT INTO resident (idr, name, sex, surname, flat_idf) VALUES (1, 'Jan', 'MALE', 'Kowalski', 1);
INSERT INTO resident (idr, name, sex, surname, flat_idf) VALUES (2, 'Ewa', 'FEMALE', 'Kowalska', 1);
INSERT INTO resident (idr, name, sex, surname, flat_idf) VALUES (3, 'Basia', 'FEMALE', 'Kowalska', 1);
INSERT INTO resident (idr, name, sex, surname, flat_idf) VALUES (4, 'Kasia', 'FEMALE', 'Zięba', 2);
INSERT INTO resident (idr, name, sex, surname, flat_idf) VALUES (5, 'Zosia', 'FEMALE', 'Nowak', 3);
INSERT INTO resident (idr, name, sex, surname, flat_idf) VALUES (6, 'Krzysiek', 'MALE', 'Abacki', 3);
INSERT INTO resident (idr, name, sex, surname, flat_idf) VALUES (7, 'Edek', 'MALE', 'Kowal', 4);
INSERT INTO resident (idr, name, sex, surname, flat_idf) VALUES (8, 'Ola', 'FEMALE', 'Zgredek', 5);