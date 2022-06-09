DROP TABLE CART IF EXISTS;
DROP TABLE PRODUCTS IF EXISTS;
DROP TABLE CATEGORIES IF EXISTS;
DROP TABLE USERS IF EXISTS;

CREATE TABLE CATEGORIES(
    ID INT PRIMARY KEY,
    NAME VARCHAR(255)
);

CREATE TABLE PRODUCTS(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    CATEGORY_ID INT,
    NAME VARCHAR(255),
    RATE DECIMAL(10, 2),
    PRICE DECIMAL(10, 2),
    FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID)
);

CREATE TABLE USERS(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    USERNAME VARCHAR(255),
    PASSWORD VARCHAR(255)
);

CREATE TABLE CART(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    USER_ID INT,
    CATEGORY_ID INT,
    PRODUCT_ID INT,
    NAME VARCHAR(255),
    RATE DECIMAL(10, 2),
    PRICE DECIMAL(10, 2),
    FOREIGN KEY(USER_ID) REFERENCES USERS(ID),
    FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID),
    FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCTS(ID)
);

INSERT INTO USERS (USERNAME, PASSWORD) VALUES('first', 'user');
INSERT INTO USERS (USERNAME, PASSWORD) VALUES('second', 'user');
INSERT INTO USERS (USERNAME, PASSWORD) VALUES('third', 'user');