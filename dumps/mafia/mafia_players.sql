CREATE DATABASE  IF NOT EXISTS `mafia` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mafia`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: mafia
-- ------------------------------------------------------
-- Server version	5.6.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `players` (
  `nickname` varchar(60) NOT NULL,
  `vkontakte` varchar(100) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL,
  `telephone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES ('Akito','',NULL,NULL,NULL),('Arch Enemy','dreamwalker7',NULL,NULL,NULL),('Barselino','',NULL,NULL,NULL),('Batler','alexbuttler',NULL,NULL,NULL),('Blink','keepon_rollin',NULL,NULL,NULL),('Candy','lyudmilkaaa7',NULL,NULL,NULL),('CAPS','kseniya_sedelnikova',NULL,NULL,NULL),('Etc','etcetere',NULL,NULL,NULL),('Faust','faustern',NULL,NULL,NULL),('Foster','komdon',NULL,NULL,NULL),('Fox','ss_fox',NULL,NULL,NULL),('Francesco','harry_franc',NULL,NULL,NULL),('Jane','id_your_jane',NULL,NULL,NULL),('Just','',NULL,NULL,NULL),('Kurt','volynetsoleg',NULL,NULL,NULL),('Lens','id16360140',NULL,NULL,NULL),('Locky','o_o_0_0_o_o',NULL,NULL,NULL),('Morley','morley',NULL,NULL,NULL),('PacMan','kovtanyuk_oleg',NULL,NULL,NULL),('Primary','id11929699',NULL,NULL,NULL),('Proud Deer','id24599413',NULL,NULL,NULL),('Questra','questra',NULL,NULL,NULL),('R2D2','ashevskiy',NULL,NULL,NULL),('Snoopy','snoopy_ua',NULL,NULL,NULL),('Spielberg','tsvill',NULL,NULL,NULL),('Ten','ten_mafia',NULL,NULL,NULL),('Vilka','kharytonov_tony',NULL,NULL,NULL),('Vincent','andrew.stanj',NULL,NULL,NULL),('XXX','komdon',NULL,NULL,NULL),('Yes','',NULL,NULL,NULL),('Аббревиатура','miss_of_cct',NULL,NULL,NULL),('Агрессор','id83905991',NULL,NULL,NULL),('Айдахо','find_the_clue',NULL,NULL,NULL),('Айсберг','id176895873',NULL,NULL,NULL),('Амахонда','id19328053',NULL,NULL,NULL),('Андріолло','komdon',NULL,NULL,NULL),('Ария','id23345456',NULL,NULL,NULL),('Арлекино','arlekos',NULL,NULL,NULL),('Артист','belb4onok',NULL,NULL,NULL),('Бабай','id71988945',NULL,NULL,NULL),('Банан','id13765879',NULL,NULL,NULL),('Бета','id5292281',NULL,NULL,NULL),('Борман','keepbleeding',NULL,NULL,NULL),('Бренди','nortman',NULL,NULL,NULL),('Вася','ndobrovolska',NULL,NULL,NULL),('Веселий Молочник','yanovych',NULL,NULL,NULL),('Голубок','komdon',NULL,NULL,NULL),('Голубь','didichenko',NULL,NULL,NULL),('Грей','komdon',NULL,NULL,NULL),('Гуффи','guffy_r',NULL,NULL,NULL),('Дания','panda_love_boo',NULL,NULL,NULL),('Денис','komdon',NULL,NULL,NULL),('Джек-Пот','x_jack_pot_x',NULL,NULL,NULL),('Дракон','dragonfly22',NULL,NULL,NULL),('Дровосек','komdon',NULL,NULL,NULL),('Елка','komdon',NULL,NULL,NULL),('Жеглов','ikulyk',NULL,NULL,NULL),('Зара','kuzhylnaya',NULL,NULL,NULL),('Заяц','egor.zaitsev',NULL,NULL,NULL),('Звездочка','just_polishchuk',NULL,NULL,NULL),('Игрок','igrok_iasa',NULL,NULL,NULL),('Инквизитор','advo_kat',NULL,NULL,NULL),('Какую Хочешь','komdon',NULL,NULL,NULL),('Капитан Морган','morgancap',NULL,NULL,NULL),('Ква','komdon',NULL,NULL,NULL),('Кертис','',NULL,NULL,NULL),('Клич','id19111965',NULL,NULL,NULL),('Князь','knyaz1991',NULL,NULL,NULL),('Командор','id5945049',NULL,NULL,NULL),('Космос','id206913380',NULL,NULL,NULL),('Лилия','id135915214',NULL,NULL,NULL),('Лунатик','amy_crime',NULL,NULL,NULL),('Мака','id26439075',NULL,NULL,NULL),('Мерлин','egemon',NULL,NULL,NULL),('Мертвячок','nikita_glumov',NULL,NULL,NULL),('Микки','id71737546',NULL,NULL,NULL),('Морган','id5815850',NULL,NULL,NULL),('Ник','nikolay_fomich',NULL,NULL,NULL),('Ниф','neftys',NULL,NULL,NULL),('Окси','oksankakow',NULL,NULL,NULL),('Павлин','id24099856',NULL,NULL,NULL),('Панда','blandger',NULL,NULL,NULL),('Паника','tanyamaslyuk',NULL,NULL,NULL),('Паника (ББ)','anna_blokhina',NULL,NULL,NULL),('ПП','shokotko_iren',NULL,NULL,NULL),('Пушкин','kirilord',NULL,NULL,NULL),('Равик','',NULL,NULL,NULL),('Рамино','id12904754',NULL,NULL,NULL),('Редиска','',NULL,NULL,NULL),('Роммель','rommel2',NULL,NULL,NULL),('Ронни','id3174572',NULL,NULL,NULL),('Свердлова','anya_sverdlova',NULL,NULL,NULL),('Сигрун','id6056686',NULL,NULL,NULL),('Сова','inna_markus',NULL,NULL,NULL),('Стриптизер','vl_kh',NULL,NULL,NULL),('Сутенерша','id24021994ann',NULL,NULL,NULL),('Термит','rinkishi',NULL,NULL,NULL),('Тип','creepisangry',NULL,NULL,NULL),('Тор','id14090560',NULL,NULL,NULL),('Точка','',NULL,NULL,NULL),('Треф','',NULL,NULL,NULL),('Турист','tourist_petya',NULL,NULL,NULL),('Уайт','komdon',NULL,NULL,NULL),('Ульфрик','p.shok',NULL,NULL,NULL),('Файна','',NULL,NULL,NULL),('Фантом','fantomchuk',NULL,NULL,NULL),('Фетиш','id23212281',NULL,NULL,NULL),('Флоркинс','florkins',NULL,NULL,NULL),('Хилависа','komdon',NULL,NULL,NULL),('Хирург','id17668773',NULL,NULL,NULL),('Цапля','id27309668',NULL,NULL,NULL),('Шпион','id221551509',NULL,NULL,NULL),('Эльза','z_vlada',NULL,NULL,NULL),('Эргноор','',NULL,NULL,NULL),('Южная','yujna_ya',NULL,NULL,NULL),('Юрист','komdon',NULL,NULL,NULL);
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-27 20:17:52
