DROP TABLE PRODUCTS IF EXISTS;
DROP TABLE CATEGORIES IF EXISTS;

CREATE TABLE CATEGORIES(
    ID INT PRIMARY KEY,
    NAME VARCHAR(255)
);
DROP TABLE PRODUCTS IF EXISTS;
CREATE TABLE PRODUCTS(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    CATEGORY_ID INT,
    NAME VARCHAR(255),
    RATE DECIMAL(10, 2),
    PRICE DECIMAL(10, 2),
    FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID)
);