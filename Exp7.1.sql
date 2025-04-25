CREATE DATABASE company;
USE company;

CREATE TABLE Employee (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Salary DECIMAL(10,2) NOT NULL
);

INSERT INTO Employee (Name, Salary) VALUES
('Alice', 50000.00),
('Bob', 60000.00),
('Charlie', 70000.00);
