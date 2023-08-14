
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    birthday VARCHAR(255),
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(255)
);


INSERT INTO user (first_name, last_name, email, birthday, login, password, phone) 
VALUES ('GUSTAVO', 'SANTOS', 'gustavo@example.com', '1990-01-15', 'johndoe', 'password123', '123-456-7890');
       
INSERT INTO user (first_name, last_name, email, birthday, login, password, phone) 
VALUES ('JOAO', 'SILVA', 'joao@example.com', '1985-05-20', 'janesmith', 'letmein', '987-654-3210');

INSERT INTO user (first_name, last_name, email, birthday, login, password, phone) 
VALUES ('MARIA', 'CONCEICAO', 'maria@example.com', '1985-05-20', 'janesmith', 'letmein', '987-654-3210');

INSERT INTO user (first_name, last_name, email, birthday, login, password, phone) 
VALUES ('ISABELLA', 'SILVA', 'isabella@example.com', '1985-05-20', 'janesmith', 'letmein', '987-654-3210');

INSERT INTO user (first_name, last_name, email, birthday, login, password, phone) 
VALUES ('ABEL', 'SANTOS', 'abel@example.com', '1985-05-20', 'janesmith', 'letmein', '987-654-3210');

INSERT INTO user (first_name, last_name, email, birthday, login, password, phone) 
VALUES ('RICARDO', 'VALLENTI', 'ricardo@example.com', '1985-05-20', 'janesmith', 'letmein', '987-654-3210');

INSERT INTO user (first_name, last_name, email, birthday, login, password, phone) 
VALUES ('PAULO', 'SANTOS', 'paulo@example.com', '1985-05-20', 'janesmith', 'letmein', '987-654-3210');

