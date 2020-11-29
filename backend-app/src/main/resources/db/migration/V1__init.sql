CREATE TABLE my_users
(
    id       INT NOT NULL,
    email    VARCHAR(255),
    name     VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO my_users(id, email, name, password)
VALUES ('1', 'jovan.milutinovic@boutsourcing.com', 'Jovan Milutinovic',
        '$2a$10$R/3YHrOJ5Pw7eS293CWW0OZMCVR/jF1xFPgeN7nqVkiW74Zy4V9Ky');