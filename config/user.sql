SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', '2017-06-18 14:14:51');
INSERT INTO `user` VALUES ('2', '李四', '2017-06-05 14:41:56');
INSERT INTO `user` VALUES ('3', '王五', '2017-06-01 14:42:14');
INSERT INTO `user` VALUES ('4', '赵六', '2017-06-03 14:42:28');
