INSERT INTO CHANNELS(NAME, DATE, SUBSCRIBERS)
VALUES ('Beyond Fireship', '2022-09-04', '329000'),
       ('Tech Acad', '2017-03-28', '75000');
INSERT INTO VIDEOS(TITLE, VIEWS, LINK, GENRE)
VALUES ('Next.js 13-The Basics', '512000', 'https://youtu.be/__mSgDEOyv8?si=PzO5bV1nShiWz30p', 'Educational'),
       ('Time is Relative, even in JS', '96000', 'https://youtu.be/acemrBKuDqw?si=0pDWXFSWkD6oMq0A', 'Educational');

INSERT INTO PUBLIC.CHANNEL_VIDEO_RELATION (CHANNEL_ID, VIDEO_ID) VALUES (1, 1);
INSERT INTO PUBLIC.CHANNEL_VIDEO_RELATION (CHANNEL_ID, VIDEO_ID) VALUES (1, 2);
