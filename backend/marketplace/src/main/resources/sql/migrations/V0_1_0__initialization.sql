-- password in plaintext: "password"
INSERT INTO user_of_system (password, email, username, fullName, phone)
VALUES
  ('$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'admin@mail.com', 'admin', 'admin',1);
-- password in plaintext: "password"
INSERT INTO user_of_system (password, email, username, fullName,  phone)
VALUES
  ('$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'seller@gmail.com', 'seller', 'seller', 2);
-- password in plaintext: "password"
INSERT INTO user_of_system (password, email, username, fullName,  phone)
VALUES ('$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'customer@gmail.com', 'customer', 'customer',3);

INSERT INTO user_of_system (password, email, username, fullName,  phone)
VALUES ('$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'analyst@gmail.com', 'analyst', 'analyst',4);

INSERT INTO ROLE (role_id, role)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLE (role_id, role)
VALUES (2, 'ROLE_SELLER');
INSERT INTO ROLE (role_id, role)
VALUES (3, 'ROLE_CUSTOMER');
INSERT INTO ROLE (role_id, role)
VALUES (4, 'ROLE_ANALYST');

INSERT INTO USER_ROLE (user_id,  role_id)
VALUES (1, 1);
INSERT INTO USER_ROLE (user_id,  role_id)
VALUES (2, 2);
INSERT INTO USER_ROLE (user_id,  role_id)
VALUES (3, 3);
INSERT INTO USER_ROLE (user_id,  role_id)
VALUES (3, 4);

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