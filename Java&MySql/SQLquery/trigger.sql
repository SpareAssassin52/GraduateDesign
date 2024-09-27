CREATE TRIGGER before_insert_user_nickname
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
  SET NEW.nickname = CONCAT(NEW.username);
END;

-- 用户昵称默认为用户名