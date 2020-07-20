CREATE TABLE expense
(
    id           bigint(20) NOT NULL AUTO_INCREMENT,
    date         date       NOT NULL,
    amount       double     NOT NULL,
    paid_by_userid bigint(20)     NOT NULL ,
    description  varchar(3000),
    is_paid       boolean    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (paid_by_userid) REFERENCES user(id),
    UNIQUE KEY name (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;