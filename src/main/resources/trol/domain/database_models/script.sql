CREATE TABLE domains_lists (
  id_domains_list INTEGER PRIMARY KEY,
  domains_list_name VARCHAR(30) NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT FALSE,
  is_black BOOLEAN NOT NULL DEFAULT TRUE,
  is_timed BOOLEAN NOT NULL DEFAULT FALSE,
  time_begin TIME,
  time_end TIME
);

CREATE TABLE domains (
  id_domain INTEGER PRIMARY KEY,
  domain_string VARCHAR(30) NOT NULL,
  id_domains_list INTEGER NOT NULL REFERENCES domains_lists
);

CREATE TABLE transmission_types (
  id_transmission_type INTEGER PRIMARY KEY,
  transmission_type_name VARCHAR(30) NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT FALSE,
  is_timed BOOLEAN NOT NULL DEFAULT FALSE,
  time_begin TIME,
  time_end TIME
);

CREATE TABLE headers (
  id_header INTEGER PRIMARY KEY,
  header_string VARCHAR(30) NOT NULL,
  id_transmission_type INTEGER NOT NULL REFERENCES transmission_types
);

CREATE TABLE words_lists (
  id_words_list INTEGER PRIMARY KEY,
  words_list_name VARCHAR(30) NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT FALSE,
  is_timed BOOLEAN NOT NULL DEFAULT FALSE,
  time_begin TIME,
  time_end TIME
);

CREATE TABLE words (
  id_word INTEGER PRIMARY KEY,
  word_string VARCHAR(30) NOT NULL,
  id_words_list INTEGER NOT NULL REFERENCES words_lists
);

CREATE TABLE user (
  id_user INTEGER PRIMARY KEY,
  user_ip VARCHAR(30) NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT FALSE,
  is_timed BOOLEAN NOT NULL DEFAULT FALSE,
  time_begin TIME,
  time_end TIME,
  has_duration BOOLEAN NOT NULL DEFAULT FALSE,
  duration_interval INTEGER,
  used_time INTEGER
);

