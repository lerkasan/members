CREATE TABLE IF NOT EXISTS members (
    id                  IDENTITY        NOT NULL    PRIMARY KEY,
    first_name          VARCHAR(100)    NOT NULL,
    last_name           VARCHAR(100)    NOT NULL,
    email               VARCHAR(100)    NOT NULL    UNIQUE,
    registration_date   DATE            NOT NULL    DEFAULT    CURRENT_DATE
);