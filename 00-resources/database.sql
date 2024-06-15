CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL CHECK (length(name) BETWEEN 3 AND 20 AND lower(name) = name)
);

CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE CHECK (name IN ('ROLE_ADMIN', 'ROLE_LIMITED_USER', 'ROLE_UNLIMITED_USER'))
);

CREATE TABLE user_account (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL CHECK (length(first_name) BETWEEN 3 AND 20 AND first_name = initcap(first_name)),
    last_name VARCHAR(50) NOT NULL CHECK (length(last_name) BETWEEN 3 AND 50 AND last_name = initcap(last_name)),
    login VARCHAR(20) UNIQUE NOT NULL CHECK (length(login) BETWEEN 3 AND 20 AND lower(login) = login),
    password VARCHAR(255) NOT NULL CHECK (length(password) >= 5),
    age INTEGER NOT NULL CHECK (age >= 18),
    role_id INTEGER,
    FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE information (
    id SERIAL PRIMARY KEY,
    title VARCHAR(20) NOT NULL CHECK (length(title) BETWEEN 3 AND 20),
    content TEXT NOT NULL CHECK (length(content) BETWEEN 5 AND 500),
    link TEXT,
    add_date DATE NOT NULL DEFAULT CURRENT_DATE,
    category_id INTEGER NOT NULL,
    remainder TIMESTAMP DEFAULT (CURRENT_TIMESTAMP + interval '1 day'),
    is_public BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE user_account_information (
    user_account_id INTEGER NOT NULL,
    information_id INTEGER NOT NULL,
    owner BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (user_account_id, information_id),
    FOREIGN KEY (user_account_id) REFERENCES user_account(id),
    FOREIGN KEY (information_id) REFERENCES information(id)
);

INSERT INTO role (name) VALUES 
('ROLE_ADMIN'), 
('ROLE_LIMITED_USER'), 
('ROLE_UNLIMITED_USER');

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
