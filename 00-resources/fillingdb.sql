-- Create roles
INSERT INTO role (name) VALUES 
('ROLE_ADMIN'), 
('ROLE_LIMITED_USER'), 
('ROLE_FULL_USER');

-- Create categories
INSERT INTO category (name) VALUES
('personal info'),
('financial info'),
('work & career'),
('legal docs'),
('education'),
('property & assets'),
('digital info'),
('travel & transport'),
('health & wellness'),
('family & relations'),
('home management'),
('hobbies & interests'),
('miscellaneous'),
('backup & recovery');

-- Create users pw admin
INSERT INTO user_account (first_name, last_name, login, password, age, role_id) VALUES
('Admin', 'User', 'admin', '$2a$10$xZQOItwVcbuo/fLmgRj42u5IefqKWfdIRWpDSEGi3lK0Gr.fIpBHe', 30, (SELECT id FROM role WHERE name = 'ROLE_ADMIN')),
('Unlimited', 'User', 'unlimited_user', '$2a$10$xZQOItwVcbuo/fLmgRj42u5IefqKWfdIRWpDSEGi3lK0Gr.fIpBHe', 28, (SELECT id FROM role WHERE name = 'ROLE_UNLIMITED_USER')),
('Limited', 'User', 'limited_user', '$2a$10$xZQOItwVcbuo/fLmgRj42u5IefqKWfdIRWpDSEGi3lK0Gr.fIpBHe', 25, (SELECT id FROM role WHERE name = 'ROLE_LIMITED_USER'));

-- Create information entries
INSERT INTO information (title, content, link, add_date, category_id, remainder, is_public) VALUES
('Info1', 'Content1', 'http://link1.com', CURRENT_DATE, (SELECT id FROM category WHERE name = 'personal info'), CURRENT_TIMESTAMP + interval '1 day', FALSE),
('Info2', 'Content2', 'http://link2.com', CURRENT_DATE, (SELECT id FROM category WHERE name = 'financial info'), CURRENT_TIMESTAMP + interval '1 day', TRUE),
('Info3', 'Content3', 'http://link3.com', CURRENT_DATE, (SELECT id FROM category WHERE name = 'work & career'), CURRENT_TIMESTAMP + interval '1 day', FALSE),
('Info4', 'Content4', 'http://link4.com', CURRENT_DATE, (SELECT id FROM category WHERE name = 'legal docs'), CURRENT_TIMESTAMP + interval '1 day', TRUE),
('Info5', 'Content5', 'http://link5.com', CURRENT_DATE, (SELECT id FROM category WHERE name = 'education'), CURRENT_TIMESTAMP + interval '1 day', FALSE),
('Info6', 'Content6', 'http://link6.com', CURRENT_DATE, (SELECT id FROM category WHERE name = 'property & assets'), CURRENT_TIMESTAMP + interval '1 day', FALSE),
('Info7', 'Content7', 'http://link7.com', CURRENT_DATE, (SELECT id FROM category WHERE name = 'digital info'), CURRENT_TIMESTAMP + interval '1 day', TRUE),
('Info8', 'Content8', 'http://link8.com', CURRENT_DATE, (SELECT id FROM category WHERE name = 'travel & transport'), CURRENT_TIMESTAMP + interval '1 day', TRUE),
('Info9', 'Content9', 'http://link9.com', CURRENT_DATE, (SELECT id FROM category WHERE name = 'health & wellness'), CURRENT_TIMESTAMP + interval '1 day', FALSE),
('Info10', 'Content10', 'http://link10.com', CURRENT_DATE, (SELECT id FROM category WHERE name = 'family & relations'), CURRENT_TIMESTAMP + interval '1 day', FALSE);

-- Link limited_user and unlimited_user with information and set ownership
INSERT INTO user_account_information (user_account_id, information_id, owner) VALUES
(2, 1, TRUE),
(2, 2, TRUE),
(2, 3, TRUE),
(2, 4, TRUE),
(2, 5, TRUE),
(2, 6, TRUE),
(2, 7, TRUE),
(2, 8, TRUE),
(2, 9, TRUE),
(2, 10, TRUE);

-- (Optional) Link additional users to information without ownership
-- Example: Unlimited user has access to limited user's information without being the owner
INSERT INTO user_account_information (user_account_id, information_id, owner) VALUES
(3, 1, FALSE),
(3, 2, FALSE),
(3, 3, FALSE),
(3, 4, FALSE),
(3, 5, FALSE),
(3, 6, FALSE);