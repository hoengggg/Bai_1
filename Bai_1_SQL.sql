CREATE DATABASE geography
GO

USE geography
GO

--USE master
--GO

--drop database geography
--go

CREATE TABLE province(
	id INT PRIMARY KEY IDENTITY(1,1),
	maTinhThanh varchar(255),
	tenTinhThanh nvarchar(255),
	trangThai BIT,
	ngayXoa DATETIME
);

CREATE TABLE ward(
	id INT PRIMARY KEY IDENTITY(1,1),
	maPhuong varchar(255),
	tenPhuong nvarchar(255),
	id_province INT,
	trangThai BIT,
	ngayXoa DATETIME,
	FOREIGN KEY (id_province) REFERENCES province(id) ON DELETE CASCADE
);

INSERT INTO province (maTinhThanh, tenTinhThanh, trangThai, ngayXoa) VALUES 
('HANOI', N'Thành ph? Hà N?i', 1, NULL),
('TPHCM', N'Thành ph? H? Chí Minh', 1, NULL),
('HAIPHONG', N'Thành ph? H?i Phòng', 1, NULL),
('DANANG', N'Thành ph? ?à N?ng', 1, NULL),
('CANTHO', N'Thành ph? C?n Th?', 1, NULL),
('THAIBINH', N'T?nh Thái Bình', 1, NULL),
('NAMDINH', N'T?nh Nam ??nh', 1, NULL),
('THANHHOA', N'T?nh Thanh Hóa', 1, NULL),
('NGHEAN', N'T?nh Ngh? An', 1, NULL),
('QUANGNINH', N'T?nh Qu?ng Ninh', 1, NULL),
('HAIDUONG', N'T?nh H?i D??ng', 1, NULL),
('BACNINH', N'T?nh B?c Ninh', 1, NULL),
('VINHPHUC', N'T?nh V?nh Phúc', 1, NULL),
('HUNGYEN', N'T?nh H?ng Yên', 1, NULL),
('HAGIANG', N'T?nh Hà Giang', 1, NULL),
('LAOCAI', N'T?nh Lào Cai', 1, NULL),
('LAMDONG', N'T?nh Lâm ??ng', 1, NULL),
('KHANHHOA', N'T?nh Khánh Hòa', 1, NULL),
('BINHDUONG', N'T?nh Bình D??ng', 1, NULL),
('DONGNAI', N'T?nh ??ng Nai', 1, NULL);

INSERT INTO ward (maPhuong, tenPhuong, id_province, trangThai, ngayXoa) VALUES 
('P_DT', N'Ph??ng D?ch V?ng', 1, 1, NULL),
('P_MYDINH', N'Ph??ng M? ?ình', 1, 1, NULL),
('P_HANGTRONG', N'Ph??ng Hàng Tr?ng', 1, 1, NULL),
('P_BENTHANH', N'Ph??ng B?n Thành', 2, 1, NULL),
('P_DAOKAO', N'Ph??ng ?a Kao', 2, 1, NULL),
('P_THAO_DIEN', N'Ph??ng Th?o ?i?n', 2, 1, NULL),
('P_LACH_TRAY', N'Ph??ng L?ch Tray', 3, 1, NULL),
('P_CAU_DAT', N'Ph??ng C?u ??t', 3, 1, NULL),
('P_THACH_THANG', N'Ph??ng Th?ch Thang', 4, 1, NULL),
('P_HOA_CUONG', N'Ph??ng Hòa C??ng', 4, 1, NULL),
('P_AN_HOA', N'Ph??ng An Hòa', 5, 1, NULL),
('P_NINH_KIEU', N'Ph??ng Ninh Ki?u', 5, 1, NULL),
('P_DE_THAM', N'Ph??ng ?? Thám', 6, 1, NULL),
('P_QUANG_TRUNG', N'Ph??ng Quang Trung', 6, 1, NULL),
('P_LOC_HOA', N'Ph??ng L?c Hòa', 7, 1, NULL),
('P_VI_HOANG', N'Ph??ng V? Hoàng', 7, 1, NULL),
('P_DONG_VE', N'Ph??ng ?ông V?', 8, 1, NULL),
('P_BA_DINH_TH', N'Ph??ng Ba ?ình', 8, 1, NULL),
('P_QUANG_TRUNG_NA', N'Ph??ng Quang Trung', 9, 1, NULL),
('P_HONG_GAI', N'Ph??ng H?ng Gai', 10, 1, NULL);
select * from ward