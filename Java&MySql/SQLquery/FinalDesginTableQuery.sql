-- 用户表
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `nickname` varchar(20) NOT NULL DEFAULT '',
  `email` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('user','expert','admin') NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
	`updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY `unique_nickname` (`nickname`),
  UNIQUE KEY `unique_email` (`email`),
  PRIMARY KEY (`user_id`)
);
-- ALTER TABLE `users`
-- ADD COLUMN `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- 课题表
CREATE TABLE `topics` (
  `topic_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` text,
  `status` enum('pending','approved','rejected') NOT NULL,
  `created_by` int NOT NULL,
  `assigned_to` int DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
	`category_id` int DEFAULT NULL,
	`batch_id` INT,
	`docs` varchar(255) DEFAULT NULL,
	FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);
  PRIMARY KEY (`topic_id`),
  FOREIGN KEY (`created_by`) REFERENCES `users` (`user_id`),
  FOREIGN KEY (`assigned_to`) REFERENCES `users` (`user_id`),
	FOREIGN KEY (`batch_id`) REFERENCES ActiveBatch(`active_batch_id`)
);

-- 评审表
CREATE TABLE `reviews` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `topic_id` int NOT NULL,
  `expert_id` int NOT NULL,
  `comments` text,
  `approved` boolean DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
	`updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`review_id`),
  FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`),
  FOREIGN KEY (`expert_id`) REFERENCES `users` (`user_id`)
);

-- 管理员操作记录表
CREATE TABLE `admin_actions` (
  `action_id` int NOT NULL AUTO_INCREMENT,
  `admin_id` int NOT NULL,
  `action_type` varchar(255) NOT NULL,
  `description` text,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`action_id`),
  FOREIGN KEY (`admin_id`) REFERENCES `users` (`user_id`)
);

-- 创建 category 表
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  `description` text,
	`create_user` int Not NULL,
	`created_at` datetime DEFAULT CURRENT_TIMESTAMP,
	`updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`category_id`)
);
-- 角色变更申请表
CREATE TABLE `role_change_requests` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `current_role` enum('user','expert','admin') NOT NULL,
  `requested_role` enum('user','expert','admin') NOT NULL,
  `status` enum('pending','approved','rejected') NOT NULL DEFAULT 'pending',
  `reason` text,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	-- 会自动更新时间
  PRIMARY KEY (`request_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

-- 创建开始批次表 ActiveBatch
CREATE TABLE activebatch (
    active_batch_id INT AUTO_INCREMENT PRIMARY KEY,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL
);

CREATE TABLE `topic_passed` (
  `pass_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,	-- 课题的名称
  `description` text,
  `created_by` VARCHAR(255),	-- 创建人的nickname
  `assigned_to` VARCHAR(255),	-- 审核人的nickname
	`category_id` int DEFAULT NULL,
	`batch_id` INT,							-- 批次编号
	`created_at` datetime DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,	-- 会自动更新时间
	FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  PRIMARY KEY (`pass_id`),
	FOREIGN KEY (`batch_id`) REFERENCES ActiveBatch(`active_batch_id`)
);

-- ALTER TABLE topics
-- ADD COLUMN batch_id INT;
-- 
-- ALTER TABLE topics
-- ADD CONSTRAINT fk_batch
-- FOREIGN KEY (batch_id) REFERENCES ActiveBatch(active_batch_id);

-- -- 更新 topics 表，添加 category_id 列
-- ALTER TABLE `users`
-- MODIFY COLUMN `email` varchar(255)  DEFAULT NULL
-- ADD COLUMN `category_id` int DEFAULT NULL,
-- ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);
-- 
-- -- ALTER TABLE category CHANGE COLUMN name category_name varchar(255) NOT NULL;

-- MODIFY COLUMN `email` varchar(255) NOT NULL DEFAULT ''
-- Alter TABLE `role_change_requests`
-- ADD updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
