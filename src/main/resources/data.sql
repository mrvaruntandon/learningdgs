CREATE TABLE IF NOT EXISTS Actor (
  actor_Id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Show (
  show_Id INT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  release_Year INT,
  lead_Actor_Id INT,
  FOREIGN KEY (lead_Actor_Id) REFERENCES Actor(actor_Id)
);

INSERT INTO Actor (actor_Id, name)
VALUES (1, 'Shah Rukh Khan');
INSERT INTO Actor (actor_Id, name)
VALUES (2, 'Salman Khan');
INSERT INTO Actor (actor_Id, name)
VALUES (3, 'Aamir Khan');

INSERT INTO Show (show_Id, title, release_Year, lead_Actor_Id)
VALUES (1, '3 Idiots', 2009, 3);
INSERT INTO Show (show_Id, title, release_Year, lead_Actor_Id)
VALUES (2, 'My Name Is Khan', 2010, 1);
INSERT INTO Show (show_Id, title, release_Year, lead_Actor_Id)
VALUES (3, 'Ready', 2011, 2);
