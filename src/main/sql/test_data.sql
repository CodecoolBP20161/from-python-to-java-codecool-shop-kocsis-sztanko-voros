INSERT INTO productcategory (productcategory_name, productcategory_department, productcategory_description)
VALUES ('Tablet', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');

INSERT INTO productcategory (productcategory_name, productcategory_department, productcategory_description)
VALUES ('Phone', 'Hardware', 'A laptop computer, flat mobile computer with a display.');

INSERT INTO supplier (supplier_name, supplier_description)
VALUES ('Amazon', 'Digital content and services');

INSERT INTO supplier (supplier_name, supplier_description)
VALUES ('Lenovo', 'Computers');

INSERT INTO product (product_name, product_description, product_defaultprice, product_defaultcurrency, product_productcategory, product_supplier)
VALUES ('Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49.9, 'USD', (SELECT productcategory_id FROM productcategory WHERE productcategory_id = 2), (SELECT supplier_id FROM supplier WHERE supplier_id = 1));

INSERT INTO product (product_name, product_description, product_defaultprice, product_defaultcurrency, product_productcategory, product_supplier)
VALUES ('Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 'USD', (SELECT productcategory_id FROM productcategory WHERE productcategory_id = 1), (SELECT supplier_id FROM supplier WHERE supplier_id = 2));