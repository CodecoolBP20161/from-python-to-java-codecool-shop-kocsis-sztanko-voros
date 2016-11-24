DROP TABLE IF EXISTS productcategory CASCADE ;
DROP  TABLE IF EXISTS supplier CASCADE ;
DROP  TABLE IF EXISTS product CASCADE ;

CREATE TABLE productcategory
(
  productcategory_id SERIAL PRIMARY KEY,
  productcategory_name VARCHAR(500),
  productcategory_department VARCHAR(500),
  productcategory_description VARCHAR (500)
);

CREATE TABLE supplier
(
  supplier_id SERIAL PRIMARY KEY,
  supplier_name VARCHAR(500),
  supplier_description VARCHAR (500)
);

CREATE TABLE product
(
  product_id SERIAL PRIMARY KEY,
  product_name VARCHAR(500),
  product_description VARCHAR (500),
  product_defaultPrice FLOAT,
  product_defaultCurrency VARCHAR(10),
  product_productcategory INT REFERENCES productcategory(productcategory_id),
  product_supplier INT REFERENCES supplier(supplier_id)
);
