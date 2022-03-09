DROP TABLE IF EXISTS contact;

CREATE TABLE CONTACT (
    ID bigserial primary key,
    FULLNAME varchar(255),
    TELNUMBER varchar (255),
    EMAIL varchar (255)
);

INSERT INTO CONTACT(ID, FULLNAME, TELNUMBER, EMAIL)
VALUES (1000, 'Ivan Ivanov', '1234567', 'iivanov@gmail.com');

INSERT INTO CONTACT(ID, FULLNAME, TELNUMBER, EMAIL)
VALUES (2000, 'Maria Ivanova', '7654321', 'mivanova@gmail.com');