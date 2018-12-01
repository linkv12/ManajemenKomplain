-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2018 at 02:21 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tubes`
--

-- --------------------------------------------------------

--
-- Table structure for table `keluhan`
--

CREATE TABLE `keluhan` (
  `idKeluhan` varchar(6) NOT NULL,
  `idUser` varchar(6) NOT NULL,
  `temaKeluhan` varchar(20) NOT NULL,
  `deskripsi` text NOT NULL,
  `keluhanMendesak` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `keluhan`
--

INSERT INTO `keluhan` (`idKeluhan`, `idUser`, `temaKeluhan`, `deskripsi`, `keluhanMendesak`) VALUES
('000004', 'superA', 'Koneksi Tidak Stabil', 'youtube ?', 1),
('000005', 'superA', 'Koneksi Tidak Stabil', 'hhhhhh', 0),
('000006', 'superA', 'Internet Mati', 'sssssssssssssss', 0),
('000007', 'superA', 'Internet Mati', 'iya', 1),
('000008', 'superA', 'Internet Mati', 'jjj', 0),
('000009', 'superA', 'Bandwidh Down', 'why though', 1),
('000010', 'superA', 'Bandwidh Down', 'hahahahaha fuc yu', 0),
('000011', 'superA', 'Bandwidh Down', '12345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678123456781234567812345678', 0);

-- --------------------------------------------------------

--
-- Table structure for table `progres`
--

CREATE TABLE `progres` (
  `idKeluhan` varchar(6) NOT NULL,
  `idSuratTugas` varchar(6) NOT NULL,
  `idUser` varchar(6) DEFAULT NULL,
  `status` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `surattugas`
--

CREATE TABLE `surattugas` (
  `idSuratTugas` varchar(6) NOT NULL,
  `idKeluhan` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `idUser` varchar(6) NOT NULL,
  `idLevel` varchar(6) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `password` varchar(12) NOT NULL,
  `alamat` varchar(256) NOT NULL,
  `noTelp` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`idUser`, `idLevel`, `nama`, `password`, `alamat`, `noTelp`) VALUES
('superA', 'adm', 'konaq3', 'admin', 'ifi futsal2', '222222222222'),
('user01', 'usr', 'mememe', 'pass', 'kkkkkk', '019');

-- --------------------------------------------------------

--
-- Table structure for table `userlevel`
--

CREATE TABLE `userlevel` (
  `idLevel` varchar(6) NOT NULL,
  `level` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userlevel`
--

INSERT INTO `userlevel` (`idLevel`, `level`) VALUES
('adm', 'admin'),
('mgr', 'manager'),
('tkn', 'teknisi'),
('usr', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `keluhan`
--
ALTER TABLE `keluhan`
  ADD PRIMARY KEY (`idKeluhan`),
  ADD KEY `idUser` (`idUser`);

--
-- Indexes for table `progres`
--
ALTER TABLE `progres`
  ADD KEY `idKeluhan` (`idKeluhan`),
  ADD KEY `idSuratTugas` (`idSuratTugas`),
  ADD KEY `idUser` (`idUser`);

--
-- Indexes for table `surattugas`
--
ALTER TABLE `surattugas`
  ADD PRIMARY KEY (`idSuratTugas`),
  ADD KEY `idKeluhan` (`idKeluhan`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`),
  ADD KEY `idLevel` (`idLevel`);

--
-- Indexes for table `userlevel`
--
ALTER TABLE `userlevel`
  ADD PRIMARY KEY (`idLevel`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `keluhan`
--
ALTER TABLE `keluhan`
  ADD CONSTRAINT `keluhan_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Constraints for table `progres`
--
ALTER TABLE `progres`
  ADD CONSTRAINT `progres_ibfk_1` FOREIGN KEY (`idKeluhan`) REFERENCES `keluhan` (`idKeluhan`),
  ADD CONSTRAINT `progres_ibfk_2` FOREIGN KEY (`idSuratTugas`) REFERENCES `surattugas` (`idSuratTugas`),
  ADD CONSTRAINT `progres_ibfk_3` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Constraints for table `surattugas`
--
ALTER TABLE `surattugas`
  ADD CONSTRAINT `surattugas_ibfk_1` FOREIGN KEY (`idKeluhan`) REFERENCES `keluhan` (`idKeluhan`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`idLevel`) REFERENCES `userlevel` (`idLevel`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
