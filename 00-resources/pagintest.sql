-- Insert testing data into category table
INSERT INTO category (name) VALUES
('tech'),
('health'),
('travel'),
('education');

-- Insert testing data into role table
INSERT INTO role (name) VALUES
('ROLE_ADMIN'),
('ROLE_LIMITED_USER'),
('ROLE_FULL_USER');

-- Insert a new user into user_account table
INSERT INTO user_account (first_name, last_name, login, password, age, role_id) VALUES
('John', 'Doe', 'johndoe', 'password123', 30, 3);

-- Get the id of the user just created
DO $$
DECLARE
    user_id INTEGER;
BEGIN
    SELECT id INTO user_id FROM user_account WHERE login = 'johndoe';

    -- Insert 20 pieces of information for the user
    INSERT INTO information (title, content, link, category_id, is_public, user_account_id) VALUES
    ('Title 1', 'Content for information 1', 'http://example.com/1', 1, TRUE, user_id),
    ('Title 2', 'Content for information 2', 'http://example.com/2', 2, TRUE, user_id),
    ('Title 3', 'Content for information 3', 'http://example.com/3', 3, TRUE, user_id),
    ('Title 4', 'Content for information 4', 'http://example.com/4', 4, TRUE, user_id),
    ('Title 5', 'Content for information 5', 'http://example.com/5', 1, TRUE, user_id),
    ('Title 6', 'Content for information 6', 'http://example.com/6', 2, TRUE, user_id),
    ('Title 7', 'Content for information 7', 'http://example.com/7', 3, TRUE, user_id),
    ('Title 8', 'Content for information 8', 'http://example.com/8', 4, TRUE, user_id),
    ('Title 9', 'Content for information 9', 'http://example.com/9', 1, TRUE, user_id),
    ('Title 10', 'Content for information 10', 'http://example.com/10', 2, TRUE, user_id),
    ('Title 11', 'Content for information 11', 'http://example.com/11', 3, TRUE, user_id),
    ('Title 12', 'Content for information 12', 'http://example.com/12', 4, TRUE, user_id),
    ('Title 13', 'Content for information 13', 'http://example.com/13', 1, TRUE, user_id),
    ('Title 14', 'Content for information 14', 'http://example.com/14', 2, TRUE, user_id),
    ('Title 15', 'Content for information 15', 'http://example.com/15', 3, TRUE, user_id),
    ('Title 16', 'Content for information 16', 'http://example.com/16', 4, TRUE, user_id),
    ('Title 17', 'Content for information 17', 'http://example.com/17', 1, TRUE, user_id),
    ('Title 18', 'Content for information 18', 'http://example.com/18', 2, TRUE, user_id),
    ('Title 19', 'Content for information 19', 'http://example.com/19', 3, TRUE, user_id),
    ('Title 20', 'Content for information 20', 'http://example.com/20', 4, TRUE, user_id);
END $$;
