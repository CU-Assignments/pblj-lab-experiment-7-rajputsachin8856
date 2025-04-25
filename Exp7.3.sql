
CREATE DATABASE IF NOT EXISTS StudentDB;
USE StudentDB;


CREATE TABLE IF NOT EXISTS Student (
    StudentID INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Department VARCHAR(50) NOT NULL,
    Marks DOUBLE NOT NULL
);
-- Step 3: Insert Sample Data
INSERT INTO Student (StudentID, Name, Department, Marks) VALUES 
(101, 'Alice Johnson', 'Computer Science', 85.5),
(102, 'Bob Smith', 'Electrical Engineering', 78.0),
(103, 'Charlie Davis', 'Mechanical Engineering', 92.3);
