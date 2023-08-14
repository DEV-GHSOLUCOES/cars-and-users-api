CREATE TABLE car (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    year INT NOT NULL,
    license_plate VARCHAR(20) NOT NULL,
    model VARCHAR(255) NOT NULL,
    color VARCHAR(50) NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

INSERT INTO car (year, license_plate, model, color, user_id)
VALUES (2021, 'ABC123', 'HB20', 'Blue', 1);

INSERT INTO car (year, license_plate, model, color, user_id)
VALUES (2022, 'ABC123', 'Onix reto', 'preto', 2);

INSERT INTO car (year, license_plate, model, color, user_id)
VALUES (2019, 'ABC123', 'Toyota Camry', 'branco', 3);

INSERT INTO car (year, license_plate, model, color, user_id)
VALUES (2020, 'ABC123', 'Toyota Camry', 'marron', 4);

INSERT INTO car (year, license_plate, model, color, user_id)
VALUES (2017, 'ABC123', 'Toyota Camry', 'vermelho', 6);

INSERT INTO car (year, license_plate, model, color, user_id)
VALUES (2015, 'ABC123', 'Toyota Camry', 'azul', 5);

INSERT INTO car (year, license_plate, model, color, user_id)
VALUES (2010, 'ABC123', 'Toyota Camry', 'verde', 7);
    

