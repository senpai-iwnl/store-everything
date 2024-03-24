-- Tabela dla kategorii
CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL CHECK (length(name) BETWEEN 3 AND 20 AND lower(name) = name)
);

-- Tabela dla ról
CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE CHECK (name IN ('ADMIN', 'LIMITED_USER', 'FULL_USER', 'GUEST'))
);

-- Tabela dla użytkowników
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

-- Tabela dla informacji
CREATE TABLE information (
    id SERIAL PRIMARY KEY,
    title VARCHAR(20) NOT NULL CHECK (length(title) BETWEEN 3 AND 20),
    content TEXT NOT NULL CHECK (length(content) BETWEEN 5 AND 500),
    link TEXT, -- Można dodać walidację URL, jeśli jest potrzebna
    add_date DATE NOT NULL DEFAULT CURRENT_DATE,
    category_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (user_id) REFERENCES user_account(id)
);

-- Wstawianie domyślnych wartości do tabeli ról
INSERT INTO role (name) VALUES ('ADMIN'), ('LIMITED_USER'), ('FULL_USER'), ('GUEST');
