CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user(name) VALUES ('Bogdan'), ('Michael'), ('Valera'), ('Nastya');