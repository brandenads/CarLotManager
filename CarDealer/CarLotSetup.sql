
ALTER TABLE Payments DROP CONSTRAINT fk_Payments_AllCars;
ALTER TABLE Offers DROP CONSTRAINT fk_Offers_AllCars;

DROP TABLE AllCars;
CREATE TABLE AllCars (
    CarVin NUMBER(4) PRIMARY KEY,
    CarMake VARCHAR2(20),
    CarModel VARCHAR2(20),
    CarPrice NUMBER(10),
    CarOwner VARCHAR2(20));
INSERT INTO AllCars VALUES (1001, 'Honda', 'Accord', 24270, 'DEALER');
INSERT INTO AllCars VALUES (1111, 'Toyota', 'Camry', 24425, 'DEALER');
INSERT INTO AllCars VALUES (1222, 'Chevy', 'Tahoe', 49000, 'DEALER');
INSERT INTO AllCars VALUES (1333, 'Ford', 'Focus', 19000, 'DEALER');
INSERT INTO AllCars VALUES (1444, 'Jeep', 'Wrangler', 28295, 'Linda');
INSERT INTO AllCars VALUES (1559, 'Nissan', 'Altima', 24100, 'Linda');
INSERT INTO AllCars VALUES (1588, 'Mercedes', 'E-Class', 54000, 'Linda');
INSERT INTO AllCars VALUES (1677, 'BMW', 'X1', 34000, 'Nancy');
INSERT INTO AllCars VALUES (1789, 'Dodge', 'Caravan', 27530, 'DEALER');
commit;
SELECT * FROM AllCars;

DROP TABLE Offers;
CREATE TABLE Offers (
    OfferId NUMBER(10) PRIMARY KEY,
    CarVin NUMBER(4),
    CustomerName VARCHAR2(20),
    Price NUMBER(10),
    Status VARCHAR2(20));
INSERT INTO Offers VALUES (10,1789,'Kathy',13579,'PENDING');
INSERT INTO Offers VALUES (11,1789,'Kimberly',24680,'PENDING');
INSERT INTO Offers VALUES (15,1001,'John',23500,'PENDING');
INSERT INTO Offers VALUES (16,1001,'David',24000,'PENDING');
INSERT INTO Offers VALUES (17,1001,'Richard',23800,'PENDING');
INSERT INTO Offers VALUES (18,1222,'David',30000,'PENDING');
INSERT INTO Offers VALUES (19,1333,'Richard',27000,'PENDING');
SELECT * FROM Offers ORDER BY OfferId;
commit;

DROP TABLE Customers;
CREATE TABLE Customers (
    CustomerName VARCHAR2(20),
    CustomerPassword VARCHAR2(20),
    EmployeeFlag NUMBER(1));
INSERT INTO Customers VALUES ('John','12345678',0);
INSERT INTO Customers VALUES ('David','12345678',0);
INSERT INTO Customers VALUES ('Richard','12345678',0);
INSERT INTO Customers VALUES ('Linda','12345678',0);
INSERT INTO Customers VALUES ('Nancy','12345678',0);
INSERT INTO Customers VALUES ('Bob','12345678',1);
INSERT INTO Customers VALUES ('Kathy','12345678',0);
INSERT INTO Customers VALUES ('Kimberly','12345678',0);
commit;
SELECT * FROM Customers;

DROP TABLE Payments;
CREATE TABLE Payments (
    PaymentId NUMBER(10) PRIMARY KEY,
    CarVin NUMBER(4),
    CustomerName VARCHAR(20),
    PaymentAmount NUMBER(5),
    PaymentDate VARCHAR2(20));
INSERT INTO Payments VALUES (423, 1444, 'Linda', 470, '06-15-20');
INSERT INTO Payments VALUES (492, 1444, 'Linda', 470, '07-15-20');
INSERT INTO Payments VALUES (372, 1559, 'Linda', 415, '05-26-20');
INSERT INTO Payments VALUES (452, 1559, 'Linda', 415, '06-26-20');
INSERT INTO Payments VALUES (534, 1559, 'Linda', 415, '07-26-20');
INSERT INTO Payments VALUES (285, 1588, 'Linda', 872, '03-17-20');
INSERT INTO Payments VALUES (312, 1588, 'Linda', 872, '04-17-20');
INSERT INTO Payments VALUES (448, 1588, 'Linda', 872, '06-17-20');
INSERT INTO Payments VALUES (507, 1588, 'Linda', 872, '07-17-20');
INSERT INTO Payments VALUES (401, 1677, 'Nancy', 583, '06-04-20');
INSERT INTO Payments VALUES (485, 1677, 'Nancy', 583, '07-04-20');
INSERT INTO Payments VALUES (351, 1789, 'Nancy', 470, '05-20-20');
INSERT INTO Payments VALUES (479, 1789, 'Nancy', 470, '06-20-20');
INSERT INTO Payments VALUES (521, 1789, 'Nancy', 470, '07-20-20');
SELECT * FROM Payments;

ALTER TABLE Offers ADD CONSTRAINT fk_Offers_AllCars FOREIGN KEY (CarVin) REFERENCES AllCars(CarVin)  ON DELETE CASCADE;
ALTER TABLE Payments ADD CONSTRAINT fk_Payments_AllCars FOREIGN KEY (CarVin) REFERENCES AllCars(CarVin)  ON DELETE CASCADE;

commit;









CREATE SEQUENCE sequencer
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1;









CREATE OR REPLACE PROCEDURE ResetData(dummy NUMBER)
IS
BEGIN

ALTER TABLE Payments DROP CONSTRAINT fk_Payments_AllCars;
ALTER TABLE Offers DROP CONSTRAINT fk_Offers_AllCars;

DROP TABLE AllCars;
CREATE TABLE AllCars (
    CarVin NUMBER(4) PRIMARY KEY,
    CarMake VARCHAR2(20),
    CarModel VARCHAR2(20),
    CarPrice NUMBER(10),
    CarOwner VARCHAR2(20));
INSERT INTO AllCars VALUES (1001, 'Honda', 'Accord', 24270, 'DEALER');
INSERT INTO AllCars VALUES (1111, 'Toyota', 'Camry', 24425, 'DEALER');
INSERT INTO AllCars VALUES (1222, 'Chevy', 'Tahoe', 49000, 'DEALER');
INSERT INTO AllCars VALUES (1333, 'Ford', 'Focus', 19000, 'DEALER');
INSERT INTO AllCars VALUES (1444, 'Jeep', 'Wrangler', 28295, 'Linda');
INSERT INTO AllCars VALUES (1559, 'Nissan', 'Altima', 24100, 'Linda');
INSERT INTO AllCars VALUES (1588, 'Mercedes', 'E-Class', 54000, 'Linda');
INSERT INTO AllCars VALUES (1677, 'BMW', 'X1', 34000, 'Nancy');
INSERT INTO AllCars VALUES (1789, 'Dodge', 'Caravan', 27530, 'DEALER');
commit;
SELECT * FROM AllCars;

DROP TABLE Offers;
CREATE TABLE Offers (
    OfferId NUMBER(10) PRIMARY KEY,
    CarVin NUMBER(4),
    CustomerName VARCHAR2(20),
    Price NUMBER(10),
    Status VARCHAR2(20));
INSERT INTO Offers VALUES (10,1789,'Kathy',13579,'PENDING');
INSERT INTO Offers VALUES (11,1789,'Kimberly',24680,'PENDING');
INSERT INTO Offers VALUES (15,1001,'John',23500,'PENDING');
INSERT INTO Offers VALUES (16,1001,'David',24000,'PENDING');
INSERT INTO Offers VALUES (17,1001,'Richard',23800,'PENDING');
INSERT INTO Offers VALUES (18,1222,'David',30000,'PENDING');
INSERT INTO Offers VALUES (19,1333,'Richard',27000,'PENDING');
SELECT * FROM Offers ORDER BY OfferId;
commit;

DROP TABLE Customers;
CREATE TABLE Customers (
    CustomerName VARCHAR2(20),
    CustomerPassword VARCHAR2(20),
    EmployeeFlag NUMBER(1));
INSERT INTO Customers VALUES ('John','12345678',0);
INSERT INTO Customers VALUES ('David','12345678',0);
INSERT INTO Customers VALUES ('Richard','12345678',0);
INSERT INTO Customers VALUES ('Linda','12345678',0);
INSERT INTO Customers VALUES ('Nancy','12345678',0);
INSERT INTO Customers VALUES ('Bob','12345678',1);
INSERT INTO Customers VALUES ('Kathy','12345678',0);
INSERT INTO Customers VALUES ('Kimberly','12345678',0);
commit;
SELECT * FROM Customers;

DROP TABLE Payments;
CREATE TABLE Payments (
    PaymentId NUMBER(10) PRIMARY KEY,
    CarVin NUMBER(4),
    CustomerName VARCHAR(20),
    PaymentAmount NUMBER(5),
    PaymentDate VARCHAR2(20));
INSERT INTO Payments VALUES (423, 1444, 'Linda', 470, '06-15-20');
INSERT INTO Payments VALUES (492, 1444, 'Linda', 470, '07-15-20');
INSERT INTO Payments VALUES (372, 1559, 'Linda', 415, '05-26-20');
INSERT INTO Payments VALUES (452, 1559, 'Linda', 415, '06-26-20');
INSERT INTO Payments VALUES (534, 1559, 'Linda', 415, '07-26-20');
INSERT INTO Payments VALUES (285, 1588, 'Linda', 872, '03-17-20');
INSERT INTO Payments VALUES (312, 1588, 'Linda', 872, '04-17-20');
INSERT INTO Payments VALUES (448, 1588, 'Linda', 872, '06-17-20');
INSERT INTO Payments VALUES (507, 1588, 'Linda', 872, '07-17-20');
INSERT INTO Payments VALUES (401, 1677, 'Nancy', 583, '06-04-20');
INSERT INTO Payments VALUES (485, 1677, 'Nancy', 583, '07-04-20');
INSERT INTO Payments VALUES (351, 1789, 'Nancy', 470, '05-20-20');
INSERT INTO Payments VALUES (479, 1789, 'Nancy', 470, '06-20-20');
INSERT INTO Payments VALUES (521, 1789, 'Nancy', 470, '07-20-20');
SELECT * FROM Payments;

ALTER TABLE Offers ADD CONSTRAINT fk_Offers_AllCars FOREIGN KEY (CarVin) REFERENCES AllCars(CarVin)  ON DELETE CASCADE;
ALTER TABLE Payments ADD CONSTRAINT fk_Payments_AllCars FOREIGN KEY (CarVin) REFERENCES AllCars(CarVin)  ON DELETE CASCADE;

commit;
END;




