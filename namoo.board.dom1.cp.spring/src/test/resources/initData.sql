DELETE FROM board_seq;
DELETE FROM posting;
DELETE FROM social_board; 

INSERT INTO board_seq (seq_name, next_seq)
VALUES
    ('board_id', 1);

COMMIT;