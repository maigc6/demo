/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : cycle

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 14/08/2022 23:39:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '管理者id',
  `admin_phone` tinyint NOT NULL COMMENT '账号',
  `admin_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `admin_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名字',
  `admin_role_id` bigint NOT NULL DEFAULT 0 COMMENT '管理员权限',
  PRIMARY KEY (`admin_id`) USING BTREE,
  INDEX `admin_role_id`(`admin_role_id` ASC) USING BTREE,
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`admin_role_id`) REFERENCES `admin_role` (`admin_role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `admin_role_id` bigint NOT NULL COMMENT '管理类型id',
  `admin_role_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理类型名称',
  PRIMARY KEY (`admin_role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (0, '普通管理员');
INSERT INTO `admin_role` VALUES (1, '超级管理员');

-- ----------------------------
-- Table structure for book_collect
-- ----------------------------
DROP TABLE IF EXISTS `book_collect`;
CREATE TABLE `book_collect`  (
  `sellbook_id` bigint UNSIGNED NOT NULL COMMENT '收藏书籍',
  `user_id` bigint NOT NULL COMMENT '收藏人',
  PRIMARY KEY (`sellbook_id`, `user_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `book_collect_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `book_collect_ibfk_2` FOREIGN KEY (`sellbook_id`) REFERENCES `sell_book` (`sellbook_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_collect
-- ----------------------------

-- ----------------------------
-- Table structure for buy_book
-- ----------------------------
DROP TABLE IF EXISTS `buy_book`;
CREATE TABLE `buy_book`  (
  `buybook_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '求购书籍id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `buybook_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '求购书名',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者',
  `edition` int NOT NULL COMMENT '版本',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `qq_number` int NOT NULL COMMENT 'qq号',
  `buybook_state` int NOT NULL COMMENT '求购书籍状态（0未1已）',
  PRIMARY KEY (`buybook_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `buy_book_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buy_book
-- ----------------------------

-- ----------------------------
-- Table structure for buy_thing
-- ----------------------------
DROP TABLE IF EXISTS `buy_thing`;
CREATE TABLE `buy_thing`  (
  `buything_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '求购物品id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `buything_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `number` int NOT NULL COMMENT '数量',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `qq_number` int NOT NULL COMMENT 'qq号',
  `buything_state` int NOT NULL COMMENT '求购物品状态（0未1已）',
  PRIMARY KEY (`buything_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `buy_thing_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buy_thing
-- ----------------------------

-- ----------------------------
-- Table structure for sell_book
-- ----------------------------
DROP TABLE IF EXISTS `sell_book`;
CREATE TABLE `sell_book`  (
  `sellbook_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '售卖书籍的id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `sellbook_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '书名',
  `edition` int NOT NULL COMMENT '版本号',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `qq_number` int NOT NULL COMMENT 'qq号',
  `sellbook_state` int NOT NULL COMMENT '书籍状态（0是未售，1是已售）',
  PRIMARY KEY (`sellbook_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `sell_book_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sell_book
-- ----------------------------

-- ----------------------------
-- Table structure for sell_thing
-- ----------------------------
DROP TABLE IF EXISTS `sell_thing`;
CREATE TABLE `sell_thing`  (
  `sellthing_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '售卖物品id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `sellthing_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `sellthing_number` int NOT NULL COMMENT '数量',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `qq_number` int NOT NULL COMMENT 'qq号',
  `sellthing_state` int NOT NULL COMMENT '物品状态(0未1已)',
  PRIMARY KEY (`sellthing_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `sell_thing_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sell_thing
-- ----------------------------

-- ----------------------------
-- Table structure for thing_collect
-- ----------------------------
DROP TABLE IF EXISTS `thing_collect`;
CREATE TABLE `thing_collect`  (
  `sellthing_id` bigint UNSIGNED NOT NULL COMMENT '收藏售卖物品的id',
  `user_id` bigint NOT NULL COMMENT '收藏人',
  PRIMARY KEY (`sellthing_id`, `user_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `thing_collect_ibfk_1` FOREIGN KEY (`sellthing_id`) REFERENCES `sell_thing` (`sellthing_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `thing_collect_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of thing_collect
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名字',
  `phone_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `user_role_id` bigint NULL DEFAULT 0 COMMENT '用户类型id',
  `user_state` int NULL DEFAULT 0 COMMENT '用户状态',
  `updated_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_role_id`(`user_role_id` ASC) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`user_role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张良', '17627887659', '$2a$10$3n8T94RVxTZ4s7aTcVJfMupD1NiFKdkv6A6aQHtrOv/QohazCXZiu', 0, 0, '2022-08-14 18:22:02');
INSERT INTO `user` VALUES (5, '文丑', '18888888888', '123456', 0, 0, NULL);
INSERT INTO `user` VALUES (6, '字丑', '19999999999', '111111', 0, 0, '2022-08-14 18:27:05');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_role_id` bigint NOT NULL,
  `user_role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (0, '普通用户');
INSERT INTO `user_role` VALUES (1, '至尊用户');

SET FOREIGN_KEY_CHECKS = 1;
