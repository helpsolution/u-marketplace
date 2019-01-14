
INSERT INTO category (id, name)
VALUES (7,'Продукты');

INSERT INTO PRODUCT (seller_id, product_name, product_category, price, description, product_color, product_size, product_count)
VALUES (2, 'Молоко', 7, 76, '5% жирность', 'Белый', '1 литр', 100);

INSERT INTO PRODUCT (seller_id, product_name, product_category, price, description, product_color, product_size, product_count)
VALUES (2, 'Шоколад', 7, 76, '5% горькость', 'Белый', '500 г', 400);

INSERT INTO basket (customer_id)
VALUES (3);