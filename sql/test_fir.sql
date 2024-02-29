SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test_fir
-- ----------------------------
DROP TABLE IF EXISTS `test_fir`;
CREATE TABLE `test_fir`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `sex` tinyint(0) NOT NULL COMMENT '性别',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `test_fir_code_uindex`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_fir
-- ----------------------------
INSERT INTO `test_fir` VALUES (26, 5, '张三', 'Q00002');
INSERT INTO `test_fir` VALUES (27, 5, '李四', 'Q00003');
INSERT INTO `test_fir` VALUES (28, 5, '王五', 'Q00004');
INSERT INTO `test_fir` VALUES (29, 5, '赵六', 'Q00005');
INSERT INTO `test_fir` VALUES (30, 5, '啦啦啦', 'Q00006');
INSERT INTO `test_fir` VALUES (31, 5, '哈哈哈哈', 'Q00007');
INSERT INTO `test_fir` VALUES (32, 5, '叽叽叽', 'Q00008');
INSERT INTO `test_fir` VALUES (33, 5, '测试名称', 'Q00001');

SET FOREIGN_KEY_CHECKS = 1;
