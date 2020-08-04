CREATE TABLE debt_payments
(
    id         bigint(20) NOT NULL AUTO_INCREMENT,
    amount     double     NOT NULL,
    debtor_id  bigint(20) NOT NULL,
    expense_id bigint(20) NOT NULL,
    is_paid    boolean    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (debtor_id) REFERENCES user (id),
    FOREIGN KEY (expense_id) REFERENCES expense (id),
    UNIQUE KEY name (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;