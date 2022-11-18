CREATE TABLE delivery (
   id BIGINT AUTO_INCREMENT NOT NULL,
   client_id BIGINT NULL,
   tax DECIMAL NULL,
   status VARCHAR(255) NULL,
   order_date datetime NULL,
   completion_date datetime NULL,
   destiny_name VARCHAR(255) NULL,
   destiny_address VARCHAR(255) NULL,
   destiny_number INT NULL,
   destiny_complement VARCHAR(255) NULL,
   destiny_district VARCHAR(255) NULL,
   CONSTRAINT pk_delivery PRIMARY KEY (id)
);