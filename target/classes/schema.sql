CREATE TABLE consult (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     doctor VARCHAR(255),
     specialty VARCHAR(255),
     patient VARCHAR(255),
     patient_age INT,
     pathology VARCHAR(255),
     symptoms VARCHAR(255)
);

CREATE TABLE patient (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255),
                         age INT
);
