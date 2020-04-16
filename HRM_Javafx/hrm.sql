/*
 Navicat Premium Data Transfer

 Source Server         : MYSQL80
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : hrm

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 16/04/2020 12:07:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(0) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1001, '123', '人事部');
INSERT INTO `account` VALUES (1002, '123', '管理部');
INSERT INTO `account` VALUES (1003, '123', '人事部');
INSERT INTO `account` VALUES (1004, '929', '管理部');
INSERT INTO `account` VALUES (1005, NULL, '人事部');

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary`  (
  `id` int(0) NOT NULL,
  `basic` int(0) NULL DEFAULT NULL,
  `subsidy` int(0) NULL DEFAULT NULL,
  `gov_allowance` int(0) NULL DEFAULT NULL,
  `pos_allowance` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary
-- ----------------------------
INSERT INTO `salary` VALUES (2001, 566, 674, 934, 6346);
INSERT INTO `salary` VALUES (2002, 203, 356, 3445, 67);
INSERT INTO `salary` VALUES (2003, 500, 500, 463, 789);
INSERT INTO `salary` VALUES (2004, 5000, 2000, 400, 500);
INSERT INTO `salary` VALUES (2005, 3000, 200, 600, 200);
INSERT INTO `salary` VALUES (2006, 3400, 234, 1245, 390);
INSERT INTO `salary` VALUES (2007, 5678, 5747, 212, 1245);
INSERT INTO `salary` VALUES (2008, 4567, 3423, 934, 6577);
INSERT INTO `salary` VALUES (2009, 4663, 52, 355, 352);
INSERT INTO `salary` VALUES (2010, 4674, 2355, 23, 23);
INSERT INTO `salary` VALUES (2011, 8423, 3567, 324, 241);
INSERT INTO `salary` VALUES (2012, 3521, 23, 244, 335);
INSERT INTO `salary` VALUES (2013, 8943, 453, 45, 234);
INSERT INTO `salary` VALUES (2014, 3525, 34, 344, 342);
INSERT INTO `salary` VALUES (2015, 352, 457, 342, 343);

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int(0) NOT NULL,
  `entry` date NOT NULL,
  `quit` date NULL DEFAULT NULL,
  `retire` date NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (2001, 'Jack', 27, '2006-03-17', '2020-03-06', NULL, '正式员工', '高级');
INSERT INTO `staff` VALUES (2002, 'Marry', 50, '2006-03-21', '2020-03-30', NULL, '主管', '中级');
INSERT INTO `staff` VALUES (2003, 'Mike', 60, '2006-03-21', NULL, '2019-10-23', '退休员工', '高级');
INSERT INTO `staff` VALUES (2004, 'Jackson', 20, '2006-03-21', NULL, NULL, '正式员工', '低级');
INSERT INTO `staff` VALUES (2005, 'Peter', 34, '2018-03-21', NULL, NULL, '临时工', '低级');
INSERT INTO `staff` VALUES (2006, 'lucy', 30, '2010-07-31', NULL, NULL, '经理', '高级');
INSERT INTO `staff` VALUES (2007, 'Luke', 59, '2020-04-10', NULL, NULL, '经理', '低级');
INSERT INTO `staff` VALUES (2008, 'Robot', 30, '2002-02-07', NULL, NULL, '正式员工', '中级');
INSERT INTO `staff` VALUES (2009, 'May', 50, '2006-03-21', '2014-07-24', NULL, '主管', '高级');
INSERT INTO `staff` VALUES (2010, 'Jay', 50, '2006-03-21', '2020-03-30', NULL, '主管', '低级');
INSERT INTO `staff` VALUES (2011, 'Michaell', 60, '2006-03-21', NULL, '2019-10-23', '退休员工', '低级');
INSERT INTO `staff` VALUES (2012, 'Mordy', 60, '2006-03-21', NULL, '2019-10-23', '退休员工', '中级');
INSERT INTO `staff` VALUES (2013, 'Challote', 30, '2010-07-31', NULL, NULL, '经理', '中级');
INSERT INTO `staff` VALUES (2014, 'Landy', 30, '2010-07-31', NULL, NULL, '临时工', '中级');
INSERT INTO `staff` VALUES (2015, 'Stephen', 30, '2010-07-31', NULL, NULL, '临时工', '高级');

SET FOREIGN_KEY_CHECKS = 1;
