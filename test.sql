/*
 Navicat Premium Data Transfer

 Source Server         : zox
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : web_test

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 11/01/2021 14:59:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for change
-- ----------------------------
DROP TABLE IF EXISTS `change`;
CREATE TABLE `change`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0),
  `g_id` int(11) NOT NULL,
  `g_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `g_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `g_price` float(10, 2) NOT NULL,
  `g_discount` float(4, 2) NOT NULL,
  `g_introduction` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `g_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0),
  `g_age` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of change
-- ----------------------------
INSERT INTO `change` VALUES (1, '2021-01-10 16:21:35', 1, '守望先锋 ', '竞技', 30.00, 0.50, '工业园传给我UG赐予我吧', '2021-01-10 16:22:17', 16, 0);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_id` int(11) NULL DEFAULT NULL,
  `g_id` int(11) NULL DEFAULT NULL,
  `content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 4, 1, '慈不带兵本次区别吧', '2021-01-10 16:23:25');

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float(10, 2) NULL DEFAULT NULL,
  `discount` float(4, 2) NULL DEFAULT NULL,
  `introduction` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` timestamp(0) NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `p_id` int(11) NULL DEFAULT NULL,
  `p_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of game
-- ----------------------------
INSERT INTO `game` VALUES (1, '守望先锋', '竞技', 28.00, 0.80, '备份我文化扶贫还能否为和平护肤品问会否我个ID哦比', '2021-01-10 16:19:28', 18, 1, 'A公司');
INSERT INTO `game` VALUES (2, '和平精英', '竞技', 18.00, 1.00, '搞一个会乌尔禾阅读喷雾和和德国催化UI问答区皮肤厈过程与务工图五点半改动和催我', '2021-01-10 16:20:54', 18, 2, 'B公司');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (1, '10001', '123456');
INSERT INTO `manager` VALUES (2, '10002', '123456');
INSERT INTO `manager` VALUES (3, 'cz123456', 'cz12345678');

-- ----------------------------
-- Table structure for put
-- ----------------------------
DROP TABLE IF EXISTS `put`;
CREATE TABLE `put`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp(0) NULL DEFAULT NULL,
  `g_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `g_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `g_price` float(10, 2) NULL DEFAULT NULL,
  `g_discount` float(4, 2) NULL DEFAULT NULL,
  `g_introduction` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `g_age` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `p_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of put
-- ----------------------------
INSERT INTO `put` VALUES (1, '2021-01-10 16:12:31', '英雄联盟', '竞技', 18.00, 1.00, 'weUI富文本是U我hi欧我比u ', 18, 0, 1);

-- ----------------------------
-- Table structure for shopping
-- ----------------------------
DROP TABLE IF EXISTS `shopping`;
CREATE TABLE `shopping`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_id` int(11) NULL DEFAULT NULL,
  `g_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shopping
-- ----------------------------
INSERT INTO `shopping` VALUES (1, 4, 1);
INSERT INTO `shopping` VALUES (5, 4, 2);

-- ----------------------------
-- Table structure for take
-- ----------------------------
DROP TABLE IF EXISTS `take`;
CREATE TABLE `take`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp(0) NULL DEFAULT NULL,
  `g_id` int(11) NULL DEFAULT NULL,
  `g_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reason` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of take
-- ----------------------------

-- ----------------------------
-- Table structure for tb_stu
-- ----------------------------
DROP TABLE IF EXISTS `tb_stu`;
CREATE TABLE `tb_stu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `sex` int(1) NULL DEFAULT NULL,
  `address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hobby` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_stu
-- ----------------------------
INSERT INTO `tb_stu` VALUES (17, '陈学冬', 25, 1, '上海市', '演戏', '2021-01-22');
INSERT INTO `tb_stu` VALUES (19, '蔡徐坤', 21, 1, '长沙市', '唱歌', '2021-01-27');
INSERT INTO `tb_stu` VALUES (20, '杨超越', 22, 2, '上海市', '搞事业', '2021-01-24');
INSERT INTO `tb_stu` VALUES (21, '张艺兴', 30, 1, '长沙市', '当导师', '2021-01-19');
INSERT INTO `tb_stu` VALUES (22, '姜云升', 24, 1, '昆明市', '唱说唱', '2021-01-23');
INSERT INTO `tb_stu` VALUES (24, '杨迪', 30, 1, '成都市', '录综艺', '2021-01-17');

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hobby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES (1, '李四', '打篮球', '南京市', '2000-01-04');
INSERT INTO `tb_student` VALUES (2, '张三', '吃饭', '镇江市', '2021-01-06');
INSERT INTO `tb_student` VALUES (3, '王五', '敲代码', '北京市', '2021-01-30');
INSERT INTO `tb_student` VALUES (4, '赵六', '吃饭', '南京市', '2021-01-26');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '貂蝉', '123456', '镇江市', 18);
INSERT INTO `tb_user` VALUES (2, '韩信', '111111', '镇江市', 18);
INSERT INTO `tb_user` VALUES (32, 'ZOX', '123', '镇江市', 21);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(16) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `type` int(11) NOT NULL,
  `ID_number` varchar(18) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'A公司', '12345678', 2, NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, 'B公司', '12345678', 2, NULL, NULL, NULL);
INSERT INTO `user` VALUES (3, '12345678', '12345678', 0, NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, '天下第一', '12345678', 1, NULL, NULL, NULL);
INSERT INTO `user` VALUES (5, '3170209085', '123456', 0, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
