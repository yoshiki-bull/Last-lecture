DROP TABLE IF EXISTS videos;

CREATE TABLE videos (
  video_id int unsigned AUTO_INCREMENT COMMENT "動画ID",
  video_title VARCHAR(100) NOT NULL COMMENT "動画タイトル",
  instructor VARCHAR(100) NOT NULL COMMENT "作者",
  language VARCHAR(100) NOT NULL COMMENT "動画の言語",
  price CHAR(10) NOT NULL COMMENT "有料か無料か",
  video_price INT(5) NOT NULL COMMENT "動画の値段",
  PRIMARY KEY(video_id)
 ) COMMENT="動画一覧";

INSERT INTO videos (video_title, instructor, language, price, video_price) VALUES ('もう怖くないGit!', '山浦', 'Japanese', 'price-paid', 12000);
INSERT INTO videos (video_title, instructor, language, price, video_price) VALUES ('もう怖くないLinuxコマンド', '田中', 'Japanese', 'price-free', 0);
INSERT INTO videos (video_title, instructor, language, price, video_price) VALUES ('Enjoy Programming', 'Jonny', 'English', 'price-free', 0);
INSERT INTO videos (video_title, instructor, language, price, video_price) VALUES ('Java Programming 17', 'Mike', 'English', 'price-paid', 12000);
