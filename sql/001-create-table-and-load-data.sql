DROP TABLE IF EXISTS videos;

CREATE TABLE videos (
  id int unsigned AUTO_INCREMENT COMMENT "動画ID",
  title VARCHAR(100) NOT NULL COMMENT "動画タイトル",
  instructor VARCHAR(100) NOT NULL COMMENT "作者",
  language VARCHAR(2) NOT NULL COMMENT "動画の言語",
  is_free BOOLEAN COMMENT "無料であるか",
  price INT(9) NOT NULL COMMENT "動画の値段",
  PRIMARY KEY(id)
 ) COMMENT="動画一覧";

INSERT INTO videos (title, instructor, language, is_free, price) VALUES ('もう怖くないGit!', '山浦', 'ja', false, 12000);
INSERT INTO videos (title, instructor, language, is_free, price) VALUES ('もう怖くないLinuxコマンド', '田中', 'ja', true, 0);
INSERT INTO videos (title, instructor, language, is_free, price) VALUES ('The Web Developer Bootcamp', 'Colt', 'en', false, 12000);
INSERT INTO videos (title, instructor, language, is_free, price) VALUES ('Java Programming 17', 'Mike', 'en', true, 0);
