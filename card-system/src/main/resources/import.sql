-- Mysql database type
-- ---------------------- challenge ---------------
DROP DATABASE challenge;

CREATE DATABASE challenge;
-- select database
USE challenge;

-- Users Table
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Accounts Table
CREATE TABLE Accounts (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    balance DECIMAL(15, 2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

-- Cards Table (Physical Cards)
CREATE TABLE Cards (
    card_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT,
    card_number VARBINARY(255) NOT NULL, -- Store encrypted card number
    expiration_date DATE NOT NULL,
    cvv VARBINARY(3) NOT NULL, -- Store encrypted and temporary CVV
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    delivered VARCHAR(1),
    active ENUM('ATIVO', 'INATIVO') DEFAULT NULL,
    cancelReason ENUM('PERDA', 'ROUBO', 'DANO') DEFAULT NULL,
    FOREIGN KEY (account_id) REFERENCES Accounts(account_id) ON DELETE CASCADE
);

-- Virtual Cards Table (Temporary Cards)
CREATE TABLE VirtualCards (
    virtual_card_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT,
    card_number VARBINARY(255) NOT NULL, -- Store encrypted card number
    expiration_date DATE NOT NULL,
    cvv VARBINARY(3) NOT NULL, -- Store encrypted and temporary CVV
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    fisical_card_id INT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES Accounts(account_id) ON DELETE CASCADE,
    FOREIGN KEY (fisical_card_id) REFERENCES Cards(card_id) ON DELETE CASCADE
);

-- Card Activity Log Table (For audit purposes)
CREATE TABLE CardActivityLog (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    card_id INT,
    action VARCHAR(50) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notes TEXT,
    FOREIGN KEY (card_id) REFERENCES Cards(card_id) ON DELETE CASCADE
);
