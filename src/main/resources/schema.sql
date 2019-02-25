drop table if exists customer;
CREATE TABLE customer(
  cnpj         VARCHAR(16)      ,
  name         VARCHAR(45)      ,
  businessArea VARCHAR(45)      ,
  PRIMARY KEY (cnpj)
);
drop table if exists saleman;
CREATE TABLE saleman
(
  cpf     VARCHAR(13)  ,
  name    VARCHAR(45)  ,
  salario DOUBLE       ,
  PRIMARY KEY (name)
);
drop table if exists item;
CREATE TABLE item(
  itemId   INT,
  quantity INT          ,
  price    DOUBLE       ,
  PRIMARY KEY (itemId)
);
drop table if exists sale;
CREATE TABLE sale(
  idSale   INT,
  salesmanName VARCHAR(45) ,
  PRIMARY KEY (idSale)
);