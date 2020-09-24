-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 24, 2020 at 11:45 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

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

CREATE TABLE `address` (
  `addresscode` int(5) NOT NULL,
  `city` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `housenum` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`addresscode`, `city`, `street`, `housenum`) VALUES
(2, 'dfssdf', 'dscfsd', 22);

-- --------------------------------------------------------

--
-- Table structure for table `allergy`
--

CREATE TABLE `allergy` (
  `name` varchar(255) NOT NULL,
  `medicinenum` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `channell`
--

CREATE TABLE `channell` (
  `channelnum` int(11) NOT NULL,
  `patientid` varchar(255) NOT NULL,
  `therapistid` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `meal`
--

CREATE TABLE `meal` (
  `name` varchar(255) NOT NULL,
  `weight` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `meal`
--

INSERT INTO `meal` (`name`, `weight`) VALUES
('bbb', 234);

-- --------------------------------------------------------

--
-- Table structure for table `medicine`
--

CREATE TABLE `medicine` (
  `medicinenum` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `timesperday` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `meeting`
--

CREATE TABLE `meeting` (
  `num` int(5) NOT NULL,
  `name` varchar(255) NOT NULL,
  `addresscode` int(5) NOT NULL,
  `date` date NOT NULL,
  `time` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `num` int(11) NOT NULL,
  `request_type` varchar(25) NOT NULL,
  `request_desc` varchar(255) NOT NULL,
  `patient_id` varchar(255) NOT NULL,
  `patient_name` varchar(255) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`num`, `request_type`, `request_desc`, `patient_id`, `patient_name`, `date`) VALUES
(1, 'Low Urgency', 'Patient-> sdasdadsa dsadsadas Needs Water', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(2, 'Medium Urgency', 'Patient-> sdasdadsa dsadsadas Needs Meal', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(3, 'Medium Urgency', 'Patient-> sdasdadsa dsadsadas Needs Meal', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(4, 'Medium Urgency', 'Patient-> sdasdadsa dsadsadas Needs Meal', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(5, 'Critical Urgency', ' Patient-> sdasdadsa dsadsadas Needs YOU', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(6, 'Critical Urgency', ' Patient-> sdasdadsa dsadsadas Needs YOU', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(7, 'Critical Urgency', ' Patient-> sdasdadsa dsadsadas Needs YOU', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(8, 'Critical Urgency', ' Patient-> sdasdadsa dsadsadas Needs YOU', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(9, 'Critical Urgency', ' Patient-> sdasdadsa dsadsadas Needs YOU', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(10, 'Critical Urgency', ' Patient-> sdasdadsa dsadsadas Needs YOU', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(11, 'Critical Urgency', ' Patient-> sdasdadsa dsadsadas Needs YOU', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(12, 'Critical Urgency', ' Patient-> sdasdadsa dsadsadas Needs YOU', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(13, 'Critical Urgency', ' Patient-> sdasdadsa dsadsadas Needs YOU', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(14, 'Critical Urgency', ' Patient-> sdasdadsa dsadsadas Needs YOU', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(15, 'Medium Urgency', 'Patient-> sdasdadsa dsadsadas Needs Toilet', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(16, 'Medium Urgency', 'Patient-> sdasdadsa dsadsadas Needs Toilet', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(17, 'Medium Urgency', 'Patient-> sdasdadsa dsadsadas Needs Toilet', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(18, 'Medium Urgency', 'Patient-> sdasdadsa dsadsadas Needs Toilet', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(19, 'Medium Urgency', 'Patient-> sdasdadsa dsadsadas Needs Toilet', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(20, 'Medium Urgency', 'Patient-> sdasdadsa dsadsadas Needs Toilet', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(21, 'Medium Urgency', 'Patient-> sdasdadsa dsadsadas Needs Toilet', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(22, 'Medium Urgency', 'Patient-> sdasdadsa dsadsadas Needs Toilet', '123412341', 'sdasdadsa dsadsadas', '2020-09-23'),
(23, 'Medium Urgency', 'Patient-> sdasdadsa dsadsadas Needs Toilet', '123412341', 'sdasdadsa dsadsadas', '2020-09-23');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`) VALUES
('123412341');

-- --------------------------------------------------------

--
-- Table structure for table `patient_allergy`
--

CREATE TABLE `patient_allergy` (
  `patientid` varchar(255) NOT NULL,
  `allergyname` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `patient_meal`
--

CREATE TABLE `patient_meal` (
  `patientid` varchar(255) NOT NULL,
  `mealname` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient_meal`
--

INSERT INTO `patient_meal` (`patientid`, `mealname`) VALUES
('123412341', 'bbb');

-- --------------------------------------------------------

--
-- Table structure for table `patient_medicine`
--

CREATE TABLE `patient_medicine` (
  `patientid` varchar(255) NOT NULL,
  `medicinenum` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `patient_meeting`
--

CREATE TABLE `patient_meeting` (
  `patientid` varchar(255) NOT NULL,
  `meetingnum` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` int(11) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `birthdate` date NOT NULL,
  `contactno` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id`, `name`, `address`, `gender`, `birthdate`, `contactno`) VALUES
('123412341', 'sdasdadsa dsadsadas', 2, 'Male', '2020-09-09', '05221321321213');

-- --------------------------------------------------------

--
-- Table structure for table `therapist`
--

CREATE TABLE `therapist` (
  `id` varchar(255) NOT NULL,
  `dateworkstart` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `therapist_schedule`
--

CREATE TABLE `therapist_schedule` (
  `therapistid` varchar(255) NOT NULL,
  `schedid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE `userinfo` (
  `username` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `patientid` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `workschedule`
--

CREATE TABLE `workschedule` (
  `schedid` int(11) NOT NULL,
  `workday` date NOT NULL,
  `hours` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`addresscode`);

--
-- Indexes for table `allergy`
--
ALTER TABLE `allergy`
  ADD PRIMARY KEY (`name`),
  ADD KEY `medicinenum` (`medicinenum`);

--
-- Indexes for table `channell`
--
ALTER TABLE `channell`
  ADD PRIMARY KEY (`channelnum`);

--
-- Indexes for table `meal`
--
ALTER TABLE `meal`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`medicinenum`);

--
-- Indexes for table `meeting`
--
ALTER TABLE `meeting`
  ADD PRIMARY KEY (`num`),
  ADD KEY `addresscode` (`addresscode`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`num`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patient_allergy`
--
ALTER TABLE `patient_allergy`
  ADD PRIMARY KEY (`patientid`,`allergyname`),
  ADD KEY `allergyname` (`allergyname`);

--
-- Indexes for table `patient_meal`
--
ALTER TABLE `patient_meal`
  ADD PRIMARY KEY (`patientid`,`mealname`),
  ADD KEY `mealname` (`mealname`);

--
-- Indexes for table `patient_medicine`
--
ALTER TABLE `patient_medicine`
  ADD PRIMARY KEY (`patientid`,`medicinenum`),
  ADD KEY `medicinenum` (`medicinenum`);

--
-- Indexes for table `patient_meeting`
--
ALTER TABLE `patient_meeting`
  ADD PRIMARY KEY (`patientid`,`meetingnum`) USING BTREE,
  ADD KEY `meetingnum` (`meetingnum`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`),
  ADD KEY `address` (`address`);

--
-- Indexes for table `therapist`
--
ALTER TABLE `therapist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `therapist_schedule`
--
ALTER TABLE `therapist_schedule`
  ADD PRIMARY KEY (`therapistid`,`schedid`),
  ADD KEY `schedid` (`schedid`);

--
-- Indexes for table `userinfo`
--
ALTER TABLE `userinfo`
  ADD PRIMARY KEY (`username`),
  ADD KEY `patientid` (`patientid`);

--
-- Indexes for table `workschedule`
--
ALTER TABLE `workschedule`
  ADD PRIMARY KEY (`schedid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `channell`
--
ALTER TABLE `channell`
  MODIFY `channelnum` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `medicine`
--
ALTER TABLE `medicine`
  MODIFY `medicinenum` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `meeting`
--
ALTER TABLE `meeting`
  MODIFY `num` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `num` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `workschedule`
--
ALTER TABLE `workschedule`
  MODIFY `schedid` int(11) NOT NULL AUTO_INCREMENT;

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

--
-- Constraints for table `userinfo`
--
ALTER TABLE `userinfo`
  ADD CONSTRAINT `userinfo_ibfk_1` FOREIGN KEY (`patientid`) REFERENCES `patient` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
