
CREATE TABLE Roles (
  roleId INT PRIMARY KEY,
  roleName NVARCHAR(255) NOT NULL
);

CREATE TABLE UserStatus (
    statusID INT PRIMARY KEY,
    statusName VARCHAR(255) NOT NULL
);

CREATE TABLE Users (
  userId INT PRIMARY KEY IDENTITY(1,1),
  userName NVARCHAR(255) NOT NULL,
  name NVARCHAR(255) NOT NULL,
  phoneNumber NVARCHAR(255) NOT NULL,
  email NVARCHAR(255) NOT NULL,
  password NVARCHAR(255) NOT NULL,
  address NVARCHAR(MAX) NOT NULL,
  avatar NVARCHAR(255),
  roleID INT NOT NULL,
  statusID INT NOT NULL,
  FOREIGN KEY (roleID) REFERENCES Roles(roleID),
  FOREIGN KEY (StatusID) REFERENCES UserStatus(StatusID)
);



CREATE TABLE Size (
  sizeId INT PRIMARY KEY IDENTITY(1,1),
  sizeName NVARCHAR(255) NOT NULL
);

CREATE TABLE Status (
  statusId INT PRIMARY KEY,
  statusName NVARCHAR(255) NOT NULL
);

CREATE TABLE Category (
  categoryId INT PRIMARY KEY IDENTITY(1,1),
  categoryName NVARCHAR(255) NOT NULL
);

CREATE TABLE Products (
  productId INT PRIMARY KEY IDENTITY(1,1),
  userId INT NOT NULL,
  productName NVARCHAR(255) NOT NULL,
  description NVARCHAR(MAX) NOT NULL,
  categoryId INT NOT NULL,
  statusId INT NOT NULL,
  thumbnail NVARCHAR(MAX) NOT NULL,
  FOREIGN KEY (categoryId) REFERENCES Category(CategoryId),
  FOREIGN KEY (statusId) REFERENCES Status(statusID),
  FOREIGN KEY (userId) REFERENCES Users(userId)
);

CREATE TABLE Informations (
  informationId INT PRIMARY KEY IDENTITY(1,1),
  userId INT NOT NULL,
  description NVARCHAR(MAX),
  nameStore NVARCHAR(255),
  image VARCHAR(255),
  address NVARCHAR(255),
  contactPhone NVARCHAR(255),
  contactEmail NVARCHAR(255),
  contactFacebook NVARCHAR(255),
  FOREIGN KEY (userId) REFERENCES Users(userId)
);

CREATE TABLE ProductSize (
  productId INT,
  sizeId INT,
  price DECIMAL NOT NULL,
  PRIMARY KEY (productId, sizeId),
  FOREIGN KEY (productId) REFERENCES Products(productId),
  FOREIGN KEY (sizeId) REFERENCES Size(sizeId)
);

CREATE TABLE Cart (
  cartId INT PRIMARY KEY IDENTITY(1,1),
  userId INT NOT NULL,
  productId INT NOT NULL,
  sizeId INT NOT NULL,
  quantity INT NOT NULL,
  price DECIMAL NOT NULL,
  totalPrice DECIMAL NOT NULL,
  FOREIGN KEY (userId) REFERENCES Users(userId),
  FOREIGN KEY (productId, sizeId) REFERENCES ProductSize(productId, sizeId)
);

CREATE TABLE PriorityLevel (
  priorityId INT PRIMARY KEY,
  priorityName NVARCHAR(255) NOT NULL
);

CREATE TABLE OrderStatus (
  statusId INT PRIMARY KEY,
  statusName NVARCHAR(255) NOT NULL
);


CREATE TABLE Voucher (
  voucherId INT PRIMARY KEY IDENTITY(1,1),
  userId INT NOT NULL,
  voucherName NVARCHAR(255) NOT NULL,
  description NVARCHAR(MAX) NOT NULL,
  voucherCode NVARCHAR(255) NOT NULL,
  voucherImage NVARCHAR(255) NOT NULL,
  discount DECIMAL NOT NULL,
  startedDate DATE NOT NULL,
  expirationDate DATE NOT NULL,
  condition INT NOT NULL,
  FOREIGN KEY (userId) REFERENCES Users(userId)
);

CREATE TABLE Orders (
  orderId INT PRIMARY KEY IDENTITY(1,1),
  orderCode NVARCHAR(50) NOT NULL,
  userId INT NOT NULL,
  salerId INT NOT NULL,
  orderTime DATETIME NOT NULL,
  statusId INT NOT NULL,
  receiverName NVARCHAR(255) NOT NULL,
  receiverAddress NVARCHAR(255) NOT NULL,
  receiverPhone NVARCHAR(255) NOT NULL,
  voucherId INT NOT NULL,
  totalAmount DECIMAL NOT NULL,
  discountAmount DECIMAL NOT NULL,
  grandTotal DECIMAL NOT NULL,
  FOREIGN KEY (userId) REFERENCES Users(userId),
  FOREIGN KEY (statusId) REFERENCES OrderStatus(statusId),
  FOREIGN KEY (salerId) REFERENCES Users(userId),
  FOREIGN KEY (voucherId) REFERENCES Voucher(voucherId)
);

CREATE TABLE OrderDetails (
  orderDetailId INT PRIMARY KEY IDENTITY(1,1),
  orderId INT NOT NULL,
  productId INT NOT NULL,
  sizeId INT NOT NULL,
  quantity INT NOT NULL,
  price DECIMAL NOT NULL,
  FOREIGN KEY (orderId) REFERENCES Orders(orderId),
  FOREIGN KEY (productId, sizeId) REFERENCES ProductSize(productId, sizeId)
);

CREATE TABLE News (
  newsId INT PRIMARY KEY IDENTITY(1,1),
  userId INT NOT NULL,
  title NVARCHAR(MAX) NOT NULL,
  image NVARCHAR(255),
  content NVARCHAR(MAX) NOT NULL,
  postedTime DATETIME NOT NULL,
  priorityId INT,
  FOREIGN KEY (priorityId) REFERENCES PriorityLevel(priorityId),
  FOREIGN KEY (userId) REFERENCES Users(userId)
);



CREATE TABLE Slider (
  sliderId INT PRIMARY KEY IDENTITY(1,1),
  image NVARCHAR(255) NOT NULL,
  [order] INT NOT NULL,
  link NVARCHAR(MAX) NOT NULL,
  status INT NOT NULL,
  FOREIGN KEY (status) REFERENCES Status(statusID)
);

CREATE TABLE Feedbacks (
  feedbackId INT PRIMARY KEY IDENTITY(1,1),
  productId INT NOT NULL,
  userId INT NOT NULL,
  ratedStar INT NOT NULL,
  image NVARCHAR(250),
  message NVARCHAR(MAX) NOT NULL,
  statusId INT NOT NULL,
  feedbackTime DATETIME NOT NULL,
  FOREIGN KEY (productId) REFERENCES Products(productId),
  FOREIGN KEY (userId) REFERENCES Users(userId),
  FOREIGN KEY (statusId) REFERENCES Status(statusId)
);



INSERT INTO Roles (roleId, roleName) VALUES
(1, 'Admin'),
(2, 'Marketer'),
(3, 'Saler'),
(4, 'Customer'),
(5, 'Saler Manager');

INSERT INTO UserStatus (statusID, statusName) VALUES
(1, 'Active'),
(2, 'Inactive');

INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('ADM0001', N'Đặng Quý Dương', '0961692085', 'duongdqhe176508@fpt.edu.vn', 'admin123', N'Số nhà 7, Thôn Bình Minh', 'anhdaidien.jpg', '1', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('MKT0002', N'Trần Thị Minh Thư', '0964017318', 'thuttmhe171462@fpt.edu.vn', 'marketer123', N'Số nhà 15, Thôn Tân Lập', 'anhdaidien.jpg', '2', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('MKT0003', N'Nguyễn Thị Hương', '0934567890', 'nguyenthihuong@gmail.com', '12345678', N'Số nhà 22, Thôn Hòa Bình', 'anhdaidien.jpg', '2', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('MKT0004', N'Lê Văn Đức', '0932109876', 'levanduc@gmail.com', '12345678', N'Số nhà 11, Thôn Phước An', 'anhdaidien.jpg', '2', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('MKT0005', N'Trần Thị Mai', '0938765432', 'tranthimai@gmail.com', '12345678', N'Số nhà 5, Thôn Kim Đồng', 'anhdaidien.jpg', '2', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('SAL0006', N'Nguyễn Đức Trường', '0901234567', 'nguyenductruong@gmail.com', '12345678', N'Số nhà 9, Thôn Hoàng Gia', 'anhdaidien.jpg', '3', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('SAL0007', N'Hoàng Thị Lan', '0909876543', 'hoangthilan@gmail.com', '12345678', N'Số nhà 14, Thôn Hưng Thịnh', 'anhdaidien.jpg', '3', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('SAL0008', N'Đặng Minh Tuấn', '0906543210', 'dangminhtuan@gmail.com', '12345678', N'Số nhà 18, Thôn Xuân Thanh', 'anhdaidien.jpg', '3', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('SAL0009', N'Nguyễn Thu Hà', '0939876543', 'nguyenthuha@gmail.com', '12345678', N'Số nhà 25, Thôn An Phú', 'anhdaidien.jpg', '3', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('SAL0010', N'Vũ Đức Anh', '0938765432', 'vuducanh@gmail.com', '12345678', N'Số nhà 3, Thôn Đại Thắng', 'anhdaidien.jpg', '3', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('SM0011', N'Bùi Thùy Linh', '0932345678', 'buithuylinh@gmail.com', '12345678', N'Số nhà 12, Thôn Minh Khai', 'anhdaidien.jpg', '5', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('CUS0012', N'Đặng Quang Anh', '0909876543', 'dangquanganh@gmail.com', '12345678', N'Số nhà 6, Thôn Nam Hà', 'anhdaidien.jpg', '4', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('CUS0013', N'Bùi Thị Hồng', '0903456789', 'buithihong@gmail.com', '12345678', N'Số nhà 8, Thôn Yên Phú', 'anhdaidien.jpg', '4', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('CUS0014', N'Nguyễn Văn Trung', '0909876543', 'nguyenvantrung@gmail.com', '12345678', N'Số nhà 17, Thôn Tân An', 'anhdaidien.jpg', '4', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('CUS0015', N'Mai Thị Ngọc', '0934567890', 'maithingoc@gmail.com', '12345678', N'Số nhà 4, Thôn Long Hưng', 'anhdaidien.jpg', '4', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('CUS0016', N'Đỗ Văn Hùng', '0906789012', 'dovanhung@gmail.com', '12345678', N'Số nhà 21, Thôn Bắc Sơn', 'anhdaidien.jpg', '4', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('CUS0017', N'Lý Thị Hằng', '0901234567', 'lythihang@gmail.com', '12345678', N'Số nhà 13, Thôn Quang Trung', 'anhdaidien.jpg', '4', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('CUS0018', N'Nguyễn Minh Hòa', '0908765432', 'nguyenminhhoa@gmail.com', '12345678', N'Số nhà 19, Thôn Hòa Thuận', 'anhdaidien.jpg', '4', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('CUS0019', N'Trần Văn Hải', '0902345678', 'tranvanhai@gmail.com', '12345678', N'Số nhà 2, Thôn Nam Sơn', 'anhdaidien.jpg', '4', '1 ');
INSERT INTO Users (userName, name, phoneNumber, email, password, address, avatar, roleID, statusID ) VALUES ('CUS0020', N'Hoàng Thị Hạnh', '0937890123', 'hoangthihanh@gmail.com', '12345678', N'Số nhà 16, Thôn Tây Hồ', 'anhdaidien.jpg', '4', '1 ');


INSERT INTO Status (statusId, statusName) VALUES
(1, 'Active'),
(2, 'Inactive');

INSERT INTO Category (categoryName) VALUES (N'Cà phê');
INSERT INTO Category (categoryName) VALUES (N'Trà');
INSERT INTO Category (categoryName) VALUES (N'Cloud');
INSERT INTO Category (categoryName) VALUES (N'Hi-Tea');
INSERT INTO Category (categoryName) VALUES (N'Trà xanh');
INSERT INTO Category (categoryName) VALUES (N'Thức uống đá xay');
INSERT INTO Category (categoryName) VALUES (N'Bánh&Snack');


INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('2', N'Đường đen sữa đá', N'Nếu chuộng vị cà phê đậm đà, bùng nổ và thích vị đường đen ngọt thơm, Đường Đen Sữa Đá đích thị là thức uống dành cho bạn. Không chỉ giúp bạn tỉnh táo buổi sáng, Đường Đen Sữa Đá còn hấp dẫn đến ngụm cuối cùng bởi thạch cà phê giòn dai, nhai cực cuốn. - Khuấy đều trước khi sử dụng', '1', '1', 'duongdensuada.jpg');
INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('2', N'Cà phê sữa đá', N'Cà phê Đắk Lắk nguyên chất được pha phin truyền thống kết hợp với sữa đặc tạo nên hương vị đậm đà, hài hòa giữa vị ngọt đầu lưỡi và vị đắng thanh thoát nơi hậu vị.', '1', '1', 'caphesuada.jpg');
INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('2', N'Cà phê sữa nóng', N'Cà phê được pha phin truyền thống kết hợp với sữa đặc tạo nên hương vị đậm đà, hài hòa giữa vị ngọt đầu lưỡi và vị đắng thanh thoát nơi hậu vị.', '1', '1', 'caphesuanong.jpg');
INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('2', N'Trà đào cam sả', N'Vị thanh ngọt của đào, vị chua dịu của Cam Vàng nguyên vỏ, vị chát của trà đen tươi được ủ mới mỗi 4 tiếng, cùng hương thơm nồng đặc trưng của sả chính là điểm sáng làm nên sức hấp dẫn của thức uống này.', '2', '1', 'tradaocamsa.jpg');
INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('3', N'Trà long nhãn hạt sen', N'Thức uống mang hương vị của nhãn, của sen, của trà Oolong đầy thanh mát cho tất cả các thành viên trong dịp Tết này. An lành, thư thái và đậm đà chính là những gì The Coffee House mong muốn gửi trao đến bạn và gia đình.', '2', '1', 'tralongnhanhatsen.jpg');
INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('3', N'Cloud-Fee hạnh nhân nướng', N'Vị đắng nhẹ từ cà phê phin truyền thống kết hợp Espresso Ý, lẫn chút ngọt ngào của kem sữa và lớp foam trứng cacao, nhấn thêm hạnh nhân nướng thơm bùi, kèm topping thạch cà phê dai giòn mê ly. Tất cả cùng quyện hoà trong một thức uống làm vị giác "thức giấc", thơm ngon hết nấc.', '3', '1', 'cloudfeehanhhnhannuong.jpg');
INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('3', N'CloudFee caramel', N'Ngon khó cưỡng bởi xíu đắng nhẹ từ cà phê phin truyền thống pha trộn với Espresso lừng danh nước Ý, quyện vị kem sữa và caramel ngọt ngọt, thêm lớp foam trứng cacao bồng bềnh béo mịn, kèm topping thạch cà phê dai giòn nhai cực cuốn. Một thức uống "điểm mười" cho cả ngày tươi không cần tưới.', '3', '1', 'cloudfeecaramel.jpg');
INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('4', N'Hi Tea Đào Kombucha', N'Trà hoa Hibiscus 0% caffeine chua nhẹ, kết hợp cùng trà lên men Kombucha hoàn toàn tự nhiên và Đào thanh mát tạo nên Hi-Tea Đào Kombucha chua ngọt cực cuốn. Đặc biệt Kombucha Detox giàu axit hữu cơ, Đào nhiều chất xơ giúp thanh lọc cơ thể và hỗ trợ giảm cân hiệu quả. Lưu ý: Khuấy đều trước khi dùng', '4', '1', 'hiteadaokombucha.jpg');
INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('4', N'Hi Tea Đào', N'Sự kết hợp ăn ý giữa Đào cùng trà hoa Hibiscus, tạo nên tổng thể hài hoà dễ gây “thương nhớ” cho team thích món thanh mát, có vị chua nhẹ.', '4', '1', 'hiteadao.jpg');
INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('4', N'Frosty Trà Xanh', N'Đá Xay Frosty Trà Xanh như lời mời mộc mạc, ghé thăm Tây Bắc vào những ngày tiết trời se lạnh, núi đèo mây phủ. Vị chát dịu, ngọt thanh của trà xanh Tây Bắc kết hợp đá xay sánh mịn, whipping cream bồng bềnh và bột trà xanh trên cùng thêm đậm vị. Đây không chỉ là thức uống mát lạnh bật mood, mà còn tốt cho sức khoẻ nhờ giàu vitamin và các chất chống oxy hoá.', '5', '1', 'frostytraxanh.jpg');
INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('5', N'Trà Xanh Đường Đen', N'Trà Xanh Đường Đen với hiệu ứng phân tầng đẹp mắt, như phác hoạ núi đồi Tây Bắc bảng lảng trong sương mây, thấp thoáng những nương chè xanh ngát. Từng ngụm là sự hài hoà từ trà xanh đậm đà, đường đen ngọt ngào, sữa tươi thơm béo. Khuấy đều trước khi dùng, để thưởng thức đúng vị', '5', '1', 'traxanhduongden.jpg');
INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('5', N'Frosty Choco Chip', N'Đá Xay Frosty Choco Chip, thử là đã! Lớp whipping cream bồng bềnh, beo béo lại có thêm bột sô cô la và sô cô la chip trên cùng. Gấp đôi vị ngon với sô cô la thật xay với đá sánh mịn, đậm đà đến tận ngụm cuối cùng.', '6', '1', 'frostychocochip.jpg');
INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('5', N'Frosty Cà Phê Đường Đen', N'Đá Xay Frosty Cà Phê Đường Đen mát lạnh, sảng khoái ngay từ ngụm đầu tiên nhờ sự kết hợp vượt chuẩn vị quen giữa Espresso đậm đà và Đường Đen ngọt thơm. Đặc biệt, whipping cream beo béo cùng thạch cà phê giòn dai, đậm vị nhân đôi sự hấp dẫn, khơi bừng sự hứng khởi trong tích tắc.', '6', '1', 'frostycapheduongden.jpg');
INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('5', N'Bánh Mì VN Thịt Nguội', N'Gói gọn trong ổ bánh mì Việt Nam là từng lớp chả, từng lớp jambon hòa quyện cùng bơ và pate thơm lừng, thêm dưa rau cho bữa sáng đầy năng lượng. *Phần bánh sẽ ngon và đậm đà nhất khi kèm pate. Để đảm bảo hương vị được trọn vẹn, Nhà mong bạn thông cảm vì không thể thay đổi định lượng pate.', '7', '1', 'banhmithitnguoi.jpg');
INSERT INTO Products (userId, productName, description, categoryId, statusId, thumbnail ) VALUES ('5', N'Croissant trứng muối', N'Croissant trứng muối thơm lừng, bên ngoài vỏ bánh giòn hấp dẫn bên trong trứng muối vị ngon khó cưỡng.', '7', '1', 'croissanttrungmuoi.jpg');




INSERT INTO Size ( sizeName) VALUES
('S'),
('M'),
('L');


INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('1', '1', '49000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('1', '2', '53000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('1', '3', '58000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('2', '1', '29000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('2', '2', '35000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('2', '3', '39000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('3', '1', '39000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('3', '2', '45000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('3', '3', '51000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('4', '1', '29000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('4', '2', '35000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('4', '3', '39000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('5', '1', '29000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('5', '2', '35000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('5', '3', '39000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('6', '1', '29000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('6', '2', '35000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('6', '3', '39000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('7', '1', '49000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('7', '2', '53000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('7', '3', '58000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('8', '1', '49000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('8', '2', '53000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('8', '3', '58000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('9', '1', '49000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('9', '2', '53000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('9', '3', '58000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('10', '1', '58000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('10', '2', '68000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('10', '3', '73000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('11', '1', '49000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('11', '2', '53000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('11', '3', '58000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('12', '1', '25000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('12', '2', '39000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('12', '3', '53000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('13', '1', '39000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('13', '2', '53000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('13', '3', '58000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('14', '1', '10000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('14', '2', '15000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('14', '3', '25000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('15', '1', '25000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('15', '2', '30000 ');
INSERT INTO ProductSize (productId, sizeId, price ) VALUES ('15', '3', '39000');

           
INSERT INTO Informations (userId, description, nameStore, image, address, contactPhone, contactEmail, contactFacebook ) VALUES ('3', N'The CoffeeLand sẽ là nơi mọi người xích lại gần nhau, đề cao giá trị kết nối con người và sẻ chia thân tình bên những tách cà phê, ly trà đượm hương, truyền cảm hứng về lối sống hiện đại.', N'The CoffeeLand', 'logo.png', N'Khu Công nghệ cao Hòa Lạc, Km29 Đại lộ Thăng Long, huyện Thạch Thất, Hà Nội', '0964017318', 'thecoffeeland@gmail.com', 'https://www.facebook.com/m.thutrant.fpt');


INSERT INTO PriorityLevel (priorityId, priorityName) VALUES
(1, 'High'),
(2, 'Medium'),
(3, 'Low');

INSERT INTO OrderStatus (statusId, statusName) VALUES
(1, N'Chờ xác nhận'),
(2, N'Đang chuẩn bị'),
(3, N'Đang giao hàng'),
(4, N'Giao hàng thành công'),
(5, N'Giao hàng không thành công');


INSERT INTO Voucher (userId, voucherName, description, voucherCode, voucherImage, discount, startedDate, expirationDate, condition) 
VALUES ('2', N'Không có voucher', N'Không có', 'khongco', 'khong co voucher', '0', '2000-01-01', '2100-12-31', '0');

INSERT INTO Voucher (userId, voucherName, description, voucherCode, voucherImage, discount, startedDate, expirationDate, condition) 
VALUES ('2', N'TRI ÂN KHÁCH HÀNG', N'Giảm giá 2% cho tất cả các đơn hàng', 'GIAMGIA2', 'voucher1.jpg', '2', '2024-02-15', '2024-03-02', '0');

INSERT INTO Voucher (userId, voucherName, description, voucherCode, voucherImage, discount, startedDate, expirationDate, condition) 
VALUES ('2', N'TẾT THẢ GA', N'Giảm giá 5% cho tất cả các đơn hàng có tổng thanh toán 50,000 đồng', 'GIAMGIA5', 'voucher2.jpg', '5', '2024-02-16', '2024-03-03', '50000');

INSERT INTO Voucher (userId, voucherName, description, voucherCode, voucherImage, discount, startedDate, expirationDate, condition) 
VALUES ('2', N'TẾT AN LÀNH', N'Giảm giá 10% cho tất cả các đơn hàng có tổng thanh toán 50,000 đồng', 'GIAMGIA10', 'voucher3.jpg', '10', '2024-02-17', '2024-03-31', '50000');

INSERT INTO Voucher (userId, voucherName, description, voucherCode, voucherImage, discount, startedDate, expirationDate, condition) 
VALUES ('5', N'THÁNG MỚI MAY MẮN', N'Giảm giá 12% cho tất cả các đơn hàng có tổng thanh toán 50,000 đồng', 'GIAMGIA12', 'voucher4.jpg', '12', '2024-03-02', '2024-04-01', '50000');

INSERT INTO Voucher (userId, voucherName, description, voucherCode, voucherImage, discount, startedDate, expirationDate, condition) 
VALUES ('5', N'ĐẦU THÁNG AN LÀNH', N'Giảm giá 15% cho tất cả các đơn hàng có tổng thanh toán 100,000 đồng', 'GIAMGIA15', 'voucher5.jpg', '15', '2024-03-03', '2024-04-02', '100000');

INSERT INTO Voucher (userId, voucherName, description, voucherCode, voucherImage, discount, startedDate, expirationDate, condition) 
VALUES ('3', N'CÙNG NHAU VUI VE', N'Giảm giá 8% cho tất cả các đơn hàng có tổng thanh toán 100,000 đồng', 'GIAMGIA8', 'voucher6.jpg', '8', '2024-03-04', '2024-04-03', '100000');

INSERT INTO Voucher (userId, voucherName, description, voucherCode, voucherImage, discount, startedDate, expirationDate, condition) 
VALUES ('3', N'ĐẠI GIẢM GIÁ', N'Giảm giá 20% cho tất cả các đơn hàng có tổng thanh toán 100,000 đồng', 'GIAMGIA20', 'voucher7.jpg', '20', '2024-03-05', '2024-04-04', '100000');

INSERT INTO Voucher (userId, voucherName, description, voucherCode, voucherImage, discount, startedDate, expirationDate, condition) 
VALUES ('4', N'ĐƠN HÀNG 0 ĐỒNG', N'Giảm giá 10% cho tất cả các đơn hàng', 'GIAMGIA10', 'voucher8.jpg', '10', '2024-03-10', '2024-04-05', '0');

INSERT INTO Voucher (userId, voucherName, description, voucherCode, voucherImage, discount, startedDate, expirationDate, condition) 
VALUES ('4', N'ĐƠN HÀNG CAO ĐÃ CÓ VOUCHER', N'Giảm giá 12% cho tất cả các đơn hàng có tổng thanh toán 150,000 đồng', 'GIAMGIA12', 'voucher9.jpg', '12', '2024-03-11', '2024-04-06', '150000');

INSERT INTO Voucher (userId, voucherName, description, voucherCode, voucherImage, discount, startedDate, expirationDate, condition) 
VALUES ('3', N'TRI ÂN QUÝ KHÁCH', N'Giảm giá 8% cho tất cả các đơn hàng có tổng thanh toán 50,000 đồng', 'GIAMGIA8', 'vouche10.jpg', '8', '2024-03-12', '2024-04-07', '50000');



INSERT INTO Orders (orderCode, userId, salerId, orderTime, statusId, receiverName, receiverAddress, receiverPhone, voucherId, totalAmount, discountAmount, grandTotal) VALUES ('ORD000001', '12', '6', '2024-01-15 08:27:45', '4', N'Đặng Quang Anh', N'Số nhà 6, Thôn Nam Hà', '0909876543', '1', '53000', '0', '53000');
INSERT INTO Orders (orderCode, userId, salerId, orderTime, statusId, receiverName, receiverAddress, receiverPhone, voucherId, totalAmount, discountAmount, grandTotal) VALUES ('ORD000002', '13', '7', '2024-02-05 17:09:31', '4', N'Bùi Thị Hồng', N'Số nhà 8, Thôn Yên Phú', '0903456789', '2', '143000', '2860', '140140');
INSERT INTO Orders (orderCode, userId, salerId, orderTime, statusId, receiverName, receiverAddress, receiverPhone, voucherId, totalAmount, discountAmount, grandTotal) VALUES ('ORD000003', '14', '8', '2024-02-20 10:45:12', '4', N'Nguyễn Văn Trung', N'Số nhà 17, Thôn Tân An', '0909876543', '2', '53000', '1060', '51940');
INSERT INTO Orders (orderCode, userId, salerId, orderTime, statusId, receiverName, receiverAddress, receiverPhone, voucherId, totalAmount, discountAmount, grandTotal) VALUES ('ORD000004', '15', '9', '2024-03-03 14:36:29', '5', N'Mai Thị Ngọc', N'Số nhà 4, Thôn Long Hưng', '0934567890', '4', '458000', '45800', '412200');
INSERT INTO Orders (orderCode, userId, salerId, orderTime, statusId, receiverName, receiverAddress, receiverPhone, voucherId, totalAmount, discountAmount, grandTotal) VALUES ('ORD000005', '16', '10', '2024-03-10 09:51:50', '4', N'Đỗ Văn Hùng', N'Số nhà 21, Thôn Bắc Sơn', '0906789012', '4', '198000', '19800', '178200');
INSERT INTO Orders (orderCode, userId, salerId, orderTime, statusId, receiverName, receiverAddress, receiverPhone, voucherId, totalAmount, discountAmount, grandTotal) VALUES ('ORD000006', '17', '6', '2024-03-12 20:15:06', '4', N'Lý Thị Hằng', N'Số nhà 13, Thôn Quang Trung', '0901234567', '4', '116000', '11600', '104400');
INSERT INTO Orders (orderCode, userId, salerId, orderTime, statusId, receiverName, receiverAddress, receiverPhone, voucherId, totalAmount, discountAmount, grandTotal) VALUES ('ORD000007', '18', '7', '2024-03-14 06:40:18', '4', N'Nguyễn Minh Hòa', N'Số nhà 19, Thôn Hòa Thuận', '0908765432', '4', '198000', '19800', '178200');
INSERT INTO Orders (orderCode, userId, salerId, orderTime, statusId, receiverName, receiverAddress, receiverPhone, voucherId, totalAmount, discountAmount, grandTotal) VALUES ('ORD000008', '19', '8', '2024-03-16 12:58:37', '4', N'Trần Văn Hải', N'Số nhà 2, Thôn Nam Sơn', '0902345678', '4', '106000', '10600', '95400');
INSERT INTO Orders (orderCode, userId, salerId, orderTime, statusId, receiverName, receiverAddress, receiverPhone, voucherId, totalAmount, discountAmount, grandTotal) VALUES ('ORD000009', '20', '9', '2024-03-18 15:20:50', '1', N'Hoàng Thị Hạnh', N'Số nhà 16, Thôn Tây Hồ', '0937890123', '4', '103000', '10300', '92700');
INSERT INTO Orders (orderCode, userId, salerId, orderTime, statusId, receiverName, receiverAddress, receiverPhone, voucherId, totalAmount, discountAmount, grandTotal) VALUES ('ORD000010', '12', '10', '2024-03-19 11:03:43', '1', N'Đặng Quang Anh', N'Số nhà 6, Thôn Nam Hà', '0909876543', '4', '128000', '12800', '115200');



INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('1', '1', '2', '1', '53000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('2', '1', '2', '1', '53000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('2', '3', '2', '2', '45000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('3', '7', '2', '1', '53000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('4', '3', '2', '4', '45000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('4', '4', '2', '2', '35000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('4', '8', '1', '2', '49000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('5', '1', '2', '3', '53000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('5', '3', '1', '1', '39000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('6', '1', '3', '2', '58000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('7', '1', '2', '1', '53000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('7', '3', '2', '1', '45000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('7', '14', '3', '1', '25000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('7', '15', '2', '1', '39000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('8', '1', '2', '2', '53000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('9', '1', '3', '1', '58000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('9', '3', '2', '1', '45000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('10', '2', '2', '1', '35000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('10', '5', '2', '2', '35000 ');
INSERT INTO OrderDetails (orderId, productId, sizeId, quantity, price ) VALUES ('10', '7', '3', '1', '58000');



INSERT INTO News (userId , title , image , content , postedTime , priorityId ) VALUES ('2 ', N'BẮT GẶP SÀI GÒN XƯA TRONG MÓN UỐNG HIỆN ĐẠI CỦA GIỚI TRẺ ', 'news1.jpg ', N'Trong số những “món ruột” nhất định phải thử ấy, bánh flan cà phê đá bào được xem là hương vị ngọt ngào bất hủ của nhiều thế hệ. Bánh flan mềm mịn, ăn cùng đá được bào nhuyễn, rưới thêm cà phê thơm đậm đà là thức quà vặt dễ ăn, dễ mê, cũng dễ bắt gặp trên mọi con phố. Dù ảnh hưởng từ món tráng miệng Crème Brulée lừng danh của Pháp, nhưng người Sài Gòn xưa đã nhanh chóng biến bánh flan thành “đặc sản” của riêng mình. Nếu có dịp xem lại những thước phim cũ về Sài Gòn, người ta sẽ thấy long lanh giữa trưa hè nóng nực là ánh mắt hồ hởi của mấy đứa trẻ con đón lấy đĩa bánh flan cà phê và thưởng thức trong niềm vui sướng. Một món ăn bình dị, nhưng là cả một bầu trời lấp lánh niềm vui sướng hồn nhiên. ', '2024-02-09 12:00:00 ', '1 ');
INSERT INTO News (userId , title , image , content , postedTime , priorityId ) VALUES ('2 ', N'CHỈ CHỌN CÀ PHÊ MỖI SÁNG ', 'news2.jpg ', N'"Tác giả ""Đối thoại với thượng đế"" - Neale Donald Walsch từng nói: ""Cuộc sống chỉ thật sự bắt đầu khi bạn bước ra khỏi vùng an toàn của mình"". Việc ""thoát khỏi vùng an toàn"" đôi khi chỉ bắt đầu từ việc nhỏ thường nhật. Chẳng hạn như thử thay đổi một thức uống mới mẻ.', '2024-02-10 13:00:00 ', '1 ');
INSERT INTO News (userId , title , image , content , postedTime , priorityId ) VALUES ('2 ', N'SIGNATURE - BIỂU TƯỢNG VĂN HOÁ CÀ PHÊ ', 'news3.jpg ', N'Để giúp người dùng có thêm lựa chọn tạo ra nhiều điểm nhấn trong cuộc sống bận rộn, The Coffee House mới đây cho ra mắt Bộ đôi Cà Phê Highlight - Điểm nhấn cho ngày phấn chấn. Với sứ mệnh làm những người yêu (hoặc suýt yêu) cà phê phải… ồ lên, sản phẩm mới từ Nhà Cà Phê mang đến hương vị phá cách ai cũng nên thử. Bởi, vẫn từ những hạt Robusta, Arabica quen thuộc, barista Nhà đã khiến thức uống trở lên lạ miệng và thú vị hơn nhiều lần."', '2024-03-11 11:59:23 ', '2 ');
INSERT INTO News (userId , title , image , content , postedTime , priorityId ) VALUES ('3 ', N'CÁCH NHẬN BIẾT HƯƠNG VỊ CÀ PHÊ ', 'news4.jpg ', N'"Vừa qua, Fanpage Nhà đã hé mở những thông tin đầu tiên về ""SIGNATURE by The Coffee House"" kèm lời hẹn ""Hôm nay bạn có hẹn chưa? Mình cà phê nhé!"". ', '2024-03-12 01:41:02 ', '2 ');
INSERT INTO News (userId , title , image , content , postedTime , priorityId ) VALUES ('3 ', N'BẬT MÍ NHIỆT ĐỘ LÝ TƯỞNG ĐỂ PHA CÀ PHÊ NGON, ĐẬM ĐÀ HƯƠNG VỊ ', 'news5.jpg ', N'Theo đó, SIGNATURE by The Coffee House sẽ gửi đến các thành viên của Nhà những Cuộc hẹn tròn đầy với 3 trải nghiệm: Tách cà phê đặc sản với những mẻ rang mới mỗi ngày, chiều lòng mọi tâm hồn yêu cà phê hay những ai đang tò mò về loại trái cây đặc biệt này; Món ăn đa bản sắc, mang phong vị giao thoa từ nhiều nơi cho cuộc hẹn dài hơn, rôm rả hơn; Một không gian đầy cảm hứng, nơi hạt cà phê kể về hành trình tuyệt vời của mình theo cách gần gũi nhất. Bạn sẽ có cơ hội thưởng thức cà phê bằng đủ những giác quan - ngửi hương, lắng nghe, ngắm nhìn và nếm vị."', '2024-03-12 01:41:02 ', '3 ');
INSERT INTO News (userId , title , image , content , postedTime , priorityId ) VALUES ('3 ', N'BỘ SƯU TẬP CẦU TOÀN KÈO THƠM: "VÍA" MAY MẮN KHÔNG THỂ BỎ LỠ TẾT NÀY ', 'news6.jpg ', N'"Ở Việt Nam có rất nhiều vùng phù hợp để trồng giống Robusta, đặc biệt là Buôn Ma Thuột, Đắk Lắk, Lâm Đồng, Gia Lai, Đắk Nông,… Đây là những vùng đất mang đến hương vị cà phê Robusta nguyên chất ngon và nổi tiếng. Tuy nhiên, do sự khác biệt về thổ nhưỡng mà hương vị Robusta ở các vùng cũng có sự khác biệt tương đối. Những người sành cà phê, có gu thưởng thức tinh tế sẽ dễ dàng cảm nhận được sự khác biệt ấn tượng mà đầy thú vị này.  ', '2024-03-12 01:41:02 ', '3 ');
INSERT INTO News (userId , title , image , content , postedTime , priorityId ) VALUES ('4 ', N'LY CÀ PHÊ SỮA ĐÁ VIỆT NAM XUẤT HIỆN Ở QUẢNG TRƯỜNG THỜI ĐẠI NEW YORK ', 'news7.jpg ', N'Hương vị cà phê Robusta nguyên chất  ', '2024-03-12 01:41:02 ', '1 ');
INSERT INTO News (userId , title , image , content , postedTime , priorityId ) VALUES ('4 ', N'NGHE NHÀ MÁCH NHỎ BÍ KÍP HEALTHY GỌN NHẸ, AI CŨNG ÁP DỤNG ĐƯỢC ', 'news8.jpg ', N'Cà phê Robusta nguyên chất được lòng nhiều người bởi vị đậm đà và mùi thơm đặc trưng. Nhìn chung, Robusta nguyên chất thường có vị chát và đắng hơn nhiều so với Arabica. Một trong những nguyên nhân dẫn đến tính chất này là do Robusta thường được áp dụng phương pháp chế biến khô. ', '2024-03-12 01:41:02 ', '1 ');
INSERT INTO News (userId , title , image , content , postedTime , priorityId ) VALUES ('5 ', N'NGHỆ THUẬT THƯỞNG THỨC CÀ PHÊ PHIN ', 'news9.jpg ', N'Ngoài ra, hạt cà phê Robusta nguyên chất chứa nhiều hàm lượng Chlorogenic Acid (CGA). Tuy được gọi là Acid nhưng Chlorogenic Acid không đặc trưng bởi “vị chua” mà là “vị đắng”. Trong quá trình rang hạt cà phê, CGA sẽ phân hủy để tạo thành axit caffeic và axit quinic, những chất này khi kết hợp cùng caffeine sẽ tạo nên vị đắng đặc trưng thường thấy ở Robusta. Vậy nên dù Robusta có lượng axit gấp đôi Arabica nhưng thực sự nó không hề chua, mà đắng hơn Arabica. "', '2024-03-12 01:41:02 ', '2 ');






INSERT INTO Slider (image, [order], link, status ) VALUES ('slider1.jpg', '1', '/CoffeeLand/client/productlist', '1 ');
INSERT INTO Slider (image, [order], link, status ) VALUES ('slider2.jpg', '2', '/CoffeeLand/client/productlist', '1 ');
INSERT INTO Slider (image, [order], link, status ) VALUES ('slider3.jpg', '3', '/CoffeeLand/client/productlist', '1');



CREATE TRIGGER InsertOrUpdateCart
ON Cart
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @UserID INT;
    DECLARE @ProductID INT;
    DECLARE @SizeID INT;
    DECLARE @NewQuantity INT;
    DECLARE @NewTotalPrice DECIMAL(10, 2);
	DECLARE @DuplicateCount INT;

    SELECT @UserID = UserID, @ProductID = ProductID, @SizeID = SizeID, @NewQuantity = Quantity, @NewTotalPrice = TotalPrice
    FROM inserted;

	SELECT @DuplicateCount = COUNT(*)
    FROM Cart
    WHERE UserID = @UserID AND ProductID = @ProductID AND SizeID = @SizeID;

    IF @DuplicateCount >= 2
    BEGIN
        -- Tìm cart đã tồn tại và cập nhật quantity và total
        UPDATE Cart
        SET Quantity = Quantity + @NewQuantity,
            TotalPrice = TotalPrice + @NewTotalPrice
        WHERE UserID = @UserID AND ProductID = @ProductID AND SizeID = @SizeID;

		-- Xóa bản ghi cuối cùng nếu có trùng lặp
    DELETE FROM Cart
    WHERE CartID IN (
        SELECT CartID
        FROM (
            SELECT CartID, ROW_NUMBER() OVER (ORDER BY CartID asc) AS RowNum
            FROM Cart
            WHERE UserID = @UserID AND ProductID = @ProductID AND SizeID = @SizeID
        ) AS Temp
        WHERE RowNum > 1
    );
    END
    

    
END;


