CREATE TABLE PATIENT (
                         ID BIGINT PRIMARY KEY,
                         FIRST_NAME VARCHAR(255),
                         LAST_NAME VARCHAR(255),
                         PHONE_NO VARCHAR(20),
                         EMAIL VARCHAR(255),
                         ADDRESS_ID BIGINT
    -- Add other columns as needed
);