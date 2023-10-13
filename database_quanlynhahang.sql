-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 13, 2023 at 11:10 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `database_quanlynhahang`
--

-- --------------------------------------------------------

--
-- Table structure for table `goimon`
--

CREATE TABLE `goimon` (
  `MaTang` text NOT NULL,
  `MaBan` text NOT NULL,
  `MaMonAn` text NOT NULL,
  `TenMonAn` text NOT NULL,
  `DonGia` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `menu_nhahang`
--

CREATE TABLE `menu_nhahang` (
  `MaMonAn` text NOT NULL,
  `TenMonAn` text NOT NULL,
  `DonGia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `menu_nhahang`
--

INSERT INTO `menu_nhahang` (`MaMonAn`, `TenMonAn`, `DonGia`) VALUES
('SP1', 'Lẩu thập cẩm 2 người', 179000),
('SP2', 'Lẩu hải sản 2 người', 179000),
('SP3', 'Lẩu thái chua cay 2 người', 179000),
('SP4', 'Kimbap', 36000),
('SP5', 'Kimbap chiên', 40000),
('SP6', 'Ngô chiên', 25000),
('SP7', 'Phở bò', 40000),
('SP8', 'Cơm gà chiên', 35000),
('SP9', 'Cơm rang dưa bò', 35000),
('SP10', 'Bún chả', 35000),
('SP11', 'Coca cola', 15000),
('SP12', 'Sting', 15000),
('SP13', 'Trà sữa trân châu', 25000),
('SP14', 'Sting dâu', 15000),
('SP15', 'Nước cam ép nguyên chất', 25000),
('SP16', 'Sinh tố bơ', 30000),
('SP17', 'Gà hầm thuốc bắc', 150000);

-- --------------------------------------------------------

--
-- Table structure for table `status_banan`
--

CREATE TABLE `status_banan` (
  `MaTang` text NOT NULL,
  `MaBan` text NOT NULL,
  `TrangThai` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `status_banan`
--

INSERT INTO `status_banan` (`MaTang`, `MaBan`, `TrangThai`) VALUES
('1', '1', 'active'),
('1', '2', 'active'),
('1', '3', 'active'),
('1', '4', 'active'),
('1', '5', 'active'),
('1', '6', 'active'),
('1', '7', 'active'),
('1', '8', 'active'),
('1', '9', 'active'),
('1', '10', 'active'),
('1', '11', 'active'),
('1', '12', 'active'),
('2', '1', 'active'),
('2', '2', 'active'),
('2', '3', 'active'),
('2', '4', 'active'),
('2', '5', 'active'),
('2', '6', 'active'),
('2', '7', 'active'),
('2', '8', 'active'),
('2', '9', 'active'),
('2', '10', 'active'),
('2', '11', 'active'),
('2', '12', 'active'),
('3', '1', 'active'),
('3', '2', 'active'),
('3', '3', 'active'),
('3', '4', 'active'),
('3', '5', 'active'),
('3', '6', 'active'),
('3', '7', 'active'),
('3', '8', 'active'),
('3', '9', 'active'),
('3', '10', 'active'),
('3', '11', 'active'),
('3', '12', 'active');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
