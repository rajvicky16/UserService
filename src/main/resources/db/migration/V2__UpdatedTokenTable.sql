ALTER TABLE tokens
    ADD token_val VARCHAR(255) NULL;

ALTER TABLE tokens
DROP
COLUMN token;