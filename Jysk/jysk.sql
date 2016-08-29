-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 10, 2013 at 12:38 PM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jysk`
--

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

CREATE TABLE IF NOT EXISTS `article` (
  `articleid` int(11) NOT NULL DEFAULT '0',
  `articlename` varchar(30) DEFAULT NULL,
  `articleweight` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`articleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`articleid`, `articlename`, `articleweight`) VALUES
(1, 'mouse', 2),
(2, 'pad', 1),
(3, 'computer', 30),
(4, 'chair', 55),
(5, 'table', 34),
(6, 'cup', 5);

-- --------------------------------------------------------

--
-- Table structure for table `pallet`
--

CREATE TABLE IF NOT EXISTS `pallet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `palletid` int(11) DEFAULT NULL,
  `articleid` int(11) DEFAULT NULL,
  `articleamount` decimal(10,0) DEFAULT NULL,
  `palletweight` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `articleid` (`articleid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `pallet`
--

INSERT INTO `pallet` (`id`, `palletid`, `articleid`, `articleamount`, `palletweight`) VALUES
(1, 1, 2, 20, 200),
(2, 2, 1, 28, 250),
(3, 3, 6, 500, 22),
(4, 3, 6, 500, 22);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pallet`
--
ALTER TABLE `pallet`
  ADD CONSTRAINT `pallet_ibfk_1` FOREIGN KEY (`articleid`) REFERENCES `article` (`articleid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
