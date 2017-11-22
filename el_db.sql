-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- 主機: 127.0.0.1
-- 產生時間： 2017-11-22 14:58:16
-- 伺服器版本: 10.1.22-MariaDB
-- PHP 版本： 7.1.4

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

-- --------------------------------------------------------

--
-- 資料表結構 `material`
--

CREATE TABLE `material` (
  `cid` int(8) NOT NULL,
  `mateID` int(8) NOT NULL,
  `mateName` varchar(32) NOT NULL,
  `mateDesc` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `optD` varchar(64) NOT NULL,
  `ans` varchar(64) NOT NULL,
  `question` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 資料表結構 `quiz`
--

CREATE TABLE `quiz` (
  `QID` int(8) NOT NULL,
  `duration` time(6) NOT NULL,
  `startTime` time(6) NOT NULL,
  `Date` date NOT NULL,
  `cid` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 資料表結構 `result`
--

CREATE TABLE `result` (
  `QID` int(8) NOT NULL,
  `id` int(8) NOT NULL,
  `score` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 資料表結構 `user`
--

CREATE TABLE `user` (
  `id` int(16) NOT NULL,
  `username` varchar(16) NOT NULL,
  `role` varchar(10) NOT NULL,
  `password` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
-- 資料表索引 `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`QID`),
  ADD KEY `cid` (`cid`),
  ADD KEY `QID` (`QID`);

--
-- 資料表索引 `result`
--
ALTER TABLE `result`
  ADD KEY `QID` (`QID`),
  ADD KEY `id` (`id`);

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
  MODIFY `cid` int(8) NOT NULL AUTO_INCREMENT;
--
-- 使用資料表 AUTO_INCREMENT `material`
--
ALTER TABLE `material`
  MODIFY `mateID` int(8) NOT NULL AUTO_INCREMENT;
--
-- 使用資料表 AUTO_INCREMENT `question`
--
ALTER TABLE `question`
  MODIFY `questID` int(8) NOT NULL AUTO_INCREMENT;
--
-- 使用資料表 AUTO_INCREMENT `quiz`
--
ALTER TABLE `quiz`
  MODIFY `QID` int(8) NOT NULL AUTO_INCREMENT;
--
-- 使用資料表 AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(16) NOT NULL AUTO_INCREMENT;
--
-- 已匯出資料表的限制(Constraint)
--

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
-- 資料表的 Constraints `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `result_ibfk_1` FOREIGN KEY (`QID`) REFERENCES `quiz` (`QID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `result_ibfk_2` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
