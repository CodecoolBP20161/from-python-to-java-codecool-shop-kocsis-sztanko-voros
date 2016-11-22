INSERT INTO productcategory (name, department, description)
VALUES ('Tablet', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');

INSERT INTO productcategory (name, department, description)
VALUES ('Laptop', 'Hardware', 'A laptop computer, flat mobile computer with a display.');

INSERT INTO supplier (name, description)
VALUES ('Amazon', 'Digital content and services');

INSERT INTO supplier (name, description)
VALUES ('Lenovo', 'Computers');

INSERT INTO product (name, description, defaultprice, defaultcurrency, product_category, supplier)
VALUES ('Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49.9, 'USD', (SELECT id FROM productcategory WHERE id = 1), (SELECT id FROM supplier WHERE id = 1));

INSERT INTO product (name, description, defaultprice, defaultcurrency, product_category, supplier)
VALUES ('Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 'USD', (SELECT id FROM productcategory WHERE id = 1), (SELECT id FROM supplier WHERE id = 2));