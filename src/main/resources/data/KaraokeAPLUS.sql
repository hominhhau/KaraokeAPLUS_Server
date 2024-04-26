-- Tạo cơ sở dữ liệu KaraokeAPLUS
CREATE DATABASE KaraokeAPLUS;
GO

-- Sử dụng cơ sở dữ liệu KaraokeAPLUS
USE KaraokeAPLUS;
GO

-- Tạo bảng LoaiNhanVien
CREATE TABLE LoaiNhanVien (
    maLoai VARCHAR(50) PRIMARY KEY,
    tenLoai VARCHAR(255)
);
GO

INSERT INTO LoaiNhanVien (maLoai, tenLoai) VALUES ('NVQL', N'Nhân viên quản lý');
INSERT INTO LoaiNhanVien (maLoai, tenLoai) VALUES ('NVTN', N'Nhân viên thu ngân');
INSERT INTO LoaiNhanVien (maLoai, tenLoai) VALUES ('Khac', N'Nhân viên khác');

-- Tạo bảng LoaiPhong
CREATE TABLE LoaiPhong (
    maLoaiPhong VARCHAR(50) PRIMARY KEY,
    tenLoaiPhong VARCHAR(255),
    Gia FLOAT
);
GO

INSERT INTO LoaiPhong (maLoaiPhong, tenLoaiPhong, Gia) VALUES ('LP001', 'VIP', 100000);
INSERT INTO LoaiPhong (maLoaiPhong, tenLoaiPhong, Gia) VALUES ('LP002', 'THUONG', 60000);


-- Tạo bảng MatHang
CREATE TABLE MatHang (
    maMH VARCHAR(50) PRIMARY KEY,
    tenMH NVARCHAR(255),
    Gia FLOAT,
	trangThai BIT
);
GO

INSERT INTO MatHang (maMH, tenMH, Gia, trangThai) VALUES ('MH001', N'Bia Heineken lon 330ml', 20000, 1);
INSERT INTO MatHang (maMH, tenMH, Gia, trangThai) VALUES ('MH002', N'Bia Heineken chai 330ml', 50000, 1);
INSERT INTO MatHang (maMH, tenMH, Gia, trangThai) VALUES ('MH003', N'Bia Tiger lon 330ml', 17000, 1);
INSERT INTO MatHang (maMH, tenMH, Gia, trangThai) VALUES ('MH004', N'Bia Tiger Crystal lon 330ml', 19000, 1);
INSERT INTO MatHang (maMH, tenMH, Gia, trangThai) VALUES ('MH005', N'Bia Budweiser lon 330ml', 18000, 1);
INSERT INTO MatHang (maMH, tenMH, Gia, trangThai) VALUES ('MH006', N'Bia Budweiser chai 330ml', 22000, 1);
INSERT INTO MatHang (maMH, tenMH, Gia, trangThai) VALUES ('MH007', N'Bia Sapporo lon 330ml', 19000, 1);
INSERT INTO MatHang (maMH, tenMH, Gia, trangThai) VALUES ('MH008', N'Nước suối Aquafina', 10000, 1);
INSERT INTO MatHang (maMH, tenMH, Gia, trangThai) VALUES ('MH009', N'Nước ngọt Cocacola', 12000, 1);
INSERT INTO MatHang (maMH, tenMH, Gia, trangThai) VALUES ('MH010', N'Nước ngọt Pepsi', 12000, 1);


-- Tạo bảng KhachHang
CREATE TABLE KhachHang (
    maKH VARCHAR(50) PRIMARY KEY,
    tenKH NVARCHAR(255),
    SDT VARCHAR(20),
    GioiTinh BIT
);
GO

INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH001', N'Nguyễn Văn An', '0912345678', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH002', N'Trần Thị Bé', '0912345679', 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH003', N'Lê Văn Cường', '0912345680', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH004', N'Nguyễn Thị Duyên', '09121234567', 0);

INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH005', N'Nguyễn Văn Đức', '0394937937', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH006', N'Trần Mỹ Ngọc', '0398999999', 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH007', N'Lê Trung Dũng', '0399888888', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH008', N'Nguyễn Thị Duyên', '0904868686', 0);

INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH009', N'Nguyễn Quốc Huy', '0908888888', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH010', N'Trần Ngọc Giang', '0909999999', 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH011', N'Lê Thị Thanh Trang', '0914666666', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH012', N'Nguyễn Thanh Bảo', '0924444444', 0);

INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH013', N'Trương Thanh Hoài Phương', '0936543321', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH014', N'Khưu Ngọc Lân', '0937453865', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH015', N'Thái Hoàng Trung', '0939221267', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH016', N'Nguyễn Thị Bảo Châu', '0931778990', 0);

INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH017', N'Lưu Quốc Hùng', '0909111333', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH018', N'Trần Ngọc Giang', '0908567432', 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH019', N'Phạm Văn Tuấn', '0914544124', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH020', N'Đặng Tuấn Minh', '0929765435', 0);

INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH021', N'Hồ Duy Khanh', '034876459', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH022', N'Trần Mỹ Kiều', '0710655433', 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH023', N'Phan Thùy Vy', '0887652356', 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH024', N'Nguyễn Phương Thùy', '0456789875', 0);

INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH025', N'Nguyễn Hoàng Tuấn', '0398777777', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH026', N'Trần Đức Hiếu', '0904555555', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH027', N'Hoàng Quốc Huy Vũ', '0924111111', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH028', N'Nguyễn Huy Vỹ', '0919666666', 1);

INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH029', N'Nguyễn Huy Hoàng', '0782322321', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH030', N'Trần Gia Huy Hùng', '0792132134', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH031', N'Châu Thị Bảo My', '0919555666', 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH032', N'Đào Châu Kim Thùy', '0789453214', 0);

INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH033', N'Hồ Tuấn Nam', '0654222222', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH034', N'Đặng Thanh Thảo', '0854111111', 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH035', N'Phạm Việt Đăng', '0909150844', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH036', N'Nguyễn Phương Trang', '0377773456', 0);

INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH037', N'Hồ Tuấn Nam', '0908888789', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH038', N'Đặng Thanh Thảo', '0559999999', 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH039', N'Phạm Việt Đăng', '0911222643', 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, GioiTinh) VALUES ('KH040', N'Hồ Minh Hậu', '0585576500', 1);
-- Tạo bảng NhanVien
CREATE TABLE NhanVien (
    maNV VARCHAR(50) PRIMARY KEY,
    tenNV NVARCHAR(255),
    gioiTinh BIT,
    CCCD VARCHAR(20),
    SDT VARCHAR(20),
    diaChi NVARCHAR(255),
    caLam VARCHAR(50),
    loaiNV VARCHAR(50),
    FOREIGN KEY (loaiNV) REFERENCES LoaiNhanVien(maLoai)
);
GO

INSERT INTO NhanVien (maNV, tenNV, gioiTinh, CCCD, SDT, diaChi, caLam, loaiNV) 
VALUES ('NV001', N'Phạm Thị Thùy Nhi', 0, '079333444555', '0912345678', N'123 Dương Quảng Hàm quận Gò vấp', 'CA 1', 'NVQL');

INSERT INTO NhanVien (maNV, tenNV, gioiTinh, CCCD, SDT, diaChi, caLam, loaiNV) 
VALUES ('NV002', N'Nguyễn Thị Quỳnh Giang', 0, '079666777888', '0909666888', N'124 Chu Văn An quận Bình Thạnh', 'CA 2', 'NVQL');

INSERT INTO NhanVien (maNV, tenNV, gioiTinh, CCCD, SDT, diaChi, caLam, loaiNV) 
VALUES ('NV003', N'Hồ Minh Hậu', 1, '079999111222', '0963444121', N'125 Quang Trung quận Gò Vấp', 'CA 3', 'NVQL');

INSERT INTO NhanVien (maNV, tenNV, gioiTinh, CCCD, SDT, diaChi, caLam, loaiNV) 
VALUES ('NV004', N'Võ Minh Nhựt', 1, '079888999444', '0972143563', N'126 Lê Lợi quận Gò Vấp', 'CA 3', 'NVQL');

INSERT INTO NhanVien (maNV, tenNV, gioiTinh, CCCD, SDT, diaChi, caLam, loaiNV) 
VALUES ('NV005', N'Châu Thanh Huy', 1, '079567432123', '0979834347', N'3 Nguyễn An Ninh quận Bình Thạnh', 'CA 2', 'NVTN');

INSERT INTO NhanVien (maNV, tenNV, gioiTinh, CCCD, SDT, diaChi, caLam, loaiNV) 
VALUES ('NV006', N'Nguyễn Thùy Thanh', 1, '079888777231', '0967654321', N'425 Huỳnh Văn Bánh quận Phú Nhuận', 'CA 1', 'NVTN');


-- Tạo bảng PhongHat
CREATE TABLE PhongHat (
    maPhong VARCHAR(50) PRIMARY KEY,
    tenPhong VARCHAR(255),
    maLoaiPhong VARCHAR(50),
    tinhTrangPhong VARCHAR(255),
    FOREIGN KEY (maLoaiPhong) REFERENCES LoaiPhong(maLoaiPhong)
);
GO

INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P001', 'V001 ', 'LP001', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P002', 'V002 ', 'LP001', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P003', 'V003', 'LP001', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P004', 'T004 ', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P005', 'T005 ', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P006', 'T006', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P007', 'T007', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P008', 'T008', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P009', 'T009', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P010', 'T010', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P011', 'T011', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P012', 'T012', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P013', 'T013', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P014', 'T014', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P015', 'T015', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P016', 'T016', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P017', 'T017', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P018', 'T018', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P019', 'T019', 'LP001', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P020', 'T020', 'LP001', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P021', 'T021', 'LP001', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P022', 'T022', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P023', 'T023', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P024', 'T024', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P025', 'T025', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P026', 'T026', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P027', 'T027', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P028', 'T028', 'LP001', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P029', 'T029', 'LP001', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P030', 'T030', 'LP001', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P031', 'T031', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P032', 'T032', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P033', 'T033', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P034', 'T034', 'LP001', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P035', 'T035', 'LP001', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P036', 'T036', 'LP001', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P037', 'T037', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P038', 'T038', 'LP002', 'Trong');
INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P039', 'T039', 'LP002', 'Trong');

INSERT INTO PhongHat (maPhong, tenPhong, maLoaiPhong, tinhTrangPhong)
VALUES ('P040', 'T040', 'LP002', 'Trong');


-- Tạo bảng TaiKhoan
CREATE TABLE TaiKhoan (
    maNV VARCHAR(50) PRIMARY KEY,
    Password VARCHAR(150),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV)
);
GO


INSERT INTO TaiKhoan (maNV, Password) VALUES ('NV001', '123A');
INSERT INTO TaiKhoan (maNV, Password) VALUES ('NV002', '123A');
INSERT INTO TaiKhoan (maNV, Password) VALUES ('NV003', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=');
INSERT INTO TaiKhoan (maNV, Password) VALUES ('NV004', '123A');
INSERT INTO TaiKhoan (maNV, Password) VALUES ('NV005', '123A');


-- Tạo bảng DonDatPhong
CREATE TABLE DonDatPhong (
    maDonDatPhong VARCHAR(50),
    ngayDat DATETIME,
    ngayNhan DATETIME,
    maPhong VARCHAR(50),
    maKH VARCHAR(50),
    FOREIGN KEY (maPhong) REFERENCES PhongHat(maPhong),
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);
GO

INSERT INTO DonDatPhong (maDonDatPhong, ngayDat, ngayNhan, maPhong, maKH)
VALUES ('DDP001', '2023-10-31 13:00:00', '2023-11-01 13:00:00', 'P001', 'KH001');

INSERT INTO DonDatPhong (maDonDatPhong, ngayDat, ngayNhan, maPhong, maKH)
VALUES ('DDP002', '2023-11-05 13:00:00', '2023-11-06 13:00:00', 'P002', 'KH002');

INSERT INTO DonDatPhong (maDonDatPhong, ngayDat, ngayNhan, maPhong, maKH)
VALUES ('DDP003', '2023-11-10 13:00:00', '2023-11-11 13:00:00', 'P003', 'KH003');

INSERT INTO DonDatPhong (maDonDatPhong, ngayDat, ngayNhan, maPhong, maKH)
VALUES ('DDP004', '2023-11-15 13:00:00', '2023-11-16 13:00:00', 'P004', 'KH004');

INSERT INTO DonDatPhong (maDonDatPhong, ngayDat, ngayNhan, maPhong, maKH)
VALUES ('DDP005', '2023-11-25 13:00:00', '2023-11-26 13:00:00', 'P005', 'KH005');

INSERT INTO DonDatPhong (maDonDatPhong, ngayDat, ngayNhan, maPhong, maKH)
VALUES ('DDP006', '2023-11-29 13:00:00', '2023-11-30 13:00:00', 'P006', 'KH006');

INSERT INTO DonDatPhong (maDonDatPhong, ngayDat, ngayNhan, maPhong, maKH)
VALUES ('DDP007', '2023-12-01 13:00:00', '2023-12-02 13:00:00', 'P007', 'KH007');

INSERT INTO DonDatPhong (maDonDatPhong, ngayDat, ngayNhan, maPhong, maKH)
VALUES ('DDP008', '2023-12-05 13:00:00', '2023-12-06 13:00:00', 'P008', 'KH008');

GO
-- Tạo bảng KhuyenMai
CREATE TABLE KhuyenMai (
    maKM VARCHAR(50) PRIMARY KEY,
    moTa NVARCHAR(255),
	batDau DATETIME,
	ketThuc DATETIME,
	phanTram FLOAT
);
GO


INSERT INTO KhuyenMai(maKM, moTa, batDau, ketThuc, phanTram)
VALUES ('KM001', 'Noel 2023', '2023-12-07 00:00:00.000', '2023-12-25 00:00:00.000', 10.00);

INSERT INTO KhuyenMai(maKM, moTa, batDau, ketThuc, phanTram)
VALUES ('KM002', 'Tết Tây 2023', '2023-12-25 00:00:00.000', '2023-01-01 00:00:00.000', 20.00);


CREATE TABLE HoaDon (
    maHD VARCHAR(50) PRIMARY KEY,
    ngayLapHD DATE,
    maKH VARCHAR(50),
    maNV VARCHAR(50),
	maKM VARCHAR(50),
    tongTien MONEY,
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV),
/*	FOREIGN KEY (maKM) REFERENCES KhuyenMai(maKM)*/
);
GO


-- Thêm dữ liệu vào bảng HoaDon
INSERT INTO HoaDon (maHD, ngayLapHD, maKH, maNV, maKM, tongTien)
VALUES ('HD001', '2023-10-30', 'KH001', 'NV001', 'KM001', 1000000.00);

INSERT INTO HoaDon (maHD, ngayLapHD, maKH, maNV, maKM, tongTien)
VALUES ('HD002', '2023-10-31', 'KH002', 'NV002', 'KM002', 1500000.00);

INSERT INTO HoaDon (maHD, ngayLapHD, maKH, maNV, maKM, tongTien)
VALUES ('HD003', '2023-11-01', 'KH003', 'NV003', 'KM003', 800000.00);

INSERT INTO HoaDon (maHD, ngayLapHD, maKH, maNV, maKM, tongTien)
VALUES ('HD004', '2023-11-02', 'KH004', 'NV004', 'KM004', 1200000.00);

-- Tạo bảng ChiTietHoaDonDV
CREATE TABLE ChiTietHoaDonDV (
    maHD VARCHAR(50),
    maMH VARCHAR(50),
	maPhong VARCHAR(50),
    soLuong INT,
    gia FLOAT,
    PRIMARY KEY (maHD, maMH,maPhong),
    FOREIGN KEY (maHD) REFERENCES HoaDon(maHD),
    FOREIGN KEY (maMH) REFERENCES MatHang(maMH),
	FOREIGN KEY (maPhong) REFERENCES PhongHat(maPhong)
);
GO

-- Thêm dữ liệu vào bảng ChiTietHoaDonDV
INSERT INTO ChiTietHoaDonDV (maHD, maMH,maPhong, soLuong, gia)
VALUES ('HD001', 'MH001','P001', 1, 100000);

INSERT INTO ChiTietHoaDonDV (maHD, maMH,maPhong, soLuong, gia)
VALUES ('HD002', 'MH002','P002', 1, 120000);

INSERT INTO ChiTietHoaDonDV (maHD, maMH,maPhong, soLuong, gia)
VALUES ('HD003', 'MH003','P003', 1, 130000);

INSERT INTO ChiTietHoaDonDV (maHD, maMH,maPhong, soLuong, gia)
VALUES ('HD004', 'MH004','P004', 1, 140000);

-- Tạo bảng ChiTietHoaDonPhong
CREATE TABLE ChiTietHoaDonPhong (
    maHD VARCHAR(50),
    maPhong VARCHAR(50),
    gia FLOAT,
    gioVao DATETIME,
    gioRa DATETIME,
    ghiChu VARCHAR(150),
    PRIMARY KEY (maHD, maPhong),
    FOREIGN KEY (maHD) REFERENCES HoaDon(maHD),
    FOREIGN KEY (maPhong) REFERENCES PhongHat(maPhong)
);
GO

-- Thêm dữ liệu vào bảng ChiTietHoaDonPhong
INSERT INTO ChiTietHoaDonPhong (maHD, maPhong, gia, gioVao, gioRa, ghiChu)
VALUES ('HD001', 'P001', 100000, '2023-10-30 13:00:00', '2023-10-30 15:00:00', '');

INSERT INTO ChiTietHoaDonPhong (maHD, maPhong, gia, gioVao, gioRa, ghiChu)
VALUES ('HD002', 'P002', 100000, '2023-10-30 15:00:00', '2023-10-30 17:00:00', '');

INSERT INTO ChiTietHoaDonPhong (maHD, maPhong, gia, gioVao, gioRa, ghiChu)
VALUES ('HD003', 'P003', 60000, '2023-10-30 12:00:00', '2023-10-30 15:00:00', '');

INSERT INTO ChiTietHoaDonPhong (maHD, maPhong, gia, gioVao, gioRa, ghiChu)
VALUES ('HD004', 'P004', 60000, '2023-10-30 11:00:00', '2023-10-30 13:00:00', '');


