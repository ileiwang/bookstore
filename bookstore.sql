/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : bookstore

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 15/07/2018 16:24:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS bookstore;
CREATE DATABASE bookstore;
USE bookstore;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'password', 'admin@qq.com');
INSERT INTO `admin` VALUES (2, 'admin01', 'password', 'admin01@qq.com');
INSERT INTO `admin` VALUES (3, 'admin02', 'password', 'admin02@qq.com');
INSERT INTO `admin` VALUES (4, 'admin03', 'password', 'admin03@qq.com');
INSERT INTO `admin` VALUES (5, 'admin04', 'password', 'admin04@qq.com');
INSERT INTO `admin` VALUES (6, 'admin05', 'password', 'admin05@qq.com');
INSERT INTO `admin` VALUES (7, 'admin06', 'password', 'admin06@qq.com');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` int(5) NOT NULL,
  `quantity` int(10) NOT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isdel` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '泰戈尔诗集', 18, 997, 'book_01.gif', 0);
INSERT INTO `book` VALUES (2, '痕记', 22, 66, 'book_02.gif', 0);
INSERT INTO `book` VALUES (3, '天堂之旅', 25, 96, 'book_03.gif', 0);
INSERT INTO `book` VALUES (4, '钱钟书集（全10册）', 332, 83, 'book_04.gif', 0);
INSERT INTO `book` VALUES (5, '赵俪生高昭—夫妻回忆录', 38, 22, 'book_05.gif', 0);
INSERT INTO `book` VALUES (6, '无聊斋（张绍刚首部随笔杂文作品）', 28, 189, 'book_06.gif', 0);
INSERT INTO `book` VALUES (7, '一颗热土豆是一张温馨的床', 38, 97, 'book_07.gif', 0);
INSERT INTO `book` VALUES (8, '李戡戡乱记', 22, 124, 'book_08.gif', 0);
INSERT INTO `book` VALUES (9, '生生世世未了缘', 17, 193, 'book_09.gif', 0);
INSERT INTO `book` VALUES (10, '一生有多少爱', 17, 378, 'book_10.gif', 0);
INSERT INTO `book` VALUES (11, '西游记', 20, 97, 'book_10.gif', 0);
INSERT INTO `book` VALUES (12, '史记', 100, 100, 'book_01.gif', 1);
INSERT INTO `book` VALUES (13, '水浒传', 100, 100, 'book_01.gif', 1);
INSERT INTO `book` VALUES (14, '论语', 199, 100, 'book_01.gif', 1);
INSERT INTO `book` VALUES (15, '三国演义', 29, 298, 'book_01.gif', 0);

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(10) NOT NULL,
  `book_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ORDERITEM_BOOK`(`book_id`) USING BTREE,
  INDEX `ORDERITEM_ORDER`(`order_id`) USING BTREE,
  CONSTRAINT `ORDERITEM_BOOK` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ORDERITEM_ORDER` FOREIGN KEY (`order_id`) REFERENCES `ordert` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES (1, 1, 1, 1);
INSERT INTO `orderitem` VALUES (2, 2, 2, 2);
INSERT INTO `orderitem` VALUES (3, 3, 1, 3);
INSERT INTO `orderitem` VALUES (4, 1, 4, 4);
INSERT INTO `orderitem` VALUES (5, 1, 2, 4);
INSERT INTO `orderitem` VALUES (6, 4, 1, 4);
INSERT INTO `orderitem` VALUES (7, 1, 1, 5);
INSERT INTO `orderitem` VALUES (8, 1, 6, 5);
INSERT INTO `orderitem` VALUES (9, 2, 1, 6);
INSERT INTO `orderitem` VALUES (10, 1, 2, 6);
INSERT INTO `orderitem` VALUES (11, 1, 3, 6);
INSERT INTO `orderitem` VALUES (12, 1, 1, 7);
INSERT INTO `orderitem` VALUES (13, 1, 10, 8);
INSERT INTO `orderitem` VALUES (14, 1, 6, 9);
INSERT INTO `orderitem` VALUES (15, 1, 7, 9);
INSERT INTO `orderitem` VALUES (16, 1, 8, 9);
INSERT INTO `orderitem` VALUES (17, 2, 1, 10);
INSERT INTO `orderitem` VALUES (18, 1, 3, 10);
INSERT INTO `orderitem` VALUES (19, 1, 4, 10);
INSERT INTO `orderitem` VALUES (20, 1, 5, 10);
INSERT INTO `orderitem` VALUES (21, 1, 8, 10);
INSERT INTO `orderitem` VALUES (22, 1, 9, 10);
INSERT INTO `orderitem` VALUES (23, 2, 10, 10);
INSERT INTO `orderitem` VALUES (24, 1, 3, 11);
INSERT INTO `orderitem` VALUES (25, 1, 1, 12);
INSERT INTO `orderitem` VALUES (26, 1, 4, 13);
INSERT INTO `orderitem` VALUES (27, 1, 5, 13);

-- ----------------------------
-- Table structure for ordert
-- ----------------------------
DROP TABLE IF EXISTS `ordert`;
CREATE TABLE `ordert`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(10) NOT NULL,
  `createdate` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  `state` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ORDER_USER`(`user_id`) USING BTREE,
  CONSTRAINT `ORDER_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ordert
-- ----------------------------
INSERT INTO `ordert` VALUES (1, 18, '2018-07-11 15:42:09', 1, 4);
INSERT INTO `ordert` VALUES (2, 44, '2018-07-12 11:54:39', 1, 2);
INSERT INTO `ordert` VALUES (3, 54, '2018-07-13 16:10:00', 1, 0);
INSERT INTO `ordert` VALUES (4, 426, '2018-07-13 17:07:17', 1, 1);
INSERT INTO `ordert` VALUES (5, 46, '2018-07-13 21:52:45', 1, 3);
INSERT INTO `ordert` VALUES (6, 83, '2018-07-14 08:50:53', 6, 3);
INSERT INTO `ordert` VALUES (7, 18, '2018-07-14 09:01:36', 6, 2);
INSERT INTO `ordert` VALUES (8, 17, '2018-07-14 09:30:40', 6, 1);
INSERT INTO `ordert` VALUES (9, 88, '2018-07-14 11:47:48', 1, 1);
INSERT INTO `ordert` VALUES (10, 504, '2018-07-14 11:50:27', 1, 1);
INSERT INTO `ordert` VALUES (11, 25, '2018-07-14 12:41:57', 1, 0);
INSERT INTO `ordert` VALUES (12, 18, '2018-07-15 09:12:37', 28, 0);
INSERT INTO `ordert` VALUES (13, 370, '2018-07-15 15:09:22', 1, 0);
INSERT INTO `ordert` VALUES (14, 18, '2018-07-15 15:42:07', 1, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `islocked` int(2) NOT NULL DEFAULT 0,
  `errorcount` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'user', 'password', 'user@qq.com', 0, 0);
INSERT INTO `user` VALUES (2, 'zhangsan', 'password', 'zhangsan@qq.com', 1, 3);
INSERT INTO `user` VALUES (3, 'wangwu', 'password', 'wangwu@qq.com', 0, 2);
INSERT INTO `user` VALUES (5, 'zhaoliu', 'password', 'zhaoliu@qq.com', 0, 1);
INSERT INTO `user` VALUES (6, 'leesi', 'password', 'leesi@qq.com', 0, 1);
INSERT INTO `user` VALUES (9, 'xiaoming', 'password', 'xiaoming@qq.com', 0, 0);
INSERT INTO `user` VALUES (10, 'xiaohua', 'password', 'xiaohua@qq.com', 0, 0);
INSERT INTO `user` VALUES (11, 'xihong', 'password', 'xiaohong@qq.com', 0, 0);
INSERT INTO `user` VALUES (12, 'xiaoqiang', 'password', 'xiaoqiang@qq.com', 0, 0);
INSERT INTO `user` VALUES (13, 'xiaozhang', 'password', 'xiaozhang@qq.com', 0, 0);
INSERT INTO `user` VALUES (14, 'xiaolee', 'password', 'xiaoli@qq.com', 0, 0);
INSERT INTO `user` VALUES (15, 'xiaowang', 'password', 'xiaowang@qq.com', 0, 3);
INSERT INTO `user` VALUES (16, 'xiaozhao', 'password', 'xiaozhao@qq.com', 0, 0);
INSERT INTO `user` VALUES (17, 'xiaoqian', 'password', 'xiaoqian@qq.com', 0, 0);
INSERT INTO `user` VALUES (18, 'xiaosun', 'password', 'xiaosun@qq.com', 0, 0);
INSERT INTO `user` VALUES (19, 'xiaozhou', 'password', 'xiaozhou@qq.com', 0, 0);
INSERT INTO `user` VALUES (20, 'xiaowu', 'password', 'xiaowu@qq.com', 0, 0);
INSERT INTO `user` VALUES (22, 'test01', 'password', 'test01@qq.com', 0, 0);
INSERT INTO `user` VALUES (25, 'test02', 'password', 'test02@qq.com', 0, 0);
INSERT INTO `user` VALUES (26, 'test03', 'password', 'test03@qq.com', 0, 0);
INSERT INTO `user` VALUES (27, 'test04', 'password', 'test04@qq.com', 0, 0);
INSERT INTO `user` VALUES (28, 'test100', 'password', 'test100@qq.com', 0, 0);
INSERT INTO `user` VALUES (29, 'zhangsi', 'password', 'zhangsi@qq.com', 1, 3);

SET FOREIGN_KEY_CHECKS = 1;
