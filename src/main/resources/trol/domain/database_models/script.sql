CREATE  TABLE appusers (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));

CREATE TABLE authorities (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES appusers (username));

INSERT INTO appusers(username,password,enabled)
VALUES ('admin','admin', true);

INSERT INTO authorities (username, role)
VALUES ('admin', 'ROLE_USER');

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
  FOREIGN KEY (id_domains_list) REFERENCES domains_lists(id_domains_list) ON DELETE CASCADE
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
  FOREIGN KEY (id_transmission_type) REFERENCES transmission_types(id_transmission_type) ON DELETE CASCADE
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
  FOREIGN KEY (id_words_list) REFERENCES words_lists(id_words_list) ON DELETE CASCADE
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

INSERT INTO transmission_types(transmission_type_name) values("Video_Stream");
INSERT INTO transmission_types(transmission_type_name) values("Videos");
INSERT INTO transmission_types(transmission_type_name) values("Audio");
INSERT INTO transmission_types(transmission_type_name) values("Images");

INSERT INTO headers(header_string,id_transmission_type) values("video/abst",1);
INSERT INTO headers(header_string,id_transmission_type) values("video/animaflex",1);
INSERT INTO headers(header_string,id_transmission_type) values("application/x-mpegURL",1);
INSERT INTO headers(header_string,id_transmission_type) values("vnd.apple.mpegURL",1);
INSERT INTO headers(header_string,id_transmission_type) values("video/MP2T",1);

INSERT INTO headers(header_string,id_transmission_type) values("video/x-amt-showrun",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-amt-demorun",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/vosaic",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/vnd.vivo",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/vivo",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/vnd.vivo",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/vivo",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/vdo",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-scm",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/vnd.rn-realvideo",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-qtc",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/quicktime",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-sgi-movie",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/mpeg",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-mpeq2a",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-mpeg",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-sgi-movie",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-motion-jpeg",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-isvideo",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-gl",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/gl",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-atomic3d-feature",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-fli",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/fli",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-dv",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-dl",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/dl",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/avs-video",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/msvideo",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-msvideo",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/avi",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-ms-asf-plugin",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/x-ms-asf",2);
INSERT INTO headers(header_string,id_transmission_type) values("video/animaflex",2);

INSERT INTO headers(header_string,id_transmission_type) values("audio/aiff",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-aiff",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/basic",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-au",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/make",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-gsm",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/it",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-jam",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/midi",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/nspaudio",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-nspaudio",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-liveaudio",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/mpeg",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-mpequrl",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-mid",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-midi",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/mod",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-mod",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-mpeg",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/mpeg3",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-mpeg-3",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/make.my.funk",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/vnd.qcelp",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-pn-realaudio",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-pn-realaudio-plugin",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-realaudio",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-pn-realaudio",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/s3m",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-psid",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/basic",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-adpcm",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/tsp-audio",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/tsplayer",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/voc",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-voc",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/voxware",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-twinvq-plugin",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-twinvq",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-twinvq-plugin",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/wav",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/x-wav",3);
INSERT INTO headers(header_string,id_transmission_type) values("audio/xm",3);

INSERT INTO headers(header_string,id_transmission_type) values("image/x-jg",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/bmp",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-windows-bmp",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/vnd.dwg",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-dwg",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/fif",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/florian",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/vnd.fpx",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/g3fax",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/gif",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-icon",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/ief",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/jpeg",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/pjpeg",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-jps",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/jutvision",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/vasa",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/naplps",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-niff",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-portable-bitmap",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-pict",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-pcx",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-portable-graymap",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-portable-greymap",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-xpixmap",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/png",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-portable-anymap",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-portable-pixmap",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-quicktime",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/cmu-raster",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-cmu-raster",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/vnd.rn-realflash",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-rgb",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/vnd.rn-realpix",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/vnd.dwg",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-dwg",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/tiff",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-tiff",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/vnd.wap.wbmp",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-xbitmap",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-xbm",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/xbm",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/xpm",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/x-xwindowdump",4);
INSERT INTO headers(header_string,id_transmission_type) values("image/vnd.xiff",4);

