-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 21 Nov 2023 pada 03.02
-- Versi server: 10.4.25-MariaDB
-- Versi PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_karyawan_terbaik`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `bobot`
--

CREATE TABLE `bobot` (
  `id_bobot` varchar(6) NOT NULL,
  `jam_kerja` float NOT NULL,
  `absensi` float NOT NULL,
  `kerapihan` float NOT NULL,
  `keterlambatan` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `bobot`
--

INSERT INTO `bobot` (`id_bobot`, `jam_kerja`, `absensi`, `kerapihan`, `keterlambatan`) VALUES
('BBT001', 0.3, 0.3, 0.2, 0.2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `hasil`
--

CREATE TABLE `hasil` (
  `nik` varchar(12) NOT NULL,
  `nilai` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `hasil`
--

INSERT INTO `hasil` (`nik`, `nilai`) VALUES
('123413123121', 0.6),
('126351281233', 0.58),
('126353612312', 0.88);

-- --------------------------------------------------------

--
-- Struktur dari tabel `jabatan`
--

CREATE TABLE `jabatan` (
  `nik` varchar(12) NOT NULL,
  `jabatan` varchar(33) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `jabatan`
--

INSERT INTO `jabatan` (`nik`, `jabatan`) VALUES
('126353612312', 'Barista'),
('126351281233', 'Billing Staff'),
('123413123121', 'Production assistant');

-- --------------------------------------------------------

--
-- Struktur dari tabel `karyawan`
--

CREATE TABLE `karyawan` (
  `nik` varchar(12) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `alamat` text NOT NULL,
  `jenis_kelamin` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `karyawan`
--

INSERT INTO `karyawan` (`nik`, `nama`, `alamat`, `jenis_kelamin`) VALUES
('123413123121', 'Nurhayati', 'Bogor', 'P'),
('126351281233', 'Abdul Rofiq', 'Depok', 'L'),
('126353612312', 'ZarkasihAlfauzi', 'Depok', 'L');

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE `login` (
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`username`, `password`, `status`) VALUES
('zarkasih', '1234', 'Admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penilaian_karyawan`
--

CREATE TABLE `penilaian_karyawan` (
  `id_penilaian` varchar(6) NOT NULL,
  `nik` varchar(12) NOT NULL,
  `jam_kerja` varchar(20) NOT NULL,
  `absensi` varchar(20) NOT NULL,
  `kerapihan` varchar(20) NOT NULL,
  `keterlambatan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `penilaian_karyawan`
--

INSERT INTO `penilaian_karyawan` (`id_penilaian`, `nik`, `jam_kerja`, `absensi`, `kerapihan`, `keterlambatan`) VALUES
('PN0001', '126353612312', '>=700 Jam', '>=95 Hadir', '>=95', '3<= Telat'),
('PN0003', '126351281233', '>=700 Jam', '71-77 Hadir', '71-77', '3<= Telat'),
('PN0005', '123413123121', '169 Jam<=', '78-84 Hadir', '85-94', '11-13 Telat');

-- --------------------------------------------------------

--
-- Struktur dari tabel `rating_kecocokan`
--

CREATE TABLE `rating_kecocokan` (
  `id_penilaian` varchar(6) NOT NULL,
  `nik` varchar(12) NOT NULL,
  `nilai_jam_kerja` float NOT NULL,
  `nilai_absensi` float NOT NULL,
  `nilai_kerapihan` float NOT NULL,
  `nilai_keterlambatan` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `rating_kecocokan`
--

INSERT INTO `rating_kecocokan` (`id_penilaian`, `nik`, `nilai_jam_kerja`, `nilai_absensi`, `nilai_kerapihan`, `nilai_keterlambatan`) VALUES
('PN0001', '126353612312', 1, 1, 1, 1),
('PN0003', '126351281233', 1, 0.4, 0.4, 1),
('PN0005', '123413123121', 0.2, 0.6, 0.8, 0.4);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `bobot`
--
ALTER TABLE `bobot`
  ADD PRIMARY KEY (`id_bobot`);

--
-- Indeks untuk tabel `hasil`
--
ALTER TABLE `hasil`
  ADD KEY `id_karyawan` (`nik`);

--
-- Indeks untuk tabel `jabatan`
--
ALTER TABLE `jabatan`
  ADD KEY `nis` (`nik`);

--
-- Indeks untuk tabel `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`nik`);

--
-- Indeks untuk tabel `penilaian_karyawan`
--
ALTER TABLE `penilaian_karyawan`
  ADD PRIMARY KEY (`id_penilaian`),
  ADD KEY `id_karyawan` (`nik`);

--
-- Indeks untuk tabel `rating_kecocokan`
--
ALTER TABLE `rating_kecocokan`
  ADD KEY `id_karyawan` (`nik`),
  ADD KEY `id_penilaian` (`id_penilaian`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `hasil`
--
ALTER TABLE `hasil`
  ADD CONSTRAINT `hasil_ibfk_1` FOREIGN KEY (`nik`) REFERENCES `karyawan` (`nik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `jabatan`
--
ALTER TABLE `jabatan`
  ADD CONSTRAINT `jabatan_ibfk_1` FOREIGN KEY (`nik`) REFERENCES `karyawan` (`nik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `penilaian_karyawan`
--
ALTER TABLE `penilaian_karyawan`
  ADD CONSTRAINT `penilaian_karyawan_ibfk_1` FOREIGN KEY (`nik`) REFERENCES `karyawan` (`nik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `rating_kecocokan`
--
ALTER TABLE `rating_kecocokan`
  ADD CONSTRAINT `rating_kecocokan_ibfk_1` FOREIGN KEY (`nik`) REFERENCES `karyawan` (`nik`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rating_kecocokan_ibfk_2` FOREIGN KEY (`id_penilaian`) REFERENCES `penilaian_karyawan` (`id_penilaian`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
