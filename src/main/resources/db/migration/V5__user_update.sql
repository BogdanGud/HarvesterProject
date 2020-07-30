ALTER TABLE user
    add column is_driver boolean NOT NULL;
ALTER TABLE user
    add column is_investor boolean NOT NULL;
ALTER TABLE user
    add column is_assistant boolean NOT NULL;

update user
set user.is_driver = true
where name = 'Valera';

update user
set user.is_investor = true
where name = 'Valera'
   or name = 'Michael'
   or name = 'Bogdan';