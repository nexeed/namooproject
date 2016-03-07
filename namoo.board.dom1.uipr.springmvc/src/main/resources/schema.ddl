-- DROP TABLE social_board IF EXISTS; 
-- DROP TABLE posting IF EXISTS;
-- DROP TABLE board_seq IF EXISTS;

CREATE TABLE IF NOT EXISTS social_board (
    board_id                CHAR(3)         PRIMARY KEY,
    board_name              VARCHAR(20)     NOT NULL,
    create_date             DATETIME        NOT NULL,
    UNIQUE KEY (board_name)
);

CREATE TABLE IF NOT EXISTS posting (
    posting_id              CHAR(8)         PRIMARY KEY,
    board_id                CHAR(3)         NOT NULL,
    title                   VARCHAR(100)    NOT NULL,
    writer_email            VARCHAR(50)     NOT NULL,
    writer_name				VARCHAR(10)		NOT NULL,
    read_count              INTEGER         NOT NULL,
    reg_date                DATETIME        NOT NULL,
    password				VARCHAR(20)		NOT NULL,
    contents                VARCHAR(5000)
);       

CREATE TABLE IF NOT EXISTS board_seq (
    seq_name                VARCHAR(20)     PRIMARY KEY,
    next_seq                INTEGER         NOT NULL
);

INSERT INTO board_seq (seq_name, next_seq)
VALUES
    ('board_id', 1);

COMMIT;

