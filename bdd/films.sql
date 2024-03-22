-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 22 mars 2024 à 15:52
-- Version du serveur :  5.7.31
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `films`
--

-- --------------------------------------------------------

--
-- Structure de la table `comments`
--

DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
                                          `comment_id` int(11) NOT NULL AUTO_INCREMENT,
                                          `movie_id` int(11) DEFAULT NULL,
                                          `user_id` int(11) DEFAULT NULL,
                                          `corps` varchar(255) DEFAULT NULL,
                                          PRIMARY KEY (`comment_id`),
                                          KEY `movie_id` (`movie_id`),
                                          KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `comments`
--

INSERT INTO `comments` (`comment_id`, `movie_id`, `user_id`, `corps`) VALUES
                                                                          (5, 5, 72, 'Wowwww'),
                                                                          (7, 3, 72, 'Excellent film'),
                                                                          (8, 3, 73, 'On comprends rien'),
                                                                          (9, 2, 72, 'Bon film');

-- --------------------------------------------------------

--
-- Structure de la table `films`
--

DROP TABLE IF EXISTS `films`;
CREATE TABLE IF NOT EXISTS `films` (
                                       `movie_id` int(11) NOT NULL AUTO_INCREMENT,
                                       `name` varchar(255) NOT NULL,
                                       `release_date` date DEFAULT NULL,
                                       `description` text,
                                       `url` varchar(255) DEFAULT NULL,
                                       PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `films`
--

INSERT INTO `films` (`movie_id`, `name`, `release_date`, `description`, `url`) VALUES
                                                                                   (2, 'The Matrix', '1999-03-31', 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.', 'Matrix.webp'),
                                                                                   (3, 'Inception ', '2024-03-04', 'Meilleur film', 'inception.webp'),
                                                                                   (5, 'Interstellar', '2014-11-07', 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity\'s survival.', 'interstellar.jpeg'),
                                                                                   (7, 'Arrival', '2016-11-11', 'A linguist works with the military to communicate with alien lifeforms after twelve mysterious spacecraft appear around the world.', 'arrival.jpeg'),
                                                                                   (10, 'Moon', '2009-07-17', 'Astronaut Sam Bell has a quintessentially personal encounter toward the end of his three-year stint on the Moon, where he, working alongside his computer, GERTY, sends back to Earth parcels of a resource that has helped diminish our planet\'s power problems.', 'moon.webp'),
                                                                                   (15, 'test', NULL, 'test', 'test.jpg'),
                                                                                   (16, 'creed', NULL, 'Boxe', 'boce.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
                                       `user_id` int(11) NOT NULL AUTO_INCREMENT,
                                       `username` varchar(70) NOT NULL,
                                       `password` varchar(70) NOT NULL,
                                       `is_admin` tinyint(1) NOT NULL DEFAULT '0',
                                       `active` tinyint(1) NOT NULL DEFAULT '1',
                                       PRIMARY KEY (`user_id`),
                                       UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `is_admin`, `active`) VALUES
                                                                                  (1, 'john_doe', 'hashed_password_john', 1, 0),
                                                                                  (20, 'mo', 'mo', 1, 1),
                                                                                  (72, 'user1', 'ok', 0, 1),
                                                                                  (73, 'user2', 'ok', 1, 0),
                                                                                  (74, 'eric', 'ok', 0, 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `comments`
--
ALTER TABLE `comments`
    ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `films` (`movie_id`) ON DELETE CASCADE,
    ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
