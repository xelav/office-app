
INSERT INTO tree_element (id, name, address, element_type) VALUES
 (1,'Black mesa', 'New Mexico, USA', 'office'),
 (2,'Aperture Science', 'Upper Michigan, USA', 'office'),
 (3,'VersaLife', '', 'office'),
 (4,'Sarif Industries', 'Detroit', 'office'),
 (5,'Vault-Tec Industries', '', 'office');


INSERT INTO tree_element (id, director_name, name, parent_id, element_type) VALUES
  (6, '', 'Anomalous Materials department', 1, 'subdivision'),
  (7, '', 'Some other department', 1, 'subdivision'),
  (8, '', 'Aperture Laboratories', 2, 'subdivision'),
  (9, '', 'Another one', 7, 'subdivision');


INSERT INTO worker (id, birth_date, email, name, phone_number, subdivision_id) VALUES
  (1, '1970-01-01', 'somemail@mail.com', 'Gordon Freeman', '1', 6),
  (2, '1970-01-01', 'somemail1@mail.com', 'Scientist #1', '2', 6),
  (3, '1970-01-07', 'somemail2@mail.com', 'Scientist #2', '3', 6),
  (4, '1970-01-09', 'somemail3@mail.com', 'Scientist #3', '4', 6),
  (5, '1970-01-10', 'somemail4@mail.com', 'Scientist #4', '5', 6),
  (6, '1970-01-15', 'somemail5@mail.com', 'Scientist #5', '6', 6),
  (7, '1970-01-16', 'somemail6@mail.com', 'Scientist #6', '7', 6),
  (8, '1970-01-17', 'somemail7@mail.com', 'Scientist #7', '8', 6),
  (9, '1970-01-18', 'somemail8@mail.com', 'Scientist #8', '9', 6),
  (10, '1970-01-19', 'somemail9@mail.com', 'Scientist #9', '10', 6),
  (11, '1970-01-20', 'somemail10@mail.com', 'Scientist #10', '11', 6),
  (12, '1970-01-21', 'somemail11@mail.com', 'Scientist #11', '12', 6),
  (13, '1970-01-22', 'somemail12@mail.com', 'Scientist #12', '13', 6),
  (14, '1970-01-23', 'somemail13@mail.com', 'Scientist #13', '14', 6);
