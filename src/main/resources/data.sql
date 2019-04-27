INSERT INTO m_user(user_id, password, user_name, gender, role)
VALUES ('yamada@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '山田花子', false, 'ROLE_ADMIN');
INSERT INTO m_user(user_id, password, user_name, gender, role)
VALUES ('tanaka@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '田中太郎', true, 'ROLE_ADMIN');
INSERT INTO m_user(user_id, password, user_name, gender, role)
VALUES ('tamura@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '田村達也', true, 'ROLE_GENERAL');

INSERT INTO articles(id, user_id, title, content)
VALUES(1, 'tanaka@xxx.co.jp', 'TEST1', 'テストデータ1');
INSERT INTO articles(id, user_id, title, content)
VALUES(2, 'tanaka@xxx.co.jp', 'TEST3', 'テストデータ3');
INSERT INTO articles(id, user_id, title, content)
VALUES(3, 'tanaka@xxx.co.jp', 'TEST4', 'テストデータ4');
INSERT INTO articles(id, user_id, title, content)
VALUES(4, 'tanaka@xxx.co.jp', 'TEST2', 'テストデータ2');
INSERT INTO articles(id, user_id, title, content)
VALUES(5, 'tamura@xxx.co.jp', 'TEST5', 'テストデータ5');
INSERT INTO articles(id, user_id, title, content)
VALUES(6, 'tamura@xxx.co.jp', 'TEST6', 'テストデータ6');
INSERT INTO articles(id, user_id, title, content)
VALUES(7, 'yamada@xxx.co.jp', 'TEST7', 'テストデータ7');
INSERT INTO articles(id, user_id, title, content)
VALUES(8, 'yamada@xxx.co.jp', 'TEST8', 'テストデータ8');
INSERT INTO articles(id, user_id, title, content)
VALUES(9, 'yamada@xxx.co.jp', 'TEST9', 'テストデータ9');