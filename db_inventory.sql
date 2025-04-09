-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 20 Des 2024 pada 12.48
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_inventory`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `kode_barang` char(15) NOT NULL,
  `nama_barang` varchar(30) NOT NULL,
  `satuan` varchar(10) DEFAULT NULL,
  `harga` double NOT NULL,
  `stok` double NOT NULL,
  `jenis_barang` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`kode_barang`, `nama_barang`, `satuan`, `harga`, `stok`, `jenis_barang`) VALUES
('b011224001', 'Rose', 'Botol ', 200, 217, 'bibit'),
('b011224002', 'Yves Saint Laurent Black Opium', 'Botol ', 1200000, -5, 'bibit'),
('b011224005', 'Giorgio Armani Si', 'Botol ', 900000, -62, 'bibit'),
('b011224007', 'Cherry blossom', 'Botol ', 40000, 6, 'bibit'),
('b011224008', 'lovely sparkle', 'Botol ', 10000, 8, 'bibit'),
('b011224009', 'Orange fresh', 'Botol ', 80000, 12, 'bibit'),
('b201224001', 'Scandal Blossom', 'Botol ', 25000, 120, 'bibit');

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang_keluar`
--

CREATE TABLE `barang_keluar` (
  `tanggal_keluar` date NOT NULL,
  `kode_barangkeluar` char(15) NOT NULL,
  `nama_barang` varchar(30) NOT NULL,
  `satuan` char(10) NOT NULL,
  `jumlah_barangkeluar` double NOT NULL,
  `jenis_barang` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `barang_keluar`
--

INSERT INTO `barang_keluar` (`tanggal_keluar`, `kode_barangkeluar`, `nama_barang`, `satuan`, `jumlah_barangkeluar`, `jenis_barang`) VALUES
('2024-12-19', 'BK0001', 'Giorgio Armani Si', 'Botol', 70, 'bibit'),
('2024-12-20', 'BK0002', 'Yves Saint Laurent Black Opium', 'Botol', 20, 'bibit');

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang_masuk`
--

CREATE TABLE `barang_masuk` (
  `tanggal_masuk` char(15) NOT NULL,
  `kode_barangmasuk` char(15) NOT NULL,
  `nama_barang` varchar(30) NOT NULL,
  `satuan` varchar(10) DEFAULT NULL,
  `jumlah_barangmasuk` double NOT NULL,
  `jenis_barang` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `barang_masuk`
--

INSERT INTO `barang_masuk` (`tanggal_masuk`, `kode_barangmasuk`, `nama_barang`, `satuan`, `jumlah_barangmasuk`, `jenis_barang`) VALUES
('2024-12-16', 'BM0001', 'Rose', 'Botol ', 20, 'bibit');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_pemesanan`
--

CREATE TABLE `detail_pemesanan` (
  `no_pesan` char(12) NOT NULL,
  `kode_barang` char(12) NOT NULL,
  `jml_pesan` int(11) NOT NULL,
  `subtotal_pesan` double NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `detail_pemesanan`
--

INSERT INTO `detail_pemesanan` (`no_pesan`, `kode_barang`, `jml_pesan`, `subtotal_pesan`, `status`) VALUES
('PS0001', 'b011224001', 12, 12, 'sedang dipesan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `distributor`
--

CREATE TABLE `distributor` (
  `id_distributor` char(10) NOT NULL,
  `nama_distributor` varchar(30) NOT NULL,
  `telp_distributor` char(13) NOT NULL,
  `alamat_distributor` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `distributor`
--

INSERT INTO `distributor` (`id_distributor`, `nama_distributor`, `telp_distributor`, `alamat_distributor`) VALUES
('D0001', 'CV. Harum ', '12345', 'jl.Merak k'),
('D0003', 'CV Rezki Kita', '1028999', 'Medan'),
('D0004', 'CV. Makmur Kali', '123456', 'Tembung');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pemesanan`
--

CREATE TABLE `pemesanan` (
  `no_pesan` char(12) NOT NULL,
  `tgl_pesan` date NOT NULL,
  `total_pesan` double NOT NULL,
  `id_distributor` char(10) NOT NULL,
  `id_pengguna` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pemesanan`
--

INSERT INTO `pemesanan` (`no_pesan`, `tgl_pesan`, `total_pesan`, `id_distributor`, `id_pengguna`) VALUES
('PS0001', '2024-12-11', 12, 'D0001', 'p0001'),
('PS0002', '2024-12-13', 1800000, 'D0001', 'ID');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` char(10) NOT NULL,
  `nama_pengguna` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `telp_pengguna` varchar(13) NOT NULL,
  `alamat_pengguna` varchar(100) NOT NULL,
  `level` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `nama_pengguna`, `username`, `password`, `telp_pengguna`, `alamat_pengguna`, `level`) VALUES
('p0001', 'Juwita', 'Owner', 'admin', '08936812223', 'Medan', 'Owner'),
('p0002', 'Irsan', 'irsan', 'irsan', '123456', 'Tuntungan', 'Staff');

-- --------------------------------------------------------

--
-- Struktur dari tabel `sem_pemesanan`
--

CREATE TABLE `sem_pemesanan` (
  `kode_barang` char(15) NOT NULL,
  `nama_barang` varchar(20) NOT NULL,
  `satuan` char(10) NOT NULL,
  `harga` double NOT NULL,
  `jml_pesan` int(11) NOT NULL,
  `subtotal_pesan` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `sem_pemesanan`
--

INSERT INTO `sem_pemesanan` (`kode_barang`, `nama_barang`, `satuan`, `harga`, `jml_pesan`, `subtotal_pesan`) VALUES
('b011224003', 'Creed Aven', 'Botol', 900000, 23, 20700000),
('b011224001', 'Rose', 'Botol', 200, 3, 600),
('b011224001', 'Rose', 'Botol', 200, 3, 600);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode_barang`);

--
-- Indeks untuk tabel `barang_masuk`
--
ALTER TABLE `barang_masuk`
  ADD PRIMARY KEY (`kode_barangmasuk`);

--
-- Indeks untuk tabel `detail_pemesanan`
--
ALTER TABLE `detail_pemesanan`
  ADD PRIMARY KEY (`no_pesan`),
  ADD KEY `kode_barang` (`kode_barang`),
  ADD KEY `no_pesan` (`no_pesan`);

--
-- Indeks untuk tabel `pemesanan`
--
ALTER TABLE `pemesanan`
  ADD PRIMARY KEY (`no_pesan`),
  ADD KEY `id_distributor` (`id_distributor`),
  ADD KEY `id_pengguna` (`id_pengguna`);

--
-- Indeks untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
