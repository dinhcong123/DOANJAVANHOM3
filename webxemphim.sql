-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table webxemphim.lưu phim
CREATE TABLE IF NOT EXISTS `lưu phim` (
  `luuphim_id` bigint NOT NULL AUTO_INCREMENT,
  `phim_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`luuphim_id`),
  KEY `FK2o1uky4ga25acaatdv69bhsk0` (`phim_id`),
  KEY `FKo2mhdfunalo1n9ccn2ukvpc9h` (`user_id`),
  CONSTRAINT `FK2o1uky4ga25acaatdv69bhsk0` FOREIGN KEY (`phim_id`) REFERENCES `phim` (`phim_id`),
  CONSTRAINT `FKo2mhdfunalo1n9ccn2ukvpc9h` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table webxemphim.lưu phim: ~2 rows (approximately)
INSERT INTO `lưu phim` (`luuphim_id`, `phim_id`, `user_id`) VALUES
	(4, 15, 3),
	(5, 14, 3);

-- Dumping structure for table webxemphim.phim
CREATE TABLE IF NOT EXISTS `phim` (
  `phim_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `imageurl` varchar(255) DEFAULT NULL,
  `sotapphim` int NOT NULL,
  `sotapphimhientai` int DEFAULT NULL,
  `tenphim` varchar(255) NOT NULL,
  `year` date DEFAULT NULL,
  `quocgia_id` bigint DEFAULT NULL,
  `theloai_id` bigint DEFAULT NULL,
  PRIMARY KEY (`phim_id`),
  KEY `FKtovi2w5iwnd7w4bvljg4s5d3x` (`quocgia_id`),
  KEY `FKfop4waw1l91830hd8cee7dvtm` (`theloai_id`),
  CONSTRAINT `FKfop4waw1l91830hd8cee7dvtm` FOREIGN KEY (`theloai_id`) REFERENCES `thể loại` (`theloai_id`),
  CONSTRAINT `FKtovi2w5iwnd7w4bvljg4s5d3x` FOREIGN KEY (`quocgia_id`) REFERENCES `quốc gia` (`quocgia_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table webxemphim.phim: ~20 rows (approximately)
INSERT INTO `phim` (`phim_id`, `description`, `imageurl`, `sotapphim`, `sotapphimhientai`, `tenphim`, `year`, `quocgia_id`, `theloai_id`) VALUES
	(1, 'Sau khi Đoàn nhạc Gió trường trung học các tiền bối của nhóm nghỉ hưu và một ban lãnh đạo mới sẽ nắm quyền; Kumiko, sắp trở thành học sinh cuối cấp', 'hibike-euphonium-3-thumbnail_90a01fed-3ec8-4e6a-b46d-1f9ff1206b23.webp', 13, 12, 'Hibike! Euphonium 3', '2024-04-10', 1, 2),
	(2, 'Sentai Daishikkaku lấy bối cảnh trong một thế giới tồn tại một nhóm siêu nhân được gọi là Chiến Đội Thánh Long. ', 'sentai-daishikkaku-thumbnail_47335f1e-35ff-4a6a-97f5-ec5a8b539f09.webp', 12, 12, 'Sentai Daishikkaku', '2024-05-01', 1, 6),
	(3, 'Phần 12 Yami Shibai.', 'yami-shibai-12-thumbnail_426b0c12-d03f-4f81-a3a0-30459795e303.webp', 13, 13, 'Yami Shibai', '2024-06-12', 1, 3),
	(4, 'Mùa 4 của Kimetsu no Yaiba.', 'demon-slayer-kimetsu-no-yaiba-hashira-training-arc-thumbnail_699a2b23-ca40-488d-b7c5-b2d34ad57a87.webp', 1, 1, 'Kimetsu no Yaiba: Hashira Geiko-hen', '2024-06-19', 1, 1),
	(5, 'Sousuke Kaburaya, một thám tử nghèo khó, gặp Sara, một công chúa đến từ thế giới khác với sức mạnh ma thuật.', 'henjin-no-salad-bowl-thumbnail_22cc7884-5d44-46d3-8f0e-99a43067d079.webp', 12, 12, 'Henjin no Salad Bowl', '2024-05-01', 1, 2),
	(6, 'Mùa thứ 2 của Series anime Mushoku Tensei Thất nghiệp chuyển sinh', 'mushoku-tensei-ii-isekai-ittara-honki-dasu-ss2-thumbnail_69d472eb-7dd3-49af-a0d5-556f30ab2723.webp', 24, 26, 'Mushoku Tensei II: Isekai Ittara Honki Dasu', '2024-06-05', 1, 2),
	(7, '"Cổng Mới" là trò chơi sinh tử trực tuyến với hàng chục nghìn người chơi. Nhờ Shin, người chơi kỳ cựu có tay nghề cao nhất, những người chơi khác cuối cùng sẽ bị loại khỏi trò chơi', 'the-new-gate-thumbnail_eddc1871-63cd-4ae2-8dfb-960ec81546eb.webp', 12, 12, 'The New Gate', '2024-05-08', 1, 1),
	(8, 'Mùa thứ hai của Maou Gakuin no Futekigousha: Shijou Saikyou no Maou no Shiso, Tensei shite Shison-tachi no Gakkou e Kayou .', 'tensei-shite-shison-tachi-no-gakkou-e-kayou-ii-thumbnail_79e29d63-6934-470c-8630-ffbc72d5b0f8.webp', 21, 21, 'Maou Gakuin no Futekigousha: Shijou Saikyou no Maou no Shiso, Tensei shite Shison-tachi no Gakkou e Kayou II', '2024-07-02', 1, 4),
	(9, 'Mở đầu câu truyện, cậu học sinh trung học 17 tuổi Shinichi Kudo bị biến thành cậu bé Conan Edogawa.', 'detective-conan-thumbnail_8372840e-e12c-4468-9e54-0ef48a2fb3e1.webp', 1129, 1129, 'Thám Tử Lừng Danh Conan', '1996-06-04', 1, 1),
	(10, 'Tonbo Ooi, nhân vật chính, mất cả cha lẫn mẹ trong một vụ tai nạn ô tô khi cô còn nhỏ và được ông nội cô, Gonji Ooi, một ngư dân trên đảo Kagoshima thuộc quần đảo Tokara, nuôi dưỡng.', 'ooi-tonbo-thumbnail_1c848171-2e72-4f48-a694-a13a5b60ba38.webp', 13, 13, 'Ooi! Tonbo', '2024-05-08', 1, 2),
	(11, 'Cuối cùng, ngày Mai tốt nghiệp cấp ba cũng đã đến. Trong khi Sakuta háo hức chờ đợi bạn gái, thì một em học sinh tiểu học trông y hệt cô xuất hiện trước mặt cậu. Nghi ngờ và vì những lý do không hề hay ho gì...', 'seishun-buta-yarou-wa-randoseru-girl-no-yume-wo-minai-thumbnail_48768e54-2bdb-4f55-8324-9a4b3326bb3e.webp', 1, 1, 'Seishun Buta Yarou wa Randoseru Girl no Yume wo Minai', '2024-02-07', 1, 2),
	(12, 'Mùa thứ năm của Date A Live .', 'date-a-live-v-thumbnail_31c48f14-0f60-4d19-9fb4-af5e0816f22e.webp', 12, 12, 'Date A Live V', '2024-03-06', 1, 4),
	(13, 'Mùa thứ ba của Shinigami Bocchan to Kuro Maid', 'shinigami-bocchan-to-kuro-maid-3rd-season-thumbnail_456a99a2-47f7-4b23-9b31-a884d74cd79e.webp', 12, 12, 'Shinigami Bocchan to Kuro Maid 3rd Season', '2024-03-06', 1, 2),
	(14, 'Các học viện của thành phố được chia thành các quận riêng và hầu hết được coi là độc lập. Tổng Hội đồng Sinh viên đóng vai trò là hội đồng quản trị để quản lý toàn bộ học viện.', 'blue-archive-the-animation-thumbnail_05ac135a-ba60-4f3e-860b-988f04c013bb.webp', 12, 12, 'Blue Archive the Animation', '2024-05-07', 1, 4),
	(15, 'Banaza là một thương nhân luôn chăm chỉ làm việc mỗi ngày đã bị đột nhiên triệu hồi tới Vương Quốc Ma Pháp Klyrode xa lạ làm ứng viên dũng sĩ. Tuy nhiên, anh đã bị đánh trượt vì chỉ có cấp độ 1 giống như dân thường.', 'lv2-kara-cheat-datta-motoyuusha-kouho-no-mattari-isekai-life-thumbnail_6c4ce50d-f1a6-4e7b-aa68-b9b3d4f5dc1b.webp', 12, 12, 'Lv2 kara Cheat datta Motoyuusha Kouho no Mattari Isekai Life', '2024-06-11', 1, 2),
	(16, 'Shintarou Tokumitsu là một học sinh trung học sống một mình, nhưng mọi chuyện trở nên bất ngờ khi một cô gái tên Towa xuất hiện trên ban công nhà anh! ', 'one-room-hiatari-futsuu-tenshi-tsuki-thumbnail_d2791099-6b83-48d7-a955-d08a55af05a3.webp', 12, 12, 'One Room, Hiatari Futsuu, Tenshi-tsuki.', '2024-06-05', 1, 1),
	(17, 'Kingdom Season 5', 'kingdom-5th-season-thumbnail_490cf9c4-d3a9-4209-9820-557d2ef656b9.webp', 13, 13, 'Kingdom 5th Season', '2019-04-17', 1, 1),
	(18, 'Khi những ký sinh thú vô danh tàn bạo chiếm con người làm vật chủ và giành được quyền chi phối, nhân loại phải vùng lên chiến đấu với mối đe dọa leo thang này.', 'ky-sinh-thu-vung-xam-thumbnail_d69d83a4-38f2-4efb-b856-c00725ca1358.webp', 6, 6, 'Parasyte: The Grey', NULL, 2, 3),
	(19, 'Trong tương lai xa, chiến tranh đã hủy diệt toàn bộ Trái đất, chỉ để lại một vùng đất hoang cằn cỗi, nơi nguồn cung cấp nước được kiểm soát bởi tên vua tham lam. Để tìm kiếm một hồ nước đã mất tích từ lâu,', 'sand-land-the-series-thumbnail_7e74b653-7a91-4a66-b775-a82e7b86ff6f.webp', 13, 13, 'Sand Land: The Series', NULL, 1, 4),
	(20, 'âu chuyện kể về Arima Kosei, một thần đồng piano. Nhưng kể từ sau chấn động tâm lí do cái sự qua đời của mẹ cậu, Kosei đã không thể nghe thấy bất kì âm thanh nào.', 'shigatsu-wa-kimi-no-uso-thumbnail_f605efdd-bf93-423a-8000-162f9529671f.webp', 22, 22, 'Shigatsu wa Kimi no Uso', NULL, 1, 2),
	(21, 'Có những từ mà Violet nghe được trên chiến trường, điều mà cô không thể quên. Những lời này đã được trao cho cô ấy bởi một người cô ấy yêu mến, hơn bất cứ ai khác. Cô ấy chưa biết ý nghĩa của họ. ', 'anhviolet_99e32eef-7fd5-4a2e-87ca-1e8ef55caf4a.png', 12, 1, 'Violet Envergadent', '2024-07-03', 1, 2),
	(22, 'Năm 2022, thế hệ game chạy trên NERvGear tiếp theo đã được công bố, người dùng có thể kết nối với thế giới ảo thông qua chức năng FullDive. ', 'Swordartonline_498c8d6c-65dd-4d6d-9365-4d348a3e90c5.png', 12, 1, 'Sword Art Online', '2024-07-03', 1, 1),
	(23, 'Phép thuật, đó không còn là truyền thuyết hay một câu chuyện cổ tích nữa. Nó đã trở thành một công nghệ thực tiễn từ gần một thế kỷ nay. ', 'anh2_7dd028c1-2366-432a-a405-d783cad48909.png', 12, 1, 'Mahouka Koukou no Rettousei', '2024-07-03', 1, 1);

-- Dumping structure for table webxemphim.quốc gia
CREATE TABLE IF NOT EXISTS `quốc gia` (
  `quocgia_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `tenquocgia` varchar(255) NOT NULL,
  PRIMARY KEY (`quocgia_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table webxemphim.quốc gia: ~4 rows (approximately)
INSERT INTO `quốc gia` (`quocgia_id`, `description`, `tenquocgia`) VALUES
	(1, 'japan', 'Nhật'),
	(2, 'US', 'Mỹ'),
	(3, 'Nga', 'RU'),
	(4, '', 'EU');

-- Dumping structure for table webxemphim.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(250) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table webxemphim.role: ~2 rows (approximately)
INSERT INTO `role` (`id`, `description`, `name`) VALUES
	(1, NULL, 'ADMIN'),
	(2, NULL, 'USER');

-- Dumping structure for table webxemphim.thể loại
CREATE TABLE IF NOT EXISTS `thể loại` (
  `theloai_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `tentheloai` varchar(255) NOT NULL,
  PRIMARY KEY (`theloai_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table webxemphim.thể loại: ~6 rows (approximately)
INSERT INTO `thể loại` (`theloai_id`, `description`, `tentheloai`) VALUES
	(1, 'hanh dong', 'Action'),
	(2, 'tinh cam', 'Romance'),
	(3, 'Kinh dị', 'Horror'),
	(4, 'Phiêu lưu', 'Adventure'),
	(5, 'Hài hước', 'Comedy'),
	(6, 'Lịch sử', 'Historical');

-- Dumping structure for table webxemphim.tập phim
CREATE TABLE IF NOT EXISTS `tập phim` (
  `tapphim_id` bigint NOT NULL AUTO_INCREMENT,
  `tapphimthu` int NOT NULL,
  `tapphimupdate` varchar(255) NOT NULL,
  `phim_id` bigint DEFAULT NULL,
  PRIMARY KEY (`tapphim_id`),
  KEY `FKpvtjg492oqbp4dlmpmjqwxm71` (`phim_id`),
  CONSTRAINT `FKpvtjg492oqbp4dlmpmjqwxm71` FOREIGN KEY (`phim_id`) REFERENCES `phim` (`phim_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table webxemphim.tập phim: ~16 rows (approximately)
INSERT INTO `tập phim` (`tapphim_id`, `tapphimthu`, `tapphimupdate`, `phim_id`) VALUES
	(1, 1, 'blue archive_163796e3-9c05-4f03-8022-d71e2fbd591e.mp4', 14),
	(2, 1, 'Your lie in April Trailer_1ac652e9-c09e-414e-b582-f7413f98e3cf.mp4', 20),
	(3, 1, 'sand land_fa7d919c-7ebd-4a67-87df-1cc6ee5b8447.mp4', 19),
	(4, 1, 'parasyte_f21fe921-4d3d-4cc8-bb55-a206c219ff11.mp4', 18),
	(5, 1, 'kingdom 5th_0710b8e1-57c5-47cb-968e-ea08cf4f1177.mp4', 17),
	(6, 1, 'One Room, Hiatari Futsuu, Tenshi-tsuki - Trailer_9778a37a-d30a-4d76-bc69-c21074974c87.mp4', 16),
	(7, 1, 'lv2_6830e9df-1c6f-497a-8c35-7fd39ea0f336.mp4', 15),
	(8, 1, 'Shinigami Bocchan to Kuro Maid (2021) - Official Trailer_55780a0a-d421-4dd8-9b97-971eb87be832.mp4', 13),
	(9, 1, 'Date A Live V Season 5 - Official Trailer 2_ac165ece-a886-455b-b4a8-37ff32260650.mp4', 12),
	(10, 1, 'Rascal Does Not Dream of a Sister Venturing Out  -  MAIN TRAILER_b50a40c3-7e9f-4da1-ba36-f5ce8c434d64.mp4', 11),
	(11, 1, 'ooi_11242998-8883-4880-8fec-6fc18f2c07e7.mp4', 10),
	(12, 1, 'DETECTIVE CONAN THE MILLION-DOLLAR PENTAGRAM (Official Trailer) - In CINEMAS 11 July 2024_445ba307-0824-4602-98cc-a85b85a3a334.mp4', 9),
	(13, 1, 'Maou Gakuin no Futekigousha Season 2 Official Trailer (The Misfit of Demon King Academy S2)_50123dcf-f6c5-4318-a23e-af2299e1014f.mp4', 8),
	(14, 1, 'the new gate_bb66b5eb-46e8-4e04-8717-44d05332bd9b.mp4', 7),
	(15, 1, 'Mushoku Tensei- Jobless Reincarnation Season 2 Part 2 - Labyrinth Arc Trailer_e053642b-8b0e-438e-8d14-0975e7ebd0e6.mp4', 6),
	(16, 1, 'henjin no salad_67bc11e4-7244-4289-9452-c70db7ec9530.mp4', 5),
	(17, 1, 'VIOLETEVERGARDEN_7833e9c2-1dec-4253-a53d-76ba1baf27f9.mp4', 21),
	(18, 1, 'Sword Art Online - Official Trailer_492bf1e0-1eed-4426-8a7d-3d61590ec20a.mp4', 22),
	(19, 1, 'Re_Monster - Official Trailer _ English Sub_0e0862c0-ca4a-4037-a4cc-1cc451680975.mp4', 23),
	(20, 2, 'Sword Art Online Alicization War of Underworld Final Season Trailer_84010649-9744-414b-8945-e113e9cea522.mp4', 22);

-- Dumping structure for table webxemphim.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(250) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table webxemphim.user: ~1 rows (approximately)
INSERT INTO `user` (`id`, `email`, `password`, `username`) VALUES
	(3, 'trandinhcong895@gmail.com', '$2a$10$jHRF/eS0p3a7zURwd.AirelXJ76nw/simCs5uUCrwseQXDQLo46se', 'admin');

-- Dumping structure for table webxemphim.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table webxemphim.user_role: ~1 rows (approximately)
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(3, 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
