CREATE TABLE ADDRESS (
                         addressId BIGINT PRIMARY KEY AUTO_INCREMENT,
                         street1 VARCHAR(255),
                         street2 VARCHAR(255),
                         state VARCHAR(20),
                         city VARCHAR(255),
                         zipcode BIGINT
    -- Add other columns as needed
);