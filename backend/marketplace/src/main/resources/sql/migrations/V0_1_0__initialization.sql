-- password in plaintext: "password"
INSERT INTO user_of_system (id,  password, email, username, fullName, phone)
VALUES
  (1, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'user@mail.com', 'user', 'Name',
   1);
-- password in plaintext: "password"
INSERT INTO user_of_system (id,  password, email, username, fullName,  phone)
VALUES
  (2, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'johndoe@gmail.com', 'johndoe', 'John', 1);
-- password in plaintext: "password"
INSERT INTO user_of_system (id,  password, email, username, fullName,  phone)
VALUES (3, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'name@gmail.com', 'namesurname', 'Name',
        1);

INSERT INTO ROLE (role_id, role)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLE (role_id, role)
VALUES (2, 'ROLE_USER');

INSERT INTO USER_ROLE (id,  role_id)
VALUES (1, 1);
INSERT INTO USER_ROLE (id,  role_id)
VALUES (2, 2);
INSERT INTO USER_ROLE (id,  role_id)
VALUES (3, 2);

-- INSERT INTO PRODUCT (name, description, quantity, price)
-- VALUES ('Soap', 'Pears baby soap for Kids', 1, 35.75);
-- INSERT INTO PRODUCT (name, description, quantity, price)
-- VALUES ('Tooth Brush', 'Signal Tooth Brushes Size in (L, M, S)', 5, 34.50);
-- INSERT INTO PRODUCT (name, description, quantity, price)
-- VALUES ('Shirt', 'Casual Shirt imported from France', 3, 1500.00);
-- INSERT INTO PRODUCT (name, description, quantity, price)
-- VALUES ('Office Bag', 'Leather bag imported from USA', 40, 1000.00);
-- INSERT INTO PRODUCT (name, description, quantity, price)
-- VALUES ('Bottle', 'Hot Water Bottles', 80, 450.45);
-- INSERT INTO PRODUCT (name, description, quantity, price)
-- VALUES ('Wrist Watch', 'Imported wrist watches from swiss', 800, 2500.00);
-- INSERT INTO PRODUCT (name, description, quantity, price)
-- VALUES ('Mobile Phone', '3G/4G capability', 700, 45000.00);
-- INSERT INTO PRODUCT (name, description, quantity, price)
-- VALUES ('Shampoo', 'Head and Shoulders Shampoo', 500, 300.00);
-- INSERT INTO PRODUCT (name, description, quantity, price)
-- VALUES ('Leather Wallets', 'Imported Leather Wallets from AUS', 1000, 500.00);
-- INSERT INTO PRODUCT (name, description, quantity, price)
-- VALUES ('Camera', 'Imported Canon camera from USA', 10, 85000.00);