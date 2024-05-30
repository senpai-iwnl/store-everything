INSERT INTO user_account (first_name, last_name, login, password, age, role_id) VALUES 
('Firstname', 'Lastname', 'admin', '$2a$10$nKVQvLW4Jo8SgwSLCjUrtOLwxIMMR3hekcEVySsS09lJ32GFL0kTm', 50, (SELECT id FROM role WHERE name = 'ROLE_ADMIN')), --pw: admin
('John', 'Doe', 'johndoe', '$2a$10$JAsgiqDgiiAyRu9mzIrYtOXK6f.l.2XHMAlX.GMIkAFWsHiowv2ny', 28, (SELECT id FROM role WHERE name = 'ROLE_FULL_USER')),  --pw: password123
('Alex', 'Smith', 'alexsmith', '$2a$10$JAsgiqDgiiAyRu9mzIrYtOXK6f.l.2XHMAlX.GMIkAFWsHiowv2ny', 25, (SELECT id FROM role WHERE name = 'ROLE_FULL_USER')),  --pw: password123
('Sara', 'Lee', 'saralee', '$2a$10$JAsgiqDgiiAyRu9mzIrYtOXK6f.l.2XHMAlX.GMIkAFWsHiowv2ny', 30, (SELECT id FROM role WHERE name = 'ROLE_LIMITED_USER')),  --pw: password123
('Elena', 'Murray', 'elenam', '$2a$10$Nex2/tfBpgKBX04qfX.2HOZlRbmWc5iKBP30O5/bv3razX7ykx4cW', 34, (SELECT id FROM role WHERE name = 'ROLE_FULL_USER')),  --pw: securepass789
('Marco', 'Polo', 'marcop', '$2a$10$GQPCXvmsWTn0fVvhw31YQeVJ6x4UR6LSSVYJakuoMTOKr8bnweYSW', 29, (SELECT id FROM role WHERE name = 'ROLE_FULL_USER')),  --pw: explore123
('Lucy', 'Hart', 'lucyheart', '$2a$10$GDgWPq7bZCMmAUGoNuwAAuu2Eux9AlKaoA73qHY7.1sQY7MzsreuS', 26, (SELECT id FROM role WHERE name = 'ROLE_LIMITED_USER')),  --pw: brightside123
('Raj', 'Kumar', 'rajk', '$2a$10$HysD6wRAe5UqiLlW3LuGYOeFmYumJXfAhSRPOeZ.QPLUkAUtRd.4e', 40, (SELECT id FROM role WHERE name = 'ROLE_LIMITED_USER')),  --pw: mypass777
('Mia', 'Wallace', 'miawallace', '$2a$10$nJnat5aIteF5DD/hXEq8nefdVzasSksrExlnMkZntFHCJmFhQhz6K', 35, (SELECT id FROM role WHERE name = 'ROLE_FULL_USER')),  --pw: vega123
('Vincent', 'Vega', 'vincentv', '$2a$10$Fw/y2X.7e/r4Ls4K2V2bZOeaVPymeG61Bb8cCf4q8p0kzqferpFQG', 33, (SELECT id FROM role WHERE name = 'ROLE_LIMITED_USER'));  --pw: jules123

--INSERT INTO information (title, content, link, category_id, user_account_id) VALUES 
--('Soccer', 'Soccer games are thrilling this season.', 'http://example.com/soccer', (SELECT id FROM category WHERE name = 'sports'), (SELECT id FROM user_account WHERE login = 'johndoe')),
--('Tech News', 'New tech gadgets released this week.', 'http://example.com/tech', (SELECT id FROM category WHERE name = 'tech'), (SELECT id FROM user_account WHERE login = 'alexsmith')),
--('Travel Tips', 'Top 10 travel tips for budget travelers.', NULL, (SELECT id FROM category WHERE name = 'travel'), (SELECT id FROM user_account WHERE login = 'saralee')),
--('Finance Tips', '5 Essential tips for personal finance.', 'http://example.com/finance', (SELECT id FROM category WHERE name = 'finance'), (SELECT id FROM user_account WHERE login = 'elenam')),
--('Healthy Eating', 'Guide to healthy eating habits.', 'http://example.com/food', (SELECT id FROM category WHERE name = 'food'), (SELECT id FROM user_account WHERE login = 'marcop')),
--('Online Education', 'Top platforms for online learning.', NULL, (SELECT id FROM category WHERE name = 'education'), (SELECT id FROM user_account WHERE login = 'lucyheart')),
--('Movie Reviews', 'Latest movie reviews and ratings.', 'http://example.com/entertainment', (SELECT id FROM category WHERE name = 'entertainment'), (SELECT id FROM user_account WHERE login = 'rajk')),
--('Travel Gear', 'Best gear for your travel adventures.', 'http://example.com/travel', (SELECT id FROM category WHERE name = 'travel'), (SELECT id FROM user_account WHERE login = 'miawallace')),
--('Yoga for Beginners', 'Getting started with yoga.', NULL, (SELECT id FROM category WHERE name = 'health'), (SELECT id FROM user_account WHERE login = 'vincentv')),
--('DIY Home Decor', 'Simple DIY projects for your home.', 'http://example.com/lifestyle', (SELECT id FROM category WHERE name = 'lifestyle'), (SELECT id FROM user_account WHERE login = 'elenam'));



