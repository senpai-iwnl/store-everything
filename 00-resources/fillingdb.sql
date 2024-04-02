-- Inserting sample data into the 'category' table
INSERT INTO category (name) VALUES 
('sports'),
('news'),
('tech'),
('travel'),
('health'),
('education'),
('finance'),
('entertainment'),
('lifestyle'),
('food');

-- Inserting sample data into the 'user_account' table
INSERT INTO user_account (first_name, last_name, login, password, age, role_id) VALUES 
('John', 'Doe', 'johndoe', 'password123', 28, (SELECT id FROM role WHERE name = 'FULL_USER')),
('Alex', 'Smith', 'alexsmith', 'password123', 25, (SELECT id FROM role WHERE name = 'ADMIN')),
('Sara', 'Lee', 'saralee', 'password123', 30, (SELECT id FROM role WHERE name = 'GUEST')),
('Elena', 'Murray', 'elenam', 'securepass789', 34, (SELECT id FROM role WHERE name = 'ADMIN')),
('Marco', 'Polo', 'marcop', 'explore123', 29, (SELECT id FROM role WHERE name = 'FULL_USER')),
('Lucy', 'Hart', 'lucyheart', 'brightside123', 26, (SELECT id FROM role WHERE name = 'LIMITED_USER')),
('Raj', 'Kumar', 'rajk', 'mypass777', 40, (SELECT id FROM role WHERE name = 'GUEST')),
('Mia', 'Wallace', 'miawallace', 'vega123', 35, (SELECT id FROM role WHERE name = 'FULL_USER')),
('Vincent', 'Vega', 'vincentv', 'jules123', 33, (SELECT id FROM role WHERE name = 'LIMITED_USER'));

-- Inserting sample data into the 'information' table
INSERT INTO information (title, content, link, category_id, user_account_id) VALUES 
('Soccer', 'Soccer games are thrilling this season.', 'http://example.com/soccer', (SELECT id FROM category WHERE name = 'sports'), (SELECT id FROM user_account WHERE login = 'johndoe')),
('Tech News', 'New tech gadgets released this week.', 'http://example.com/tech', (SELECT id FROM category WHERE name = 'tech'), (SELECT id FROM user_account WHERE login = 'alexsmith')),
('Travel Tips', 'Top 10 travel tips for budget travelers.', NULL, (SELECT id FROM category WHERE name = 'travel'), (SELECT id FROM user_account WHERE login = 'saralee')),
('Finance Tips', '5 Essential tips for personal finance.', 'http://example.com/finance', (SELECT id FROM category WHERE name = 'finance'), (SELECT id FROM user_account WHERE login = 'elenam')),
('Healthy Eating', 'Guide to healthy eating habits.', 'http://example.com/food', (SELECT id FROM category WHERE name = 'food'), (SELECT id FROM user_account WHERE login = 'marcop')),
('Online Education', 'Top platforms for online learning.', NULL, (SELECT id FROM category WHERE name = 'education'), (SELECT id FROM user_account WHERE login = 'lucyheart')),
('Movie Reviews', 'Latest movie reviews and ratings.', 'http://example.com/entertainment', (SELECT id FROM category WHERE name = 'entertainment'), (SELECT id FROM user_account WHERE login = 'rajk')),
('Travel Gear', 'Best gear for your travel adventures.', 'http://example.com/travel', (SELECT id FROM category WHERE name = 'travel'), (SELECT id FROM user_account WHERE login = 'miawallace')),
('Yoga for Beginners', 'Getting started with yoga.', NULL, (SELECT id FROM category WHERE name = 'health'), (SELECT id FROM user_account WHERE login = 'vincentv')),
('DIY Home Decor', 'Simple DIY projects for your home.', 'http://example.com/lifestyle', (SELECT id FROM category WHERE name = 'lifestyle'), (SELECT id FROM user_account WHERE login = 'elenam'));



