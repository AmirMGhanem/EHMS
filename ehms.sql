-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 03, 2020 at 06:02 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ehms`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
  `addresscode` int(5) NOT NULL,
  `city` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `housenum` int(11) DEFAULT NULL,
  PRIMARY KEY (`addresscode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`addresscode`, `city`, `street`, `housenum`) VALUES
(2, 'maghar', 'albas97', 1548),
(11, 'maghar', 'albas97', 1548),
(111, 'maghar', 'albas97', 1548),
(213, 'maghar', 'albas97', 1548),
(222, 'maghar', 'albas97', 1548),
(1548, 'maghar', 'albas97', 1548),
(12213, 'maghar', 'albas97', 1548);

-- --------------------------------------------------------

--
-- Table structure for table `allergy`
--

DROP TABLE IF EXISTS `allergy`;
CREATE TABLE IF NOT EXISTS `allergy` (
  `name` varchar(255) NOT NULL,
  `medicinenum` int(11) NOT NULL,
  PRIMARY KEY (`name`),
  KEY `medicinenum` (`medicinenum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `channell`
--

DROP TABLE IF EXISTS `channell`;
CREATE TABLE IF NOT EXISTS `channell` (
  `channelnum` int(11) NOT NULL AUTO_INCREMENT,
  `patientid` varchar(255) NOT NULL,
  `therapistid` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`channelnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
CREATE TABLE IF NOT EXISTS `meal` (
  `name` varchar(255) NOT NULL,
  `weight` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
CREATE TABLE IF NOT EXISTS `medicine` (
  `medicinenum` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`medicinenum`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medicine`
--

INSERT INTO `medicine` (`medicinenum`, `name`, `type`) VALUES
(1, 'optalgen', 'Liquid'),
(2, 'reoletic', 'Capsules');

-- --------------------------------------------------------

--
-- Table structure for table `meeting`
--

DROP TABLE IF EXISTS `meeting`;
CREATE TABLE IF NOT EXISTS `meeting` (
  `num` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `addresscode` int(5) NOT NULL,
  `date` date NOT NULL,
  `time` varchar(255) NOT NULL,
  PRIMARY KEY (`num`),
  KEY `addresscode` (`addresscode`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `request_type` varchar(25) NOT NULL,
  `request_desc` varchar(255) NOT NULL,
  `patient_id` varchar(255) NOT NULL,
  `patient_name` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `therapist` varchar(255) NOT NULL,
  `istreated` varchar(25) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`num`, `request_type`, `request_desc`, `patient_id`, `patient_name`, `date`, `therapist`, `istreated`) VALUES
(1, 'Low Urgency', 'Patient-> test testt Needs Water', '123123', 'test testt', '2020-10-01', '', 'false'),
(2, 'Critical Urgency', ' Patient-> test testt Needs YOU', '123123', 'test testt', '2020-10-01', '', 'false'),
(3, 'Medium Urgency', 'Patient-> test testt Needs Meal', '123123', 'test testt', '2020-10-01', '', 'false'),
(4, 'Low Urgency', 'Patient-> test testt Needs Water', '123123', 'test testt', '2020-10-01', '', 'false'),
(5, 'Medium Urgency', 'Patient-> test testt Needs Toilet', '123123', 'test testt', '2020-10-01', '', 'false'),
(6, 'Low Urgency', 'Patient-> alam aslan Needs Water', '123123', 'alam aslan', '2020-10-01', '', 'false'),
(7, 'Critical Urgency', ' Patient-> alam aslan Needs YOU', '123123', 'alam aslan', '2020-10-01', '', 'false'),
(8, 'Medium Urgency', 'Patient-> alam aslan Needs Toilet', '123123', 'alam aslan', '2020-10-01', '', 'false'),
(9, 'Medium Urgency', 'Patient-> alam aslan Needs Toilet', '123123', 'alam aslan', '2020-10-01', '', 'false'),
(10, 'Medium Urgency', 'Patient-> alam aslan Needs Meal', '123123', 'alam aslan', '2020-10-01', '', 'false'),
(11, 'Critical Urgency', ' Patient-> alam aslan Needs YOU', '123123', 'alam aslan', '2020-10-01', '', 'false'),
(12, 'Low Urgency', 'Patient-> alam aslan Needs Water', '123123', 'alam aslan', '2020-10-01', '', 'false'),
(13, 'Medium Urgency', 'Patient-> alam aslan Needs Toilet', '123123', 'alam aslan', '2020-10-01', '', 'false'),
(14, 'Critical Urgency', ' Patient-> alam aslan Needs YOU', '123123', 'alam aslan', '2020-10-01', '', 'false'),
(15, 'Medium Urgency', 'Patient-> alam aslan Needs Meal', '123123', 'alam aslan', '2020-10-01', '', 'false'),
(16, 'Low Urgency', 'Patient-> alam aslan Needs Water', '123123', 'alam aslan', '2020-10-01', '', 'false');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`) VALUES
('123123');

-- --------------------------------------------------------

--
-- Table structure for table `patient_allergy`
--

DROP TABLE IF EXISTS `patient_allergy`;
CREATE TABLE IF NOT EXISTS `patient_allergy` (
  `patientid` varchar(255) NOT NULL,
  `allergyname` varchar(255) NOT NULL,
  PRIMARY KEY (`patientid`,`allergyname`),
  KEY `allergyname` (`allergyname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `patient_meal`
--

DROP TABLE IF EXISTS `patient_meal`;
CREATE TABLE IF NOT EXISTS `patient_meal` (
  `patientid` varchar(255) NOT NULL,
  `mealname` varchar(255) NOT NULL,
  PRIMARY KEY (`patientid`,`mealname`),
  KEY `mealname` (`mealname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `patient_medicine`
--

DROP TABLE IF EXISTS `patient_medicine`;
CREATE TABLE IF NOT EXISTS `patient_medicine` (
  `patientid` varchar(255) NOT NULL,
  `medicinenum` int(11) NOT NULL,
  `timesperday` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  PRIMARY KEY (`patientid`,`medicinenum`),
  KEY `medicinenum` (`medicinenum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `patient_meeting`
--

DROP TABLE IF EXISTS `patient_meeting`;
CREATE TABLE IF NOT EXISTS `patient_meeting` (
  `patientid` varchar(255) NOT NULL,
  `meetingnum` int(11) NOT NULL,
  PRIMARY KEY (`patientid`,`meetingnum`) USING BTREE,
  KEY `meetingnum` (`meetingnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` int(11) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `birthdate` date NOT NULL,
  `contactno` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `address` (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id`, `name`, `address`, `gender`, `birthdate`, `contactno`) VALUES
('123123', 'alam aslan', 222, 'Male', '2020-10-06', '0521232131'),
('208913236', 'alam aslan', 1548, 'Male', '2020-09-28', '0502208594'),
('313258030', 'amir wayne', 111, 'Male', '2020-10-06', '0521231231231');

-- --------------------------------------------------------

--
-- Table structure for table `tblusers`
--

DROP TABLE IF EXISTS `tblusers`;
CREATE TABLE IF NOT EXISTS `tblusers` (
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblusers`
--

INSERT INTO `tblusers` (`username`, `password`) VALUES
('˻˾̇̃̈', '˻˾̇̃̈'),
('˻̇̃̌', '˻̇̃̌'),
('˻̆˻̇', '˻̆˻̇'),
('˻˻˻', '˻˻˻'),
('̉̍̈˻̎', '̉̍̈˻̎'),
('˻˾̇̄̃̈ˋˌˍ', '˻̍˾');

-- --------------------------------------------------------

--
-- Table structure for table `therapist`
--

DROP TABLE IF EXISTS `therapist`;
CREATE TABLE IF NOT EXISTS `therapist` (
  `id` varchar(255) NOT NULL,
  `dateworkstart` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `therapist`
--

INSERT INTO `therapist` (`id`, `dateworkstart`) VALUES
('208913236', '2020-09-29'),
('313258030', '2020-10-02');

-- --------------------------------------------------------

--
-- Table structure for table `therapist_schedule`
--

DROP TABLE IF EXISTS `therapist_schedule`;
CREATE TABLE IF NOT EXISTS `therapist_schedule` (
  `therapistid` varchar(255) NOT NULL,
  `schedid` int(11) NOT NULL,
  PRIMARY KEY (`therapistid`,`schedid`),
  KEY `schedid` (`schedid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `workschedule`
--

DROP TABLE IF EXISTS `workschedule`;
CREATE TABLE IF NOT EXISTS `workschedule` (
  `therapistid` varchar(9) NOT NULL,
  `day` varchar(255) NOT NULL,
  `shift` int(11) NOT NULL,
  KEY `therapistid` (`therapistid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `workschedule`
--

INSERT INTO `workschedule` (`therapistid`, `day`, `shift`) VALUES
('208913236', 'Sunday', 1),
('208913236', 'Monday', 2),
('208913236', 'Tuesday', 3),
('208913236', 'Wednesday', 2),
('208913236', 'Thursday', 1),
('208913236', 'Friday', 2),
('208913236', 'Saturday', 3),
('313258030', 'Sunday', 3),
('313258030', 'Monday', 2),
('313258030', 'Tuesday', 1),
('313258030', 'Wednesday', 2),
('313258030', 'Thursday', 3),
('313258030', 'Friday', 1),
('313258030', 'Saturday', 3);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `allergy`
--
ALTER TABLE `allergy`
  ADD CONSTRAINT `allergy_ibfk_1` FOREIGN KEY (`medicinenum`) REFERENCES `medicine` (`medicinenum`);

--
-- Constraints for table `meeting`
--
ALTER TABLE `meeting`
  ADD CONSTRAINT `meeting_ibfk_1` FOREIGN KEY (`addresscode`) REFERENCES `address` (`addresscode`);

--
-- Constraints for table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`id`) REFERENCES `person` (`id`);

--
-- Constraints for table `patient_allergy`
--
ALTER TABLE `patient_allergy`
  ADD CONSTRAINT `patient_allergy_ibfk_1` FOREIGN KEY (`patientid`) REFERENCES `patient` (`id`),
  ADD CONSTRAINT `patient_allergy_ibfk_2` FOREIGN KEY (`allergyname`) REFERENCES `allergy` (`name`);

--
-- Constraints for table `patient_meal`
--
ALTER TABLE `patient_meal`
  ADD CONSTRAINT `patient_meal_ibfk_1` FOREIGN KEY (`patientid`) REFERENCES `patient` (`id`),
  ADD CONSTRAINT `patient_meal_ibfk_2` FOREIGN KEY (`mealname`) REFERENCES `meal` (`name`);

--
-- Constraints for table `patient_medicine`
--
ALTER TABLE `patient_medicine`
  ADD CONSTRAINT `patient_medicine_ibfk_1` FOREIGN KEY (`patientid`) REFERENCES `patient` (`id`),
  ADD CONSTRAINT `patient_medicine_ibfk_2` FOREIGN KEY (`medicinenum`) REFERENCES `medicine` (`medicinenum`);

--
-- Constraints for table `patient_meeting`
--
ALTER TABLE `patient_meeting`
  ADD CONSTRAINT `patient_meeting_ibfk_1` FOREIGN KEY (`patientid`) REFERENCES `patient` (`id`),
  ADD CONSTRAINT `patient_meeting_ibfk_2` FOREIGN KEY (`meetingnum`) REFERENCES `meeting` (`num`);

--
-- Constraints for table `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `person_ibfk_1` FOREIGN KEY (`address`) REFERENCES `address` (`addresscode`),
  ADD CONSTRAINT `person_ibfk_2` FOREIGN KEY (`address`) REFERENCES `address` (`addresscode`);

--
-- Constraints for table `therapist`
--
ALTER TABLE `therapist`
  ADD CONSTRAINT `therapist_ibfk_1` FOREIGN KEY (`id`) REFERENCES `person` (`id`);

--
-- Constraints for table `therapist_schedule`
--
ALTER TABLE `therapist_schedule`
  ADD CONSTRAINT `therapist_schedule_ibfk_1` FOREIGN KEY (`therapistid`) REFERENCES `therapist` (`id`),
  ADD CONSTRAINT `therapist_schedule_ibfk_2` FOREIGN KEY (`schedid`) REFERENCES `workschedule` (`schedid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
