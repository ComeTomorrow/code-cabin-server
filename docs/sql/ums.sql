-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: ums
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL COMMENT '文章标题',
  `user_id` varchar(45) NOT NULL COMMENT '会员id',
  `subtitle` varchar(45) DEFAULT NULL COMMENT '副标题',
  `summary` varchar(45) NOT NULL COMMENT '文章摘要',
  `cover_address` varchar(45) NOT NULL COMMENT '封面地址',
  `content` varchar(255) NOT NULL COMMENT '内容',
  `markdown_content` varchar(255) NOT NULL COMMENT 'markdown内容',
  `type` int DEFAULT NULL COMMENT '文章类型（1原创、2转载、3翻译）',
  `original_link` varchar(45) DEFAULT NULL COMMENT '转载、翻译的原始链接',
  `read_type` int DEFAULT NULL COMMENT '阅读范围（1全部可见、2仅我可见、3粉丝可见、4VIP可见）',
  `status` int DEFAULT NULL COMMENT '文章状态（0草稿，1，2）',
  `hits` bigint DEFAULT NULL COMMENT '点击量',
  `source` varchar(45) DEFAULT NULL COMMENT '客户端来源',
  `authorized_status` int DEFAULT NULL COMMENT '审核状态',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_tag`
--

DROP TABLE IF EXISTS `article_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT '姓名',
  `parent_id` int NOT NULL COMMENT '父标签id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分栏标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_tag`
--

LOCK TABLES `article_tag` WRITE;
/*!40000 ALTER TABLE `article_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `column`
--

DROP TABLE IF EXISTS `column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `column` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT '分类专栏名称',
  `blurb` varchar(45) DEFAULT NULL COMMENT '分类专栏简介',
  `coverAddress` varchar(45) DEFAULT NULL COMMENT '封面地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分类专栏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `column`
--

LOCK TABLES `column` WRITE;
/*!40000 ALTER TABLE `column` DISABLE KEYS */;
/*!40000 ALTER TABLE `column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '会员id',
  `article_id` int NOT NULL COMMENT '文章id',
  `content_address` varchar(45) DEFAULT NULL COMMENT '内容地址',
  `cover_address` varchar(45) DEFAULT NULL,
  `parent_comment_id` int NOT NULL COMMENT '父级评论id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `draft`
--

DROP TABLE IF EXISTS `draft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `draft` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `content` varchar(45) DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='草稿';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `draft`
--

LOCK TABLES `draft` WRITE;
/*!40000 ALTER TABLE `draft` DISABLE KEYS */;
/*!40000 ALTER TABLE `draft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_user`
--

DROP TABLE IF EXISTS `member_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `mu_code` varchar(45) NOT NULL COMMENT '用户扩展id，由系统生成',
  `nick_name` varchar(45) NOT NULL COMMENT '会员昵称',
  `mobile` varchar(45) NOT NULL COMMENT '手机号，用来作为登录会员主体',
  `password` varchar(255) NOT NULL COMMENT '密码（加密）',
  `enabled` tinyint NOT NULL DEFAULT '1' COMMENT '会员启用状态',
  `account_non_expired` tinyint DEFAULT NULL COMMENT '账号是否过期（1：是，0：否）',
  `account_non_locked` tinyint DEFAULT NULL COMMENT '账号是否锁定（1：是，0：否）',
  `credentials_non_expired` tinyint DEFAULT NULL COMMENT '凭证是否过期（1：是，0：否）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`),
  UNIQUE KEY `mu_code_UNIQUE` (`mu_code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_user`
--

LOCK TABLES `member_user` WRITE;
/*!40000 ALTER TABLE `member_user` DISABLE KEYS */;
INSERT INTO `member_user` VALUES (1,'126452','','15153247815','{noop}123456',1,1,1,1,NULL,NULL),(4,'66666','','13456295691','{bcrypt}$2a$10$/g/4gKlhpm3Rtrpo1BhnCOuATOfvm/6a4/pWLEslSzBKcJLGoA7dC',1,1,1,1,NULL,NULL),(5,'8c583258-b91f-4679-a294-b5694112764e','coder8c583258-b91f-4679-a294-b5694112764e','13456295692','{bcrypt}$2a$10$9niVSykbSvX6SevsTFgstufUPcIhTa7V7jh.RLu1miimL6vz2kuhO',1,1,1,1,NULL,NULL),(6,'766059ec-4f0b-4965-b3e3-31129f11c2cb','coder766059ec-4f0b-4965-b3e3-31129f11c2cb','13456295693','{bcrypt}$2a$10$arTb2HNGvr.0kmrF.kC5duzqqJVE2PPxWIdiRh6lNKpgP1o9qzj6y',1,1,1,1,NULL,NULL),(7,'bfe1baaa-3f8d-4117-b48b-57a4f53e362c','coderbfe1baaa-3f8d-4117-b48b-57a4f53e362c','13456295694','{bcrypt}$2a$10$GmZhDUBAKVsaeLb21SCwr.EJA7ovA5nm3epQC4yq2Jf58TiM54vQC',1,1,1,1,NULL,NULL),(8,'85414509-22cc-4f5b-a2e7-245f132aa4ed','coder85414509-22cc-4f5b-a2e7-245f132aa4ed','13456295695','{bcrypt}$2a$10$Cd5UxW1LT92UM0bpT3hNf.PR9OdRfPfkfE4XR9RELl7/HAn3FBbLW',1,1,1,1,NULL,NULL);
/*!40000 ALTER TABLE `member_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relation_article_tag_article`
--

DROP TABLE IF EXISTS `relation_article_tag_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `relation_article_tag_article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_tag_id` int NOT NULL,
  `article_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relation_article_tag_article`
--

LOCK TABLES `relation_article_tag_article` WRITE;
/*!40000 ALTER TABLE `relation_article_tag_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `relation_article_tag_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relation_column_article`
--

DROP TABLE IF EXISTS `relation_column_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `relation_column_article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `column_id` int NOT NULL COMMENT '专栏id',
  `article_id` int NOT NULL COMMENT '文章id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relation_column_article`
--

LOCK TABLES `relation_column_article` WRITE;
/*!40000 ALTER TABLE `relation_column_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `relation_column_article` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-06 10:22:33
