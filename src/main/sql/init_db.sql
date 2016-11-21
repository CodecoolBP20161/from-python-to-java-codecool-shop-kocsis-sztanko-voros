DROP  TABLE IF EXISTS productcategory;
DROP  TABLE IF EXISTS supplier;
DROP  TABLE IF EXISTS product;

CREATE TABLE productcategory
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(40),
  department VARCHAR(40)
);

CREATE TABLE supplier
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(40)
);

CREATE TABLE product
(
  id SERIAL PRIMARY KEY,
  defaultPrice FLOAT,
  defaultCurrency VARCHAR(10),
  product_category INT REFERENCES productcategory(id),
  supplier INT REFERENCES supplier(id)
);
