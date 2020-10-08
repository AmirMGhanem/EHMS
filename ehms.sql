SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;



CREATE TABLE address (
  addresscode int(5) NOT NULL,
  city varchar(255) NOT NULL,
  street varchar(255) NOT NULL,
  housenum int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE allergy (
  name varchar(255) NOT NULL,
  medicinenum int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE channell (
  channelnum int(11) NOT NULL,
  patientid varchar(255) NOT NULL,
  therapistid varchar(255) NOT NULL,
  username varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE meal (
  name varchar(255) NOT NULL,
  weight int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE medicine (
  medicinenum int(11) NOT NULL,
  name varchar(255) NOT NULL,
  type varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE meeting (
  num int(5) NOT NULL,
  name varchar(255) NOT NULL,
  addresscode int(5) NOT NULL,
  date date NOT NULL,
  time varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE notification (
  num int(11) NOT NULL,
  request_type varchar(25) NOT NULL,
  request_desc varchar(255) NOT NULL,
  patient_id varchar(255) NOT NULL,
  patient_name varchar(255) NOT NULL,
  date date NOT NULL,
  therapist varchar(255) NOT NULL,
  istreated varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE patient (
  id varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE patient_allergy (
  patientid varchar(255) NOT NULL,
  allergyname varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE patient_meal (
  patientid varchar(255) NOT NULL,
  mealname varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE patient_medicine (
  patientid varchar(255) NOT NULL,
  medicinenum int(11) NOT NULL,
  timesperday int(11) NOT NULL,
  duration int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE patient_meeting (
  patientid varchar(255) NOT NULL,
  meetingnum int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE person (
  id varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  address int(11) NOT NULL,
  gender varchar(255) NOT NULL,
  birthdate date NOT NULL,
  contactno varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE tblusers (
  username varchar(25) NOT NULL,
  password varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO tblusers (username, `password`) VALUES
('˻˾̇̃̈', '˻˾̇̃̈'),
('˻̇̃̌', '˻̇̃̌'),
('˻̆˻̇', '˻̆˻̇'),
('˻˻˻', '˻˻˻'),
('̉̍̈˻̎', '̉̍̈˻̎'),
('˻˾̇̄̃̈ˋˌˍ', '˻̍˾');

CREATE TABLE therapist (
  id varchar(255) NOT NULL,
  dateworkstart date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE therapist_schedule (
  therapistid varchar(255) NOT NULL,
  schedid int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE workschedule (
  therapistid varchar(9) NOT NULL,
  day varchar(255) NOT NULL,
  shift int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;


ALTER TABLE address
  ADD PRIMARY KEY (addresscode);

ALTER TABLE allergy
  ADD PRIMARY KEY (name),
  ADD KEY medicinenum (medicinenum);

ALTER TABLE channell
  ADD PRIMARY KEY (channelnum);

ALTER TABLE meal
  ADD PRIMARY KEY (name);

ALTER TABLE medicine
  ADD PRIMARY KEY (medicinenum);

ALTER TABLE meeting
  ADD PRIMARY KEY (num),
  ADD KEY addresscode (addresscode);

ALTER TABLE notification
  ADD PRIMARY KEY (num);

ALTER TABLE patient
  ADD PRIMARY KEY (id);

ALTER TABLE patient_allergy
  ADD PRIMARY KEY (patientid,allergyname),
  ADD KEY allergyname (allergyname);

ALTER TABLE patient_meal
  ADD PRIMARY KEY (patientid,mealname),
  ADD KEY mealname (mealname);

ALTER TABLE patient_medicine
  ADD PRIMARY KEY (patientid,medicinenum),
  ADD KEY medicinenum (medicinenum);

ALTER TABLE patient_meeting
  ADD PRIMARY KEY (patientid,meetingnum) USING BTREE,
  ADD KEY meetingnum (meetingnum);

ALTER TABLE person
  ADD PRIMARY KEY (id),
  ADD KEY address (address);

ALTER TABLE therapist
  ADD PRIMARY KEY (id);

ALTER TABLE therapist_schedule
  ADD PRIMARY KEY (therapistid,schedid),
  ADD KEY schedid (schedid);

ALTER TABLE workschedule
  ADD KEY therapistid (therapistid);


ALTER TABLE channell
  MODIFY channelnum int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE medicine
  MODIFY medicinenum int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE meeting
  MODIFY num int(5) NOT NULL AUTO_INCREMENT;

ALTER TABLE notification
  MODIFY num int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE allergy
  ADD CONSTRAINT allergy_ibfk_1 FOREIGN KEY (medicinenum) REFERENCES medicine (medicinenum);

ALTER TABLE meeting
  ADD CONSTRAINT meeting_ibfk_1 FOREIGN KEY (addresscode) REFERENCES address (addresscode);

ALTER TABLE patient
  ADD CONSTRAINT patient_ibfk_1 FOREIGN KEY (id) REFERENCES person (id);

ALTER TABLE patient_allergy
  ADD CONSTRAINT patient_allergy_ibfk_1 FOREIGN KEY (patientid) REFERENCES patient (id),
  ADD CONSTRAINT patient_allergy_ibfk_2 FOREIGN KEY (allergyname) REFERENCES allergy (name);

ALTER TABLE patient_meal
  ADD CONSTRAINT patient_meal_ibfk_1 FOREIGN KEY (patientid) REFERENCES patient (id),
  ADD CONSTRAINT patient_meal_ibfk_2 FOREIGN KEY (mealname) REFERENCES meal (name);

ALTER TABLE patient_medicine
  ADD CONSTRAINT patient_medicine_ibfk_1 FOREIGN KEY (patientid) REFERENCES patient (id),
  ADD CONSTRAINT patient_medicine_ibfk_2 FOREIGN KEY (medicinenum) REFERENCES medicine (medicinenum);

ALTER TABLE patient_meeting
  ADD CONSTRAINT patient_meeting_ibfk_1 FOREIGN KEY (patientid) REFERENCES patient (id),
  ADD CONSTRAINT patient_meeting_ibfk_2 FOREIGN KEY (meetingnum) REFERENCES meeting (num);

ALTER TABLE person
  ADD CONSTRAINT person_ibfk_1 FOREIGN KEY (address) REFERENCES address (addresscode),
  ADD CONSTRAINT person_ibfk_2 FOREIGN KEY (address) REFERENCES address (addresscode);

ALTER TABLE therapist
  ADD CONSTRAINT therapist_ibfk_1 FOREIGN KEY (id) REFERENCES person (id);
USE phpmyadmin;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
