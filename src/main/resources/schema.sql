DROP TABLE IF EXISTS CHANNELS;
CREATE TABLE CHANNELS
(
    ID          INTEGER AUTO_INCREMENT PRIMARY KEY,
    NAME        CHARACTER VARYING(100) NOT NULL,
    DATE        DATE,
    SUBSCRIBERS INTEGER
);