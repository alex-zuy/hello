CREATE TABLE IF NOT EXISTS users (
  id           INTEGER PRIMARY KEY AUTO_INCREMENT,
  login        VARCHAR(50) NOT NULL,
  password_hash VARCHAR(60) NOT NULL,
  role         VARCHAR(20) NOT NULL,
  UNIQUE (login)
);

TRUNCATE users;
