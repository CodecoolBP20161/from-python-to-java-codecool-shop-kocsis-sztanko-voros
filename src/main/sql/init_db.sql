DROP TABLE IF EXISTS productcategory CASCADE ;
DROP  TABLE IF EXISTS supplier CASCADE ;
DROP  TABLE IF EXISTS product CASCADE ;

CREATE TABLE productcategory
(
  id SERIAL PRIMARY KEY UNIQUE,
  name VARCHAR(500),
  department VARCHAR(500),
  description VARCHAR (500)
);

CREATE TABLE supplier
(
  id SERIAL PRIMARY KEY UNIQUE ,
  name VARCHAR(500),
  description VARCHAR (500)
);

CREATE TABLE product
(
  id SERIAL PRIMARY KEY UNIQUE,
  name VARCHAR(500),
  description VARCHAR (500),
  defaultPrice FLOAT,
  defaultCurrency VARCHAR(10),
  product_category INT REFERENCES productcategory(id),
  supplier INT REFERENCES supplier(id)
);
