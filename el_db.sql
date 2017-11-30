-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- 主機: 127.0.0.1
-- 產生時間： 2017-11-30 21:28:57
-- 伺服器版本: 10.1.26-MariaDB
-- PHP 版本： 7.0.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `el_db`
--

-- --------------------------------------------------------

--
-- 資料表結構 `course`
--

CREATE TABLE `course` (
  `cid` int(8) NOT NULL,
  `cname` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `course`
--

INSERT INTO `course` (`cid`, `cname`) VALUES
(1, 'ITP12412 JSP'),
(2, 'COURSE 2'),
(3, 'aa');

-- --------------------------------------------------------

--
-- 資料表結構 `courseregistration`
--

CREATE TABLE `courseregistration` (
  `id` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `uid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `courseregistration`
--

INSERT INTO `courseregistration` (`id`, `cid`, `uid`) VALUES
(2, 1, 3);

-- --------------------------------------------------------

--
-- 資料表結構 `material`
--

CREATE TABLE `material` (
  `cid` int(8) NOT NULL,
  `mateID` int(8) NOT NULL,
  `mateName` varchar(255) NOT NULL,
  `mateDesc` varchar(32) NOT NULL,
  `Visibility` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `material`
--

INSERT INTO `material` (`cid`, `mateID`, `mateName`, `mateDesc`, `Visibility`) VALUES
(1, 1, 'ITP4507-assignment-1718_final.pdf', 'test', 1);

-- --------------------------------------------------------

--
-- 資料表結構 `question`
--

CREATE TABLE `question` (
  `questID` int(8) NOT NULL,
  `QID` int(8) NOT NULL,
  `optA` varchar(64) NOT NULL,
  `optB` varchar(64) NOT NULL,
  `optC` varchar(64) NOT NULL,
  `ans` varchar(64) NOT NULL,
  `question` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `question`
--

INSERT INTO `question` (`questID`, `QID`, `optA`, `optB`, `optC`, `ans`, `question`) VALUES
(1, 4, '1', '2', '3', 'A', 'choose a '),
(2, 4, '1', '2', '3', 'B', 'choose B'),
(3, 4, '1', '32', '2', 'C', 'ans = c'),
(4, 4, '1', '32', '2', 'A', 'ans = A'),
(5, 4, '1', '32', '2', 'A', 'ans = A'),
(6, 4, 'asfasfasf', 'asfasf', 'asfasf', 'C', 'choose c');

-- --------------------------------------------------------

--
-- 資料表結構 `question_pool`
--

CREATE TABLE `question_pool` (
  `questID` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `optA` varchar(255) NOT NULL,
  `optB` varchar(255) NOT NULL,
  `optC` varchar(255) NOT NULL,
  `ans` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `question_pool`
--

INSERT INTO `question_pool` (`questID`, `question`, `optA`, `optB`, `optC`, `ans`) VALUES
(1, 'A...............', '1', '2', '3', 'A'),
(2, 'B...............', '1', '2', '3', 'B');

-- --------------------------------------------------------

--
-- 資料表結構 `quiz`
--

CREATE TABLE `quiz` (
  `QID` int(8) NOT NULL,
  `duration` int(3) NOT NULL,
  `startDate` date NOT NULL,
  `cid` int(8) NOT NULL,
  `attemptTime` int(2) NOT NULL,
  `endDate` date NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `quiz`
--

INSERT INTO `quiz` (`QID`, `duration`, `startDate`, `cid`, `attemptTime`, `endDate`, `description`) VALUES
(3, 2, '2017-11-25', 1, 2, '2017-12-14', 'test'),
(4, 1, '2017-11-24', 1, 3, '2017-12-28', 'program quiz');

-- --------------------------------------------------------

--
-- 資料表結構 `quizregistration`
--

CREATE TABLE `quizregistration` (
  `id` int(11) NOT NULL,
  `qid` int(11) NOT NULL,
  `uid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `quizregistration`
--

INSERT INTO `quizregistration` (`id`, `qid`, `uid`) VALUES
(8, 4, 3);

-- --------------------------------------------------------

--
-- 資料表結構 `result`
--

CREATE TABLE `result` (
  `id` int(11) NOT NULL,
  `qid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `score` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `result`
--

INSERT INTO `result` (`id`, `qid`, `uid`, `score`) VALUES
(1, 4, 3, 20),
(6, 4, 3, 80);

-- --------------------------------------------------------

--
-- 資料表結構 `user`
--

CREATE TABLE `user` (
  `id` int(16) NOT NULL,
  `username` varchar(16) NOT NULL,
  `role` varchar(10) NOT NULL,
  `password` varchar(16) NOT NULL,
  `email` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `user`
--

INSERT INTO `user` (`id`, `username`, `role`, `password`, `email`) VALUES
(1, 'admin', 'ADMIN', '123', '1@gmail.com'),
(2, 'teacher', 'TEACHER', '123', '11@gmail.com'),
(3, 'student', 'STUDENT', '123', '44@gmail.com');

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`cid`),
  ADD UNIQUE KEY `cid` (`cid`),
  ADD KEY `cid_2` (`cid`);

--
-- 資料表索引 `courseregistration`
--
ALTER TABLE `courseregistration`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cid` (`cid`),
  ADD KEY `uid` (`uid`);

--
-- 資料表索引 `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`mateID`),
  ADD KEY `cid` (`cid`);

--
-- 資料表索引 `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`questID`),
  ADD KEY `QID` (`QID`);

--
-- 資料表索引 `question_pool`
--
ALTER TABLE `question_pool`
  ADD PRIMARY KEY (`questID`);

--
-- 資料表索引 `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`QID`),
  ADD KEY `cid` (`cid`),
  ADD KEY `QID` (`QID`),
  ADD KEY `QID_2` (`QID`);

--
-- 資料表索引 `quizregistration`
--
ALTER TABLE `quizregistration`
  ADD PRIMARY KEY (`id`),
  ADD KEY `qid` (`qid`),
  ADD KEY `uid` (`uid`);

--
-- 資料表索引 `result`
--
ALTER TABLE `result`
  ADD PRIMARY KEY (`id`),
  ADD KEY `qid` (`qid`),
  ADD KEY `uid` (`uid`);

--
-- 資料表索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- 在匯出的資料表使用 AUTO_INCREMENT
--

--
-- 使用資料表 AUTO_INCREMENT `course`
--
ALTER TABLE `course`
  MODIFY `cid` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用資料表 AUTO_INCREMENT `courseregistration`
--
ALTER TABLE `courseregistration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- 使用資料表 AUTO_INCREMENT `material`
--
ALTER TABLE `material`
  MODIFY `mateID` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用資料表 AUTO_INCREMENT `question`
--
ALTER TABLE `question`
  MODIFY `questID` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- 使用資料表 AUTO_INCREMENT `question_pool`
--
ALTER TABLE `question_pool`
  MODIFY `questID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表 AUTO_INCREMENT `quiz`
--
ALTER TABLE `quiz`
  MODIFY `QID` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- 使用資料表 AUTO_INCREMENT `quizregistration`
--
ALTER TABLE `quizregistration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- 使用資料表 AUTO_INCREMENT `result`
--
ALTER TABLE `result`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- 使用資料表 AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(16) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 已匯出資料表的限制(Constraint)
--

--
-- 資料表的 Constraints `courseregistration`
--
ALTER TABLE `courseregistration`
  ADD CONSTRAINT `courseregistration_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`),
  ADD CONSTRAINT `courseregistration_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`id`);

--
-- 資料表的 Constraints `material`
--
ALTER TABLE `material`
  ADD CONSTRAINT `material_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 資料表的 Constraints `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`QID`) REFERENCES `quiz` (`QID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 資料表的 Constraints `quiz`
--
ALTER TABLE `quiz`
  ADD CONSTRAINT `quiz_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 資料表的 Constraints `quizregistration`
--
ALTER TABLE `quizregistration`
  ADD CONSTRAINT `quizregistration_ibfk_1` FOREIGN KEY (`qid`) REFERENCES `quiz` (`QID`),
  ADD CONSTRAINT `quizregistration_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`id`);

--
-- 資料表的 Constraints `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `result_ibfk_1` FOREIGN KEY (`qid`) REFERENCES `quiz` (`QID`),
  ADD CONSTRAINT `result_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
