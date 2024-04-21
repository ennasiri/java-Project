-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 16 avr. 2023 à 11:51
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ECELOCAR`
--

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

DROP TABLE IF EXISTS `avis`;
CREATE TABLE IF NOT EXISTS `avis` (
  `Id_CR` int(11) NOT NULL,
  `Id_L` int(11) NOT NULL,
  `Note` decimal(11,2) NOT NULL,
  `Commentaire` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `clientr`
--

DROP TABLE IF EXISTS `clientr`;
CREATE TABLE IF NOT EXISTS `clientr` (
  `Id_CR` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Mail` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nb_location` int(11) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `num_tel` varchar(20) NOT NULL,
  `carte_bleue` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`Mail`),
  UNIQUE KEY `UNIQUE` (`Id_CR`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `clientr`
--

INSERT INTO `clientr` (`Id_CR`, `Name`, `Mail`, `password`, `nb_location`, `age`, `num_tel`, `carte_bleue`) VALUES
(1, '', 'encore.test.gmail.com', '', '0', '0', '0', ''),
(14, 'Badr', 'Badr.ennasiri@edu.ece.fr', 'Badre2003@', NULL, '20', '0751565508', NULL),
(2, 'Hugo', 'Hugo@gmail.com', 'Hugo1234', '0', '21', '0699158408', Null),
(17, 'Abd Alhadi', 'Abdalhadi@gmail.com', 'AloueElfasi', NULL, '16', '0750037143', NULL),
(15, 'Ayoub', 'Ayoub.faham@edu.ece.fr', 'AyoubLwalidL3ziz', NULL, '46', '0616170502', NULL),
(3, '', 'test.gmail.com', '', '0', '0', '0', '');

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `Id_AD` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Mail` varchar(25) NOT NULL,
  `password` varchar(255) NOT NULL,
  `num_tel` varchar(15) NOT NULL,
  `nb_locat` int(11) NOT NULL DEFAULT '1',
  `age` int(11) NOT NULL,
  PRIMARY KEY (`Mail`),
  UNIQUE KEY `Id_AD` (`Id_AD`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`Id_AD`, `Name`, `Mail`, `password`, `num_tel`, `nb_locat`, `age`) VALUES
(1, 'Jon Holland', 'jdejarmy@gmail.com', 'lucy0001', '0612859432', 1, 30),
(2, 'Roberth Noldan', 'j.berthet@mail.fr', 'BlaBlaBla', '0770284332', 1, 26),
(3, 'Abdelhadi alaoui', 'abdelhadi20@gmail.com', 'admin3', '0780816268', 5, 20);

-- --------------------------------------------------------

--
-- Structure de la table `vehicules`
--

DROP TABLE IF EXISTS `vehicules`;
CREATE TABLE IF NOT EXISTS `vehicules` (
  `Id_L` int(11) NOT NULL AUTO_INCREMENT,
  `MailLoca` varchar(25) NOT NULL,
  `Photo1` varchar(255) NOT NULL,
  `Photo2` varchar(255) NOT NULL,
  `nomvoiture` varchar(255) NOT NULL,
  `description` text,
  `categorie` varchar(255) NOT NULL,
  `modele` varchar(255) NOT NULL,
  `Note` decimal(11,2) NOT NULL DEFAULT '5.00',
  `Prix` decimal(11,2) NOT NULL,
  `Capacite` int(11) NOT NULL,
  `nb_porte` int(11) NOT NULL,
  `nb_cheveaux` int(11) NOT NULL DEFAULT '0',
  `electrique` tinyint(1) NOT NULL,
  `automatique` tinyint(1) NOT NULL,
  PRIMARY KEY (`nomvoiture`),
  UNIQUE KEY `l_id` (`Id_L`),
  UNIQUE KEY `l_id_2` (`Id_l`),
  UNIQUE KEY `Id_L` (`Id_L`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `vehicules`
--

INSERT INTO `vehicules` (`Id_L`, `MailLoca`, `photo1` , `photo2` , `nomvoiture`, `description`, `categorie`,`modele`,`Note`,`Prix`,`Capacite`,`nb_porte`,`nb_cheveaux`,`electrique`,`automatique`) VALUES
(1, '1@gmail.com','images/1078707-Mercedes-AMG-G63-2K.jpg' , 'images/2mercedes-g63-amg-suv-gelendvagen-g63.jpg' , 'G63', 'G63 est', 'Mercedes','2020','4.5','150','5 per','4','250','0','1'),
(2, '2@gmail.com','images/gls1.jpg' , 'images/gls2.jpg' , 'GLS', 'GLS est', ' Mercedes','2019','4','120','6 pers','4','200','0','1'),
(3, '3@gmail.com','images/class1.jpg' , 'images/class2.jpg' , 'Class S', 'La Mercedes Classe S 2020 est la berline de luxe', 'Mercedes','2022','5','200','3','4','250','0','1'),
(4, '4@gmail.com','images/BMW_s1_1.jpg','images/BMW_s1_2.jpg','serie 3', 'La BMW Série 3 est', 'BMW','2023','4.9','190','5','4','280','0','1'),
(5, '5@gmail.com','images/bz4x1.jpg','images/bz4x2.jpg', 'bz4x', 'La Toyota bZ4X est un véhicule électrique', 'toyota','2018','4','50','6','4','150','1','1'),
(6, '6@gmail.com','images/206_1' ,'images/206_2', '206', 'la peugeot 206 est ', 'peugeot','2004','3.5','20','4 pers','4','6','0','0');
-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
CREATE TABLE IF NOT EXISTS `reservations` (
  `Id_R` int(11) NOT NULL AUTO_INCREMENT,
  `Mailclient` varchar(60) NOT NULL,
  `Nomclient` varchar(60) NOT NULL,
  `Datelocation` date NOT NULL,
  `Daterevien` date NOT NULL,
  `Id_L` int(11) NOT NULL,
  `Id_CR` int(11) NOT NULL,
  PRIMARY KEY (`Id_R`)
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reservations`
--

INSERT INTO `reservations` (`Id_R`, `Mailclient`, `Nomclient`, `Datelocation`, `Daterevien`, `Id_L`, `Id_CR`) VALUES
(1, 'Abdalhadi@gmail.com', 'Abd Alhadi', '2023-11-05', '3923-11-10', 2, 17),
(2, 'Abdalhadi@gmail.com', 'Abd Alhadi', '2023-11-05', '3923-11-10', 2, 17),
(3, 'Abdalhadi@gmail.com', 'Abd Alhadi', '2023-11-05', '3923-11-10', 2, 17),
(4, 'Abdalhadi@gmail.com', 'Abd Alhadi', '2023-11-05', '3923-11-10', 2, 17),
(5, 'Abdalhadi@gmail.com', 'Abd Alhadi', '2023-11-05', '3923-11-10', 2, 17),
(6, 'Abdalhadi@gmail.com', 'Abd Alhadi', '2023-11-05', '3923-11-10', 2, 17),
(7, 'Abdalhadi@gmail.com', 'Abd Alhadi', '2023-11-05', '3923-11-10', 2, 17),
(8, 'Abdalhadi@gmail.com', 'Abd Alhadi', '2023-11-05', '3923-11-10', 2, 17),
(9, 'Abdalhadi@gmail.com', 'Abd Alhadi', '2023-11-05', '3923-11-10', 2, 17),
(10, 'Abdalhadi@gmail.com', '', '2023-11-05', '3923-11-10', 4, 2),
(11, 'Badr.ennasiri@edu.ece.fr', 'Badr', '2023-11-05', '3923-11-10', 5, 14),
(12, 'Badr.ennasiri@edu.ece.fr', 'Badr', '2023-11-05', '3923-11-10', 5, 14),
(13, 'Badr.ennasiri@edu.ece.fr', 'Badr', '2023-11-05', '3923-11-10', 5, 14),
(14, 'Badr.ennasiri@edu.ece.fr', 'Badr', '2023-11-05', '3923-11-10', 5, 14),
(15, 'Ayoub.faham@edu.ece.fr', 'Ayoub', '2023-11-05', '3923-11-10', 6, 15),
(16, 'Ayoub.faham@edu.ece.fr', 'Ayoub', '2023-11-05', '3923-11-10', 6, 15),
(17, 'Ayoub.faham@edu.ece.fr', 'Ayoub', '2023-11-05', '3923-11-10', 6,15),
(18, 'Ayoub.faham@edu.ece.fr', 'Ayoub', '2023-11-05', '3923-11-10', 6, 15),
(19, 'Ayoub.faham@edu.ece.fr', 'Ayoub', '2023-11-05', '3923-11-10', 6, 15),
(20, 'Ayoub.faham@edu.ece.fr', 'Ayoub', '2023-11-05', '3923-11-10', 6, 15),
(21, 'Ayoub.faham@edu.ece.fr', 'Ayoub', '2023-11-05', '3923-11-10', 6, 15),
(22, 'Ayoub.faham@edu.ece.fr', 'Ayoub', '2023-11-05', '2023-11-10', 6, 215),
(23, 'Hugo@gmail.com', 'Hugo', '2023-11-05', '2023-11-10', 6, 2),
(24, 'Hugo@gmail.com', 'Hugo', '2023-11-05', '2023-11-10',6, 2),
(25, 'Hugo@gmail.com', 'Hugo', '2023-11-05', '2023-11-10', 6, 2),
(26, 'Hugo@gmail.com', 'Hugo', '2023-11-05', '2023-11-10', 6, 2),
(27, 'null', 'null', '2023-11-05', '2023-11-10', 1, 0),
(28, 'null', 'null', '2023-11-05', '2023-11-10', 1, 0),
(29, 'Bad.renassiri@edu.ece.fr', 'Badr', '2023-11-05', '2023-11-10', 3, 0),
(30, 'null', 'null', '2023-11-05', '2023-11-10', 3, 0),
(31, 'Ayoub.faham@edu.ece.fr', 'Ayoub', '2023-11-05', '2023-11-10', 7, 0),
(32, 'null', 'null', '2023-11-05', '2023-11-10', 12, 0),
(33, 'Abdalhadi@gmail.com', 'null', '2023-11-05', '2023-11-10', 6, 17),
(34, 'null', 'null', '2023-11-05', '2023-11-10', 8, 0),
(35, 'Hugo@gmail.com', 'null', '2023-11-05', '2023-11-10', 2, 17),
(36, 'null', 'null', '2023-11-05', '2023-11-10', 4 , 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
