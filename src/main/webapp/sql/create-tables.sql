CREATE TABLE Department
(
    department_id INT PRIMARY KEY NOT NULL IDENTITY,
    title VARCHAR(45) NOT NULL
);

CREATE TABLE Employee
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    id_dep INT NOT NULL,
    title VARCHAR(45) NOT NULL,
    first_name VARCHAR(45) NOT NULL,
    second_name VARCHAR(45) NOT NULL,
    salary INT NOT NULL,
    date DATE NOT NULL,
    CONSTRAINT fk_Employee_1 FOREIGN KEY (id_dep) REFERENCES Department (department_id)
);
CREATE INDEX fk_Employee_1_idx ON Employee (id_dep);