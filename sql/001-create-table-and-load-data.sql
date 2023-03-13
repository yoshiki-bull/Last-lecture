DROP TABLE IF EXISTS videos;

CREATE TABLE videos (
  video_id int unsigned AUTO_INCREMENT COMMENT "動画ID",
  video_title VARCHAR(100) NOT NULL COMMENT "動画タイトル",
  video_language VARCHAR(100) NOT NULL COMMENT "動画の言語",
  price CHAR(10) NOT NULL COMMENT "有料か無料か",
  video_price INT(5) NOT NULL COMMENT "動画の値段",
  PRIMARY KEY(video_id)
 ) COMMENT="動画一覧";

INSERT INTO videos (video_title, video_language, price, video_price) VALUES ('もう怖くないGit!', 'ja', 'price-paid', 12000);
INSERT INTO videos (video_title, video_language, price, video_price) VALUES ('もう怖くないLinuxコマンド。', 'ja', 'price-paid', 12000);
INSERT INTO videos (video_title, video_language, price, video_price) VALUES ('ゼロから実践するAWS。', 'ja', 'price-free', 0);
INSERT INTO videos (video_title, video_language, price, video_price) VALUES ('Java Programming Masterclass updated to Java 17', 'en', 'price-free', 0);