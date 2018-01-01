CREATE TABLE domains_lists (
  id_domains_list INTEGER NOT NULL AUTO_INCREMENT,
  domains_list_name VARCHAR(30) NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT FALSE,
  is_black BOOLEAN NOT NULL DEFAULT TRUE,
  is_timed BOOLEAN NOT NULL DEFAULT FALSE,
  time_begin TIME NOT NULL DEFAULT '12:00',
  time_end TIME NOT NULL DEFAULT '13:00',
  PRIMARY KEY (id_domains_list)
);

CREATE TABLE domains (
  id_domain INTEGER NOT NULL AUTO_INCREMENT,
  domain_string VARCHAR(30) NOT NULL,
  id_domains_list INTEGER NOT NULL,
  PRIMARY KEY (id_domain),
  FOREIGN KEY (id_domains_list) REFERENCES domains_lists(id_domains_list)
);

CREATE TABLE transmission_types (
  id_transmission_type INTEGER NOT NULL AUTO_INCREMENT,
  transmission_type_name VARCHAR(30) NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT FALSE,
  is_timed BOOLEAN NOT NULL DEFAULT FALSE,
  time_begin TIME NOT NULL DEFAULT '12:00',
  time_end TIME NOT NULL DEFAULT '13:00',
  PRIMARY KEY (id_transmission_type)
);

CREATE TABLE headers (
  id_header INTEGER NOT NULL AUTO_INCREMENT,
  header_string VARCHAR(30) NOT NULL,
  id_transmission_type INTEGER NOT NULL,
  PRIMARY KEY (id_header),
  FOREIGN KEY (id_transmission_type) REFERENCES transmission_types(id_transmission_type)
);

CREATE TABLE words_lists (
  id_words_list INTEGER NOT NULL AUTO_INCREMENT,
  words_list_name VARCHAR(30) NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT FALSE,
  is_timed BOOLEAN NOT NULL DEFAULT FALSE,
  time_begin TIME NOT NULL DEFAULT '12:00',
  time_end TIME NOT NULL DEFAULT '13:00',
  PRIMARY KEY (id_words_list)
);

CREATE TABLE words (
  id_word INTEGER NOT NULL AUTO_INCREMENT,
  word_string VARCHAR(30) NOT NULL,
  id_words_list INTEGER NOT NULL,
  PRIMARY KEY (id_word),
  FOREIGN KEY (id_words_list) REFERENCES words_lists(id_words_list)
);

CREATE TABLE user (
  id_user INTEGER NOT NULL AUTO_INCREMENT,
  user_ip VARCHAR(30) NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT FALSE,
  is_timed BOOLEAN NOT NULL DEFAULT FALSE,
  time_begin TIME NOT NULL DEFAULT '12:00',
  time_end TIME NOT NULL DEFAULT '13:00',
  has_duration BOOLEAN NOT NULL DEFAULT FALSE,
  duration_interval INTEGER NOT NULL DEFAULT 60,
  used_time INTEGER NOT NULL DEFAULT 0,
  PRIMARY KEY (id_user)
);

