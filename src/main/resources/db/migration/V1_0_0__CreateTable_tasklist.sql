CREATE TABLE IF NOT EXISTS tasklist (
  id varchar(8) PRIMARY KEY,
  task varchar(256),
  deadline varchar(10),
  done boolean
);
