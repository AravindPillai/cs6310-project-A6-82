CREATE TABLE users (
	  name VARCHAR(50) NOT NULL,
	  email VARCHAR(50) NOT NULL,
	  password VARCHAR(100) NOT NULL,
	  enabled TINYINT NOT NULL DEFAULT 1,
	  PRIMARY KEY (email)
	);

CREATE TABLE authorities (
  email VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (email) REFERENCES users(email)
);

CREATE UNIQUE INDEX ix_auth_email on authorities (email,authority);

-- User user@email.pass/pass
	INSERT INTO users (name, email, password, enabled)
	  values ('user',
	    'user@email.com',
	    '$2y$12$OP7N6nRxVQKZzoQ9yAL1Y.Va5fbLqbkmTXPXQjqF0vGA5J/T9U8J.',
	    1);

INSERT INTO authorities (email, authority)
	  values ('user@email.com', 'ROLE_USER');