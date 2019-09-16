-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2018 at 11:43 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jesselland`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `passw` varchar(50) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `passw`, `status`) VALUES
(1, 'joella', '559fe6ce731f37ca7be25429a7a14f8', 1),
(2, 'jea', 'b8f93e16e6dcce8195d433bb56ea', 1),
(3, 'andhika', '6ef95621c960af17372d1145d69af6c8', 1);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `notlp` varchar(12) NOT NULL,
  `email` varchar(100) NOT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `nama`, `alamat`, `notlp`, `email`, `status_aktif`) VALUES
(1, 'Kurniawan', 'Jalan Syanteek 8', '08183791037', 'jekajeka', 1),
(2, 'Athalia', 'Jalan Wuhu 17', '0829579209', 'jesshahaha', 1),
(3, 'Evantia', 'Jalan Walankerto 11', '08325329510', 'fantaiii', 1),
(4, 'Charles', 'Jalan Keselamatan 1', '081377917102', 'lesles', 1);

-- --------------------------------------------------------

--
-- Table structure for table `detail_nota_pembelian`
--

CREATE TABLE `detail_nota_pembelian` (
  `id` int(11) NOT NULL,
  `id_notapembelian` int(11) NOT NULL,
  `id_item` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga_satuan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_nota_pembelian`
--

INSERT INTO `detail_nota_pembelian` (`id`, `id_notapembelian`, `id_item`, `jumlah`, `harga_satuan`) VALUES
(1, 1, 1, 50, 30000);

-- --------------------------------------------------------

--
-- Table structure for table `detail_nota_penjualan`
--

CREATE TABLE `detail_nota_penjualan` (
  `id` int(11) NOT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `id_notapenjualan` int(11) DEFAULT NULL,
  `id_item` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_nota_penjualan`
--

INSERT INTO `detail_nota_penjualan` (`id`, `jumlah`, `id_notapenjualan`, `id_item`) VALUES
(3, 20, 2, 3),
(4, 20, 2, 5),
(8, 30, 4, 5),
(9, 30, 4, 1),
(10, 30, 4, 3);

-- --------------------------------------------------------

--
-- Table structure for table `detail_pembayaran_hutang`
--

CREATE TABLE `detail_pembayaran_hutang` (
  `id` int(11) NOT NULL,
  `id_notapembelian` int(11) NOT NULL,
  `pembayaran` int(11) NOT NULL,
  `id_pembayaranhutang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_pembayaran_hutang`
--

INSERT INTO `detail_pembayaran_hutang` (`id`, `id_notapembelian`, `pembayaran`, `id_pembayaranhutang`) VALUES
(1, 1, 500000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `detail_pembayaran_piutang`
--

CREATE TABLE `detail_pembayaran_piutang` (
  `id` int(11) NOT NULL,
  `id_notapenjualan` int(11) NOT NULL,
  `pembayaran` int(11) NOT NULL,
  `id_pembayaranpiutang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_pembayaran_piutang`
--

INSERT INTO `detail_pembayaran_piutang` (`id`, `id_notapenjualan`, `pembayaran`, `id_pembayaranpiutang`) VALUES
(2, 1, 500000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `detail_retur_penjualan`
--

CREATE TABLE `detail_retur_penjualan` (
  `id` int(11) NOT NULL,
  `id_returpenjualan` int(11) NOT NULL,
  `id_item` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_retur_penjualan`
--

INSERT INTO `detail_retur_penjualan` (`id`, `id_returpenjualan`, `id_item`, `jumlah`) VALUES
(3, 1, 1, 5),
(4, 2, 5, 5),
(5, 2, 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `detail_surat_jalan`
--

CREATE TABLE `detail_surat_jalan` (
  `id` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `id_suratjalan` int(11) NOT NULL,
  `nama_item` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_surat_jalan`
--

INSERT INTO `detail_surat_jalan` (`id`, `jumlah`, `id_suratjalan`, `nama_item`) VALUES
(1, 10, 1, 'RS 16\"'),
(2, 10, 2, 'RS 16\"'),
(3, 10, 3, 'RS 16\"'),
(4, 10, 4, 'RS 16\"'),
(5, 10, 5, 'RS 16\"'),
(6, 10, 6, 'RS 16\"'),
(7, 10, 7, 'RS 14\"'),
(10, 50, 8, 'RS 16\"');

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `merk` varchar(100) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `harga_pokok` int(11) DEFAULT NULL,
  `harga_jual` int(11) DEFAULT NULL,
  `id_lokasi` int(11) DEFAULT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`id`, `nama`, `merk`, `stock`, `harga_pokok`, `harga_jual`, `id_lokasi`, `status_aktif`) VALUES
(1, 'RS 16\"', 'REJ', 210, 18000, 20000, 1, 1),
(2, 'RS 16\"', 'REJ', 20, 18000, 20000, 2, 1),
(3, 'RS 14\"', 'REJ', 40, 15000, 18000, 1, 1),
(4, 'RS 14\"', 'REJ', 10, 15000, 18000, 2, 1),
(5, 'RS 12\"', 'REJ', 70, 10000, 12500, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `lokasi`
--

CREATE TABLE `lokasi` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lokasi`
--

INSERT INTO `lokasi` (`id`, `nama`, `status_aktif`) VALUES
(1, 'Toko', 1),
(2, 'Gudang', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nota_pembelian`
--

CREATE TABLE `nota_pembelian` (
  `id` int(11) NOT NULL,
  `id_supplier` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `total_harga` int(11) NOT NULL,
  `terbayar` int(11) NOT NULL DEFAULT '0',
  `retur` int(11) NOT NULL DEFAULT '0',
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nota_pembelian`
--

INSERT INTO `nota_pembelian` (`id`, `id_supplier`, `tanggal`, `total_harga`, `terbayar`, `retur`, `status_aktif`) VALUES
(1, 1, '2018-12-07', 1500000, 500000, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `nota_penjualan`
--

CREATE TABLE `nota_penjualan` (
  `id` int(11) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `total_harga` int(11) DEFAULT NULL,
  `terbayar` int(11) NOT NULL DEFAULT '0',
  `retur` int(11) NOT NULL DEFAULT '0',
  `id_customer` int(11) DEFAULT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nota_penjualan`
--

INSERT INTO `nota_penjualan` (`id`, `tanggal`, `total_harga`, `terbayar`, `retur`, `id_customer`, `status_aktif`) VALUES
(1, '2018-12-08', 380000, 0, 100000, 1, 0),
(2, '2018-12-14', 610000, 0, 0, 1, 1),
(3, '2018-12-10', 180000, 0, 0, 1, 0),
(4, '2018-12-10', 1515000, 0, 0, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran_hutang`
--

CREATE TABLE `pembayaran_hutang` (
  `id` int(11) NOT NULL,
  `id_supplier` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembayaran_hutang`
--

INSERT INTO `pembayaran_hutang` (`id`, `id_supplier`, `total`, `tanggal`, `status_aktif`) VALUES
(1, 1, 500000, '2018-12-07', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran_piutang`
--

CREATE TABLE `pembayaran_piutang` (
  `id` int(11) NOT NULL,
  `id_customer` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembayaran_piutang`
--

INSERT INTO `pembayaran_piutang` (`id`, `id_customer`, `total`, `tanggal`, `status_aktif`) VALUES
(1, 1, 500000, '2018-12-08', 1);

-- --------------------------------------------------------

--
-- Table structure for table `retur_penjualan`
--

CREATE TABLE `retur_penjualan` (
  `id` int(11) NOT NULL,
  `id_customer` int(11) NOT NULL,
  `id_notapenjualan` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `total_harga` int(11) NOT NULL,
  `potong_nota` tinyint(1) NOT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `retur_penjualan`
--

INSERT INTO `retur_penjualan` (`id`, `id_customer`, `id_notapenjualan`, `tanggal`, `total_harga`, `potong_nota`, `status_aktif`) VALUES
(1, 1, 1, '2018-12-08', 100000, 1, 1),
(2, 2, 4, '2018-12-11', 162500, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `notlp` varchar(12) NOT NULL,
  `email` varchar(100) NOT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id`, `nama`, `alamat`, `notlp`, `email`, `status_aktif`) VALUES
(1, 'Joella', 'Jalan Tambak 28', '081703124777', 'kurniawannn', 1),
(2, 'Jessica', 'Jalan Tralala 13', '08137029103', 'jajajaja', 1),
(3, 'Andhika', 'Jalan Swk 99', '08374921038', 'dhikdhik', 1),
(4, 'johinni', 'Jalan blablabla', '0817329640', 'jojojojoo', 0);

-- --------------------------------------------------------

--
-- Table structure for table `surat_jalan`
--

CREATE TABLE `surat_jalan` (
  `id` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `lokasi_awal` int(11) NOT NULL,
  `lokasi_tujuan` int(11) NOT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `surat_jalan`
--

INSERT INTO `surat_jalan` (`id`, `tanggal`, `lokasi_awal`, `lokasi_tujuan`, `status_aktif`) VALUES
(1, '2018-12-02', 1, 2, 1),
(2, '2018-12-02', 1, 2, 1),
(3, '2018-12-02', 1, 2, 1),
(4, '2018-12-02', 1, 2, 1),
(5, '2018-12-02', 1, 2, 1),
(6, '2018-12-02', 1, 2, 1),
(7, '2018-12-02', 1, 2, 1),
(8, '2018-12-07', 2, 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detail_nota_pembelian`
--
ALTER TABLE `detail_nota_pembelian`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detail_nota_penjualan`
--
ALTER TABLE `detail_nota_penjualan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `it_ind` (`id_item`);

--
-- Indexes for table `detail_pembayaran_hutang`
--
ALTER TABLE `detail_pembayaran_hutang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detail_pembayaran_piutang`
--
ALTER TABLE `detail_pembayaran_piutang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detail_retur_penjualan`
--
ALTER TABLE `detail_retur_penjualan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detail_surat_jalan`
--
ALTER TABLE `detail_surat_jalan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `par_ind` (`id_lokasi`);

--
-- Indexes for table `lokasi`
--
ALTER TABLE `lokasi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nota_pembelian`
--
ALTER TABLE `nota_pembelian`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nota_penjualan`
--
ALTER TABLE `nota_penjualan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `par_ind` (`id_customer`);

--
-- Indexes for table `pembayaran_hutang`
--
ALTER TABLE `pembayaran_hutang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pembayaran_piutang`
--
ALTER TABLE `pembayaran_piutang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `retur_penjualan`
--
ALTER TABLE `retur_penjualan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `surat_jalan`
--
ALTER TABLE `surat_jalan`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `detail_nota_pembelian`
--
ALTER TABLE `detail_nota_pembelian`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `detail_nota_penjualan`
--
ALTER TABLE `detail_nota_penjualan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `detail_pembayaran_hutang`
--
ALTER TABLE `detail_pembayaran_hutang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `detail_pembayaran_piutang`
--
ALTER TABLE `detail_pembayaran_piutang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `detail_retur_penjualan`
--
ALTER TABLE `detail_retur_penjualan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `detail_surat_jalan`
--
ALTER TABLE `detail_surat_jalan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `lokasi`
--
ALTER TABLE `lokasi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `nota_pembelian`
--
ALTER TABLE `nota_pembelian`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `nota_penjualan`
--
ALTER TABLE `nota_penjualan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pembayaran_hutang`
--
ALTER TABLE `pembayaran_hutang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `pembayaran_piutang`
--
ALTER TABLE `pembayaran_piutang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `retur_penjualan`
--
ALTER TABLE `retur_penjualan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `surat_jalan`
--
ALTER TABLE `surat_jalan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
