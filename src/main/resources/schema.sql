DROP TABLE IF EXISTS CHANNEL_VIDEO_RELATION;
DROP TABLE IF EXISTS CHANNELS;
DROP TABLE IF EXISTS VIDEOS;

CREATE TABLE CHANNELS
(
    ID          INTEGER AUTO_INCREMENT PRIMARY KEY UNIQUE,
    NAME        CHARACTER VARYING(100) NOT NULL,
    DATE        DATE,
    SUBSCRIBERS INTEGER
);
CREATE TABLE VIDEOS
(
    ID    INTEGER AUTO_INCREMENT PRIMARY KEY UNIQUE,
    TITLE CHARACTER VARYING(100) NOT NULL,
    VIEWS INTEGER,
    LINK  VARCHAR,
    GENRE VARCHAR(50),
    CHANNEL_ID VARCHAR
);
CREATE TABLE CHANNEL_VIDEO_RELATION
(
    CHANNEL_ID INTEGER,
    VIDEO_ID   INTEGER,
    CONSTRAINT FK_CHANNEL_ID
        FOREIGN KEY (CHANNEL_ID) REFERENCES CHANNELS,
    CONSTRAINT FK_VIDEO_ID
        FOREIGN KEY (VIDEO_ID) REFERENCES VIDEOS
);