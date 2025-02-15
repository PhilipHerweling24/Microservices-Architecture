CREATE DATABASE librarydb;
USE librarydb;

CREATE TABLE users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_name VARCHAR(255) NOT NULL,
                       user_email VARCHAR(255) UNIQUE NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE books (
                       book_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       book_title VARCHAR(255) NOT NULL,
                       book_author VARCHAR(255) NOT NULL,
                       isbn VARCHAR(50) UNIQUE NOT NULL,
                       available_copies INT NOT NULL DEFAULT 1
);

CREATE TABLE loans (
                       loan_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_id BIGINT NOT NULL,
                       book_id BIGINT NOT NULL,
                       borrow_date DATE NOT NULL,
                       due_date DATE NOT NULL,
                       returned BOOLEAN DEFAULT FALSE,
                       CONSTRAINT fk_loan_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                       CONSTRAINT fk_loan_book FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);
