CREATE TABLE user
(
    id   bigint(20)   NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY name (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE revenue
(
    id                             bigint(20) NOT NULL AUTO_INCREMENT,
    date                           date       NOT NULL,
    square                         double     NOT NULL,
    price_per_hectare                double     NOT NULL,
    combiner_driver_payment_hectare double     NOT NULL,
    comment                        varchar(2000),
    PRIMARY KEY (id),
    UNIQUE KEY name (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO user(name)
VALUES ('Bogdan'),
       ('Michael'),
       ('Valera'),
       ('Nastya');