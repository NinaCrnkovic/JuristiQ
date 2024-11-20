INSERT INTO LAW_FIRM (oib, name, address, email, phone) VALUES
                                                            ('12345678901', 'Pravda d.o.o.', 'Ulica slobode 15, Zagreb', 'info@pravda.hr', '0912345678'),
                                                            ('23456789012', 'Zakon i Pravo d.o.o.', 'Avenija Hrvatske bratske zajednice 10, Split', 'kontakt@zakonipravo.hr', '0998765432'),
                                                            ('34567890123', 'Legalitet j.d.o.o.', 'Petrinjska ulica 8, Rijeka', 'info@legalitet.hr', '0923456789'),
                                                            ('45678901234', 'Pravni savjeti d.o.o.', 'Obala kralja Petra Krešimira IV. 22, Zadar', 'savjeti@pravnisavjeti.hr', '0987654321'),
                                                            ('56789012345', 'Odvjetnički ured Nova pravda', 'Glavna ulica 5, Osijek', 'ured@novapravda.hr', '0956781234');


INSERT INTO LAWYER (name, surname, oib, email, phone, law_firm_id) VALUES
-- Pravda d.o.o.
('Ivan', 'Horvat', '12345678910', 'ivan.horvat@pravda.hr', '0911111111', 1),
('Ana', 'Kovačić', '12345678911', 'ana.kovacic@pravda.hr', '0921111111', 1),

-- Zakon i Pravo d.o.o.
('Petar', 'Novak', '23456789013', 'petar.novak@zakonipravo.hr', '0912222222', 2),

-- Legalitet j.d.o.o.
('Lucija', 'Matić', '34567890124', 'lucija.matic@legalitet.hr', '0923333333', 3),
('Marko', 'Rendić', '34567890125', 'marko.rendic@legalitet.hr', '0933333333', 3),
('Tanja', 'Kralj', '34567890126', 'tanja.kralj@legalitet.hr', '0943333333', 3),

-- Pravni savjeti d.o.o.
('Iva', 'Barišić', '45678901235', 'iva.barisic@pravnisavjeti.hr', '0914444444', 4),

-- Odvjetnički ured Nova pravda
('Davor', 'Lončar', '56789012346', 'davor.loncar@novapravda.hr', '0915555555', 5),
('Marina', 'Jurić', '56789012347', 'marina.juric@novapravda.hr', '0925555555', 5);


INSERT INTO CLIENT (name, surname, oib, address, email, phone_number, represented, lawyer_id) VALUES
                                                                                                  ('Ivan', 'Horvat', '12345678901', 'Zagrebačka ulica 15, Zagreb', 'ivan.horvat@example.com', '0911111111', TRUE, 1),
                                                                                                  ('Ana', 'Kovačić', '23456789012', 'Splitska ulica 10, Split', 'ana.kovacic@example.com', '0922222222', TRUE, 2),
                                                                                                  ('Marko', 'Matić', '34567890123', 'Riječka ulica 8, Rijeka', 'marko.matic@example.com', '0933333333', TRUE, 3),
                                                                                                  ('Lucija', 'Novak', '45678901234', 'Zadarska ulica 22, Zadar', 'lucija.novak@example.com', '0944444444', FALSE, NULL),
                                                                                                  ('Petra', 'Ivić', '56789012345', 'Osječka ulica 5, Osijek', 'petra.ivic@example.com', '0955555555', TRUE, 4);

INSERT INTO CLIENT (name, surname, oib, address, email, phone_number, represented, lawyer_id) VALUES
                                                                                                  ('Hrvoje', 'Šarić', '67890123456', 'Vukovarska ulica 12, Vukovar', 'hrvoje.saric@example.com', '0966666666', FALSE, NULL),
                                                                                                  ('Maja', 'Pavlović', '78901234567', 'Dubrovnička ulica 33, Dubrovnik', 'maja.pavlovic@example.com', '0977777777', TRUE, 5),
                                                                                                  ('Tomislav', 'Barišić', '89012345678', 'Karlovačka ulica 14, Karlovac', 'tomislav.barisic@example.com', '0988888888', FALSE, NULL),
                                                                                                  ('Nikolina', 'Grgić', '90123456789', 'Zagrebačka avenija 50, Zagreb', 'nikolina.grgic@example.com', '0999999999', TRUE, 1),
                                                                                                  ('Filip', 'Vuković', '01234567890', 'Istarska ulica 21, Pula', 'filip.vukovic@example.com', '0921231231', TRUE, 2);


INSERT INTO LITIGATION_CASE (id, designation, court, judge, vps) VALUES
                                                                     (1, 'P-22/2018', 'OPĆINSKI_SUD_U_OSIJEKU', 'Ana Sudac', 200.0),
                                                                     (2, 'P-33/2020', 'ŽUPANIJSKI_SUD_U_SPLITU', 'Marko Pravdić', 1500.0),
                                                                     (3, 'P-44/2021', 'TRGOVAČKI_SUD_U_ZAGREBU', 'Lucija Petković', 750.0),
                                                                     (4, 'P-55/2022', 'TRGOVAČKI_SUD_U_ZAGREBU', 'Davor Perić', 300.0),
                                                                     (5, 'P-66/2023', 'OPĆINSKI_SUD_U_DUBROVNIKU', 'Ivana Kovačić', 500.0);
/*
-- Associate Plaintiffs (Represented Parties)
INSERT INTO litigation_represented (case_id, client_id) VALUES
                                                            (1, 1), -- Case 1: Ivan Horvat
                                                            (2, 2), -- Case 1: Ana Kovačić
                                                            (3, 3), -- Case 2: Marko Matić
                                                            (4, 4), -- Case 3: Lucija Novak
                                                            (5, 2); -- Case 4: Petra Ivić

-- Associate Defendants (Opposing Parties)
INSERT INTO litigation_opposing (case_id, client_id) VALUES
                                                         (1, 6), -- Case 1: Hrvoje Šarić
                                                         (2, 7), -- Case 2: Maja Pavlović
                                                         (3, 8), -- Case 3: Tomislav Barišić
                                                         (4, 9), -- Case 4: Nikolina Grgić
                                                         (5, 5); -- Case 5: Filip Vuković

*/
