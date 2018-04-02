-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 24, 2018 at 04:47 PM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `beebook`
--

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE IF NOT EXISTS `anggota` (
  `id_anggota` varchar(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `jenis_kelamin` varchar(6) NOT NULL,
  `no_telp` varchar(15) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL,
  `status` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `anggota`
--

INSERT INTO `anggota` (`id_anggota`, `nama`, `jenis_kelamin`, `no_telp`, `alamat`, `email`, `status`) VALUES
('Beebook-001', 'Acer', 'Pria', 'Pria', 'Jln Hj. Mencong No. 23 Rt.003 Rw.004 Tangerang', '1234@gmail.com', 'Meminjam'),
('Beebook-002', 'Semur', 'Pria', '087788899233', 'Jalan Hj. Gedad No. 38 RT.003 RW.005 Sudimara Jaya', 'Semar@yahoo.com', 'Meminjam'),
('Beebook-003', 'Bagil', 'Pria', '0822898455678', 'Jalan Kedondong 1 No. 80 RT 003 RW 004 Sudimara Timur Ciledug Tangerang', 'bagil12@gmail.com', 'Meminjam'),
('Beebook-004', 'Marzuki Bin Iman', 'Pria', '08389999999', 'Jalan KPBD No. 38 Bayoran', 'marzuki@gmail.com', '-'),
('Beebook-005', 'Juju', 'Wanita', '082298123443', 'Jalan Petukangan No. 28 Ciledug Tangerang', 'J@gmail.com', '-'),
('Beebook-006', 'Jojo', 'Wanita', '089734758182', 'Jalan Ceger Raya No. 13', 'Jo@gmail.com', 'Meminjam'),
('Beebook-007', 'Selena Gomez', 'Wanita', '08228213637', 'Jalan Gandaria 1 No. 38. Bayoran. Jakarta Selatan.', 'gomez@gmail.com', '-'),
('Beebook-008', 'Babaaa', 'Pria', '0812987612345', 'Jalan Hj. Mean', 'baba!@gmail.com', '-');

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE IF NOT EXISTS `buku` (
  `id_buku` varchar(4) NOT NULL,
  `judul` varchar(50) NOT NULL,
  `pengarang` varchar(30) NOT NULL,
  `penerbit` varchar(30) NOT NULL,
  `tahun_terbit` varchar(4) NOT NULL,
  `kategori` varchar(30) NOT NULL,
  `harga` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id_buku`, `judul`, `pengarang`, `penerbit`, `tahun_terbit`, `kategori`, `harga`) VALUES
('B001', 'Aroma Kasta', 'Dee Lestari', 'Bentang Pustaka', '2018', 'Fiksi', 125000),
('B002', 'The Life Changing Magic Of Tidying Up', 'Marie Kondo', 'Benteng Pustaka', '2016', 'Referensi Umum', 54000),
('B003', 'Dilan 2 Dia Adalah Dilanku', 'Pidi Baiq', 'Pastel Books', '2015', 'Remaja', 69000),
('B004', 'Metropop:Resign!', 'Almira Bastari', 'Gramedia Pustaka Utama', '2018', 'Metropop', 68000),
('B005', 'Don''t Follow Your Passion', 'Cal Newport', 'Noura Book Publishing', '2016', 'Pengembangan Diri', 69000),
('B006', 'Sebuah Seni Untuk Bersikap Bodo Amat', 'Mark Manson', 'Gramedia Pustaka Utama', '2018', 'Pengembangan Diri', 67000),
('B007', 'Ubur-Ubur Lembur', 'Raditya Dika', 'GagasMedia', '2018', 'Komedi', 66000),
('B008', 'Why Rich Are Getting Better', 'Robert T. Kiyosaki', 'Gramedia Pustaka Utama', '2018', 'Inspirasional', 98000),
('B009', 'DILAN dia Adalah Dilanku Tahun 1990', 'Pidi Baiq', 'DAR! Mizan', '2014', 'Remaja', 69000),
('B010', 'Milea Suara Dari Dilan (Dilan)', 'Pidi Baiq', 'Pastel Books', '2016', 'Remaja', 79000),
('B011', 'Annisa Petualang Cantik', 'Annisa Jurainun', 'Kurang Tahu Studio', '2018', 'Fiksi', 5000);

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE IF NOT EXISTS `detail_transaksi` (
  `id_detail` int(11) NOT NULL,
  `no_pinjam` varchar(4) NOT NULL,
  `id_buku` varchar(4) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_detail`, `no_pinjam`, `id_buku`) VALUES
(26, 'P001', 'B005'),
(27, 'P001', 'B009'),
(28, 'P002', 'B005'),
(29, 'P002', 'B008'),
(30, 'P003', 'B005'),
(31, 'P003', 'B002'),
(32, 'P004', 'B005'),
(33, 'P005', 'B002'),
(34, 'P005', 'B003'),
(35, 'P009', 'B009'),
(36, 'P009', 'B010'),
(37, 'P009', 'B011'),
(38, 'P009', 'B006'),
(39, 'P010', 'B001'),
(40, 'P010', 'B004'),
(41, 'P011', 'B001'),
(42, 'P012', 'B001'),
(43, 'P013', 'B004'),
(44, 'P013', 'B008'),
(45, 'P013', 'B002');

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE IF NOT EXISTS `peminjaman` (
  `no_pinjam` varchar(4) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `tgl_pengembalian` date NOT NULL,
  `biaya_sewa` double NOT NULL,
  `uang_bayar` double NOT NULL,
  `uang_kembali` double NOT NULL,
  `id_anggota` varchar(11) NOT NULL,
  `status` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`no_pinjam`, `tgl_pinjam`, `tgl_pengembalian`, `biaya_sewa`, `uang_bayar`, `uang_kembali`, `id_anggota`, `status`) VALUES
('P001', '2018-03-08', '2018-03-15', 138000, 0, 0, 'Beebook-001', 'Selesai'),
('P002', '2018-03-08', '2018-03-15', 167000, 0, 0, 'Beebook-002', 'Selesai'),
('P003', '2018-03-08', '2018-03-15', 123000, 0, 0, 'Beebook-006', 'Selesai'),
('P004', '2018-03-08', '2018-03-15', 69000, 0, 0, 'Beebook-001', 'Selesai'),
('P005', '2018-03-09', '2018-03-16', 123000, 0, 0, 'Beebook-003', 'Selesai'),
('P006', '2018-03-13', '2018-03-20', 0, 0, 0, 'Beebook-007', 'Selesai'),
('P007', '2018-03-13', '2018-03-20', 0, 0, 0, 'Beebook-003', 'Selesai'),
('P008', '2018-03-13', '2018-03-20', 0, 0, 0, 'Beebook-005', 'Selesai'),
('P009', '2018-03-13', '2018-03-20', 220000, 0, 0, 'Beebook-004', 'Selesai'),
('P010', '2018-03-13', '2018-03-20', 193000, 0, 0, 'Beebook-002', 'Meminjam'),
('P011', '2018-03-24', '2018-03-31', 125000, 150000, 25000, 'Beebook-003', 'Meminjam'),
('P012', '2018-03-24', '2018-03-31', 125000, 130000, 5000, 'Beebook-006', 'Meminjam'),
('P013', '2018-03-24', '2018-03-31', 220000, 220000, 0, 'Beebook-001', 'Meminjam');

-- --------------------------------------------------------

--
-- Table structure for table `pengembalian`
--

CREATE TABLE IF NOT EXISTS `pengembalian` (
  `id` int(11) NOT NULL,
  `no_pinjam` varchar(4) NOT NULL,
  `tgl_dikembalikan` date NOT NULL,
  `denda` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengembalian`
--

INSERT INTO `pengembalian` (`id`, `no_pinjam`, `tgl_dikembalikan`, `denda`) VALUES
(4, 'P001', '2018-03-08', 0),
(5, 'P005', '2018-03-09', 0),
(6, 'P009', '2018-03-13', 0),
(7, 'P008', '2018-03-13', 0),
(8, 'P007', '2018-03-13', 0),
(9, 'P006', '2018-03-13', 0),
(10, 'P004', '2018-03-13', 0),
(11, 'P003', '2018-03-13', 0),
(12, 'P002', '2018-03-13', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id_user` varchar(4) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `hak_akses` varchar(5) NOT NULL,
  `password` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nama`, `hak_akses`, `password`) VALUES
('U001', 'Annisa', 'Owner', '12345'),
('U002', 'Aan', 'Admin', '12345');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`id_anggota`);

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id_buku`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_detail`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`no_pinjam`);

--
-- Indexes for table `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=46;
--
-- AUTO_INCREMENT for table `pengembalian`
--
ALTER TABLE `pengembalian`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
