-- create admin users with 'password' password
INSERT INTO users(login, password_hash, role)
  VALUES ('admin', '$2a$10$H4cx5xv4buljDgMssNADiOHMD26LZc4dxyFrWzsjlHq71XnHmz/Za', 'ROLE_ADMIN');