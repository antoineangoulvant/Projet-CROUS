-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 01 mai 2018 à 12:01
-- Version du serveur :  5.7.21
-- Version de PHP :  5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestioncrous`
--

-- --------------------------------------------------------

--
-- Structure de la table `bien`
--

DROP TABLE IF EXISTS `bien`;
CREATE TABLE IF NOT EXISTS `bien` (
  `id_bien` int(20) NOT NULL AUTO_INCREMENT,
  `id_proprietaire` int(20) NOT NULL,
  `adresse_bien` varchar(255) NOT NULL,
  `id_nature` int(20) NOT NULL,
  PRIMARY KEY (`id_bien`),
  KEY `fk_id_proprietaire` (`id_proprietaire`),
  KEY `fk_id_nature` (`id_nature`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `loue`
--

DROP TABLE IF EXISTS `loue`;
CREATE TABLE IF NOT EXISTS `loue` (
  `id_personne` int(20) NOT NULL,
  `id_bien` int(20) NOT NULL,
  `date_debut` date NOT NULL,
  `loyer` float NOT NULL,
  `periode` int(11) NOT NULL,
  KEY `fk_id_personne` (`id_personne`),
  KEY `fk_id_bien` (`id_bien`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `nature`
--

DROP TABLE IF EXISTS `nature`;
CREATE TABLE IF NOT EXISTS `nature` (
  `id_nature` int(20) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(20) NOT NULL,
  `pourcentage` float NOT NULL,
  PRIMARY KEY (`id_nature`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `id_personne` int(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `adresse_personne` varchar(255) NOT NULL,
  PRIMARY KEY (`id_personne`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bien`
--
ALTER TABLE `bien`
  ADD CONSTRAINT `fk_id_nature` FOREIGN KEY (`id_nature`) REFERENCES `nature` (`id_nature`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_id_proprietaire` FOREIGN KEY (`id_proprietaire`) REFERENCES `personne` (`id_personne`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `loue`
--
ALTER TABLE `loue`
  ADD CONSTRAINT `fk_id_bien` FOREIGN KEY (`id_bien`) REFERENCES `bien` (`id_bien`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_id_personne` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
