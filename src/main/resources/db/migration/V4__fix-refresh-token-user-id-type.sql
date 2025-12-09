
ALTER TABLE refresh_token
DROP FOREIGN KEY fk_refresh_token_user;

ALTER TABLE refresh_token
MODIFY COLUMN user_id VARCHAR(36) NOT NULL;

ALTER TABLE refresh_token
ADD CONSTRAINT fk_refresh_token_user
FOREIGN KEY (user_id) REFERENCES users(id);
