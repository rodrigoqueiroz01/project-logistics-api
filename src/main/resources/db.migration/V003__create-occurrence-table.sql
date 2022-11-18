CREATE TABLE occurrence (
  id BIGINT AUTO_INCREMENT NOT NULL,
   delivery_id BIGINT NULL,
   `description` VARCHAR(255) NULL,
   registration_date datetime NULL,
   CONSTRAINT pk_occurrence PRIMARY KEY (id)
);