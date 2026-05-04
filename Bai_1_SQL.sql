CREATE DATABASE geography
GO

USE geography
GO

drop database geography
go

CREATE TABLE province(
	id INT PRIMARY KEY IDENTITY(1,1),
	maTinhThanh varchar(255),
	tenTinhThanh nvarchar(255)
);

CREATE TABLE ward(
	id INT PRIMARY KEY IDENTITY(1,1),
	maPhuong varchar(255),
	tenPhuong nvarchar(255),
	id_province INT,
	 FOREIGN KEY (id_province) REFERENCES province(id) ON DELETE CASCADE
);

INSERT INTO province (maTinhThanh, tenTinhThanh) VALUES 
('HANOI', N'Thành ph? Hà N?i'),
('TPHCM', N'Thành ph? H? Chí Minh'),
('HAIPHONG', N'Thành ph? H?i Phòng'),
('DANANG', N'Thành ph? ?à N?ng'),
('CANTHO', N'Thành ph? C?n Th?'),
('THAIBINH', N'T?nh Thái Bình'),
('NAMDINH', N'T?nh Nam ??nh'),
('THANHHOA', N'T?nh Thanh Hóa'),
('NGHEAN', N'T?nh Ngh? An'),
('QUANGNINH', N'T?nh Qu?ng Ninh'),
('HAIDUONG', N'T?nh H?i D??ng'),
('BACNINH', N'T?nh B?c Ninh'),
('VINHPHUC', N'T?nh V?nh Phúc'),
('HUNGYEN', N'T?nh H?ng Yên'),
('HAGIANG', N'T?nh Hà Giang'),
('LAOCAI', N'T?nh Lào Cai'),
('LAMDONG', N'T?nh Lâm ??ng'),
('KHANHHOA', N'T?nh Khánh Hòa'),
('BINHDUONG', N'T?nh Bình D??ng'),
('DONGNAI', N'T?nh ??ng Nai');

INSERT INTO ward (maPhuong, tenPhuong, id_province) VALUES 
('P_DT', N'Ph??ng D?ch V?ng', 1),
('P_MYDINH', N'Ph??ng M? ?ình', 1),
('P_HANGTRONG', N'Ph??ng Hàng Tr?ng', 1),
('P_BENTHANH', N'Ph??ng B?n Thành', 2),
('P_DAOKAO', N'Ph??ng ?a Kao', 2),
('P_THAO_DIEN', N'Ph??ng Th?o ?i?n', 2),
('P_LACH_TRAY', N'Ph??ng L?ch Tray', 3),
('P_CAU_DAT', N'Ph??ng C?u ??t', 3),
('P_THACH_THANG', N'Ph??ng Th?ch Thang', 4),
('P_HOA_CUONG', N'Ph??ng Hòa C??ng', 4),
('P_AN_HOA', N'Ph??ng An Hòa', 5),
('P_NINH_KIEU', N'Ph??ng Ninh Ki?u', 5),
('P_DE_THAM', N'Ph??ng ?? Thám', 6),
('P_QUANG_TRUNG', N'Ph??ng Quang Trung', 6),
('P_LOC_HOA', N'Ph??ng L?c Hòa', 7),
('P_VI_HOANG', N'Ph??ng V? Hoàng', 7),
('P_DONG_VE', N'Ph??ng ?ông V?', 8),
('P_BA_DINH_TH', N'Ph??ng Ba ?ình', 8),
('P_QUANG_TRUNG_NA', N'Ph??ng Quang Trung', 9),
('P_HONG_GAI', N'Ph??ng H?ng Gai', 10);