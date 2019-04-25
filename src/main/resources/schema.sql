CREATE TABLE IF NOT EXISTS m_user(
	user_id VARCHAR(50) PRIMARY KEY,
	password VARCHAR(100),
	user_name VARCHAR(50),
	gender BOOLEAN,
	role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS articles(
  id INT(10) NOT NULL AUTO_INCREMENT,
  user_id VARCHAR(50) NOT NULL,
  title VARCHAR(30),
  content TEXT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES m_user (user_id)
);