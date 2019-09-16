-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 24 Nov 2018 pada 11.36
-- Versi server: 10.1.36-MariaDB
-- Versi PHP: 7.2.11

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
-- Struktur dari tabel `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `passw` varchar(50) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id`, `username`, `passw`, `status`) VALUES
(1, 'joella', '559fe6ce731f37ca7be25429a7a14f8', 1),
(2, 'jea', 'b8f93e16e6dcce8195d433bb56ea', 1),
(3, 'andhika', '6ef95621c960af17372d1145d69af6c8', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `notlp` varchar(12) NOT NULL,
  `email` varchar(100) NOT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `customer`
--

INSERT INTO `customer` (`id`, `nama`, `alamat`, `notlp`, `email`, `status_aktif`) VALUES
(1, 'sa', 'sd', 'asd', 'asd', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_nota_pembelian`
--

DROP TABLE IF EXISTS `detail_nota_pembelian`;
CREATE TABLE `detail_nota_pembelian` (
  `id` int(11) NOT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `id_notapembelian` int(11) DEFAULT NULL,
  `id_item` int(11) DEFAULT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_nota_penjualan`
--

DROP TABLE IF EXISTS `detail_nota_penjualan`;
CREATE TABLE `detail_nota_penjualan` (
  `id` int(11) NOT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `id_notapenjualan` int(11) DEFAULT NULL,
  `id_item` int(11) DEFAULT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `item`
--

DROP TABLE IF EXISTS `item`;
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
-- Dumping data untuk tabel `item`
--

INSERT INTO `item` (`id`, `nama`, `merk`, `stock`, `harga_pokok`, `harga_jual`, `id_lokasi`, `status_aktif`) VALUES
(1, 'sda', 'asd', 23, 1, 3, NULL, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `lokasi`
--

DROP TABLE IF EXISTS `lokasi`;
CREATE TABLE `lokasi` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `lokasi`
--

INSERT INTO `lokasi` (`id`, `nama`, `status_aktif`) VALUES
(1, 'Toko', 1),
(2, 'Gudang', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `nota_pembelian`
--

DROP TABLE IF EXISTS `nota_pembelian`;
CREATE TABLE `nota_pembelian` (
  `id` int(11) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `total_harga` int(11) DEFAULT NULL,
  `id_supplier` int(11) DEFAULT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `nota_penjualan`
--

DROP TABLE IF EXISTS `nota_penjualan`;
CREATE TABLE `nota_penjualan` (
  `id` int(11) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `total_harga` int(11) DEFAULT NULL,
  `id_customer` int(11) DEFAULT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `supplier`
--

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `notlp` varchar(12) NOT NULL,
  `email` varchar(100) NOT NULL,
  `status_aktif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `supplier`
--

INSERT INTO `supplier` (`id`, `nama`, `alamat`, `notlp`, `email`, `status_aktif`) VALUES
(1, 'sad', 'asd', 'sad', 'asd', 1),
(2, '1', '2', '3', '4', 1),
(3, '1', '2', '3', '4', 1),
(4, 'dsa', 'asd', 'asd', 'asd', 1),
(5, 'sad', 'sad', 'asd', 'ads', 1),
(6, 'sda', 'asd', 'asdasd', 'as', 0);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `detail_nota_pembelian`
--
ALTER TABLE `detail_nota_pembelian`
  ADD PRIMARY KEY (`id`),
  ADD KEY `par_ind` (`id_notapembelian`),
  ADD KEY `it_ind` (`id_item`);

--
-- Indeks untuk tabel `detail_nota_penjualan`
--
ALTER TABLE `detail_nota_penjualan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `par_ind` (`id_notapenjualan`),
  ADD KEY `it_ind` (`id_item`);

--
-- Indeks untuk tabel `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `par_ind` (`id_lokasi`);

--
-- Indeks untuk tabel `lokasi`
--
ALTER TABLE `lokasi`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `nota_pembelian`
--
ALTER TABLE `nota_pembelian`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sup_ind` (`id_supplier`);

--
-- Indeks untuk tabel `nota_penjualan`
--
ALTER TABLE `nota_penjualan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `par_ind` (`id_customer`);

--
-- Indeks untuk tabel `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `detail_nota_pembelian`
--
ALTER TABLE `detail_nota_pembelian`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `detail_nota_penjualan`
--
ALTER TABLE `detail_nota_penjualan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `item`
--
ALTER TABLE `item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `lokasi`
--
ALTER TABLE `lokasi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `nota_pembelian`
--
ALTER TABLE `nota_pembelian`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `nota_penjualan`
--
ALTER TABLE `nota_penjualan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detail_nota_pembelian`
--
ALTER TABLE `detail_nota_pembelian`
  ADD CONSTRAINT `detail_nota_pembelian_ibfk_1` FOREIGN KEY (`id_notapembelian`) REFERENCES `nota_pembelian` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `detail_nota_pembelian_ibfk_2` FOREIGN KEY (`id_item`) REFERENCES `item` (`id`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `detail_nota_penjualan`
--
ALTER TABLE `detail_nota_penjualan`
  ADD CONSTRAINT `detail_nota_penjualan_ibfk_1` FOREIGN KEY (`id_notapenjualan`) REFERENCES `nota_penjualan` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `detail_nota_penjualan_ibfk_2` FOREIGN KEY (`id_item`) REFERENCES `item` (`id`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`id_lokasi`) REFERENCES `lokasi` (`id`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `nota_pembelian`
--
ALTER TABLE `nota_pembelian`
  ADD CONSTRAINT `nota_pembelian_ibfk_1` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `nota_penjualan`
--
ALTER TABLE `nota_penjualan`
  ADD CONSTRAINT `nota_penjualan_ibfk_1` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
