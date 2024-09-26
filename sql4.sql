use TestDB;

CREATE TABLE Lam_viec(
	id bigint NOT NULL PRIMARY KEY auto_increment,
	Id_nhan_vien bigint NOT NULL,
	Id_bo_phan bigint NOT NULL
);

CREATE TABLE Nhan_vien(
	id bigint NOT NULL PRIMARY KEY auto_increment,
	Ten VARCHAR(150) NOT NULL,
	Tuoi Integer,
    Muc_luong double
);

CREATE TABLE Bo_phan(
	id bigint NOT NULL PRIMARY KEY auto_increment,
	Bo_phan VARCHAR(150) NOT NULL,
    UNIQUE (Bo_phan)
);

 ALTER TABLE Lam_viec
 ADD CONSTRAINT fk_Id FOREIGN KEY (Id_bo_phan)
 REFERENCES Nhan_Vien(id);
 
  ALTER TABLE Lam_viec
 ADD CONSTRAINT fk_Id1 FOREIGN KEY (Id_nhan_vien)
 REFERENCES Nhan_Vien(id);
 
 
select Ten, Max(Muc_luong), Bo_phan From Nhan_vien, Bo_Phan, Lam_viec 
where  Nhan_vien.id = Lam_viec.Id_nhan_vien 
AND Bo_phan.id = Lam_viec.Id_bo_phan 
group by Bo_phan;

use mvcdemo;

CREATE TABLE IF NOT EXISTS `register` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `sex` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;



INSERT INTO `register` (`id`, `name`, `password`, `email`, `sex`, `country`) VALUES
(17, 'arun', 'kumar', 'arun@gmail.com', 'Male', 'India'),
(19, 'sonoo', 'jaiswal', 'sonoo@javatpoint.com', 'male', 'India'),
(20, 'Ashok', 'ashok', 'ashok@javatpoint.com', 'male', 'India');


use test;

/*
 Navicat Premium Data Transfer

 Source Server         : MySQL8.4
 Source Server Type    : MySQL
 Source Server Version : 80402 (8.4.2)
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80402 (8.4.2)
 File Encoding         : 65001

 Date: 24/09/2024 14:00:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int NOT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Fruits');
INSERT INTO `category` VALUES (2, 'Vegetable');
INSERT INTO `category` VALUES (3, 'Biscuit');
INSERT INTO `category` VALUES (4, 'Cheese');
INSERT INTO `category` VALUES (5, 'Juice');
INSERT INTO `category` VALUES (6, 'Milk');
INSERT INTO `category` VALUES (7, 'Chocolate');
INSERT INTO `category` VALUES (8, 'Yoghurt');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int NOT NULL,
  `cc_expiry_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cc_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cc_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `contact_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `delivery_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` int NOT NULL,
  `order_id` int NOT NULL,
  `price` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int NOT NULL,
  `category_id` int NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 7, 'Mint Chocolate 250g', 'Timtim', 12.45);
INSERT INTO `product` VALUES (2, 7, 'Chocolate with peanut butter 240g', 'Borneo Bonbon', 15.95);
INSERT INTO `product` VALUES (3, 6, 'Full cream 200g', 'Dairy Milk', 8.25);

-- ----------------------------
-- Table structure for shopping_item
-- ----------------------------
DROP TABLE IF EXISTS `shopping_item`;
CREATE TABLE `shopping_item`  (
  `product_id` int NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_item
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;


use test1;

INSERT INTO categories (category_id, name, description) VALUES
(1, 'Fiction', 'Books that contain invented and imaginative writing'),
(2, 'Non-Fiction', 'Books based on real facts and information'),
(3, 'Science Fiction', 'Books about futuristic science, technology, and space'),
(4, 'Fantasy', 'Books featuring magical elements and imaginary worlds'),
(5, 'Mystery', 'Books centered around solving a crime or uncovering secrets'),
(6, 'Biography', 'Books detailing the life story of a real person'),
(7, 'Self-help', 'Books intended to guide readers on personal improvement'),
(8, 'History', 'Books exploring historical events and figures'),
(9, 'Romance', 'Books that focus on love and relationships'),
(10, 'Thriller', 'Books designed to create intense excitement and suspense'),
(11, 'Horror', 'Books meant to evoke fear, dread, or shock'),
(12, 'Poetry', 'Books containing collections of poems');


INSERT INTO books (book_id, author, description, image_url, price, publisher, stock, title, year, category_id) VALUES
(1, 'George Orwell', 'A dystopian social science fiction novel and cautionary tale', 'url_to_image_1984.jpg', 15.99, 'Secker & Warburg', 120, '1984', 1949, 1),
(2, 'J.K. Rowling', 'A magical journey of a young wizard', 'url_to_image_hp.jpg', 29.99, 'Bloomsbury', 200, 'Harry Potter and the Philosopher''s Stone', 1997, 4),
(3, 'Yuval Noah Harari', 'A groundbreaking look at the history of humankind', 'url_to_image_sapiens.jpg', 19.99, 'Harper', 85, 'Sapiens: A Brief History of Humankind', 2011, 2),
(4, 'Isaac Asimov', 'A classic science fiction about robots and ethics', 'url_to_image_robot.jpg', 10.99, 'Gnome Press', 55, 'I, Robot', 1950, 3),
(5, 'Agatha Christie', 'A famous detective story featuring Hercule Poirot', 'url_to_image_murder.jpg', 12.50, 'Collins Crime Club', 60, 'Murder on the Orient Express', 1934, 5),
(6, 'Michelle Obama', 'An inspiring autobiography of the former First Lady', 'url_to_image_becoming.jpg', 22.00, 'Crown Publishing Group', 150, 'Becoming', 2018, 6),
(7, 'Dale Carnegie', 'A guide to improving interpersonal skills and communication', 'url_to_image_howtowin.jpg', 14.99, 'Simon & Schuster', 80, 'How to Win Friends and Influence People', 1936, 7),
(8, 'Stephen King', 'A terrifying tale of a haunted hotel', 'url_to_image_shining.jpg', 18.99, 'Doubleday', 90, 'The Shining', 1977, 11),
(9, 'William Shakespeare', 'A classic romantic tragedy', 'url_to_image_romeo.jpg', 9.99, 'Thomas Creede', 75, 'Romeo and Juliet', 1597, 9),
(10, 'Dan Brown', 'A thrilling story involving art, history, and secret codes', 'url_to_image_da_vinci_code.jpg', 16.99, 'Doubleday', 95, 'The Da Vinci Code', 2003, 10),
(11, 'J.R.R. Tolkien', 'An epic fantasy adventure set in Middle-earth', 'url_to_image_lotr.jpg', 25.99, 'Allen & Unwin', 170, 'The Lord of the Rings', 1954, 4),
(12, 'Homer', 'An ancient epic poem about the adventures of Odysseus', 'url_to_image_odyssey.jpg', 13.50, 'Penguin Classics', 40, 'The Odyssey', -800, 12);

INSERT INTO users (address, password, phone_number, username)
VALUES ('123 Main St, Anytown, CA', 'pass123', '1234567890', 'john.doe'),
       ('456 Elm St, Yourtown, NY', 'pass456', '0987654321', 'jane.smith');
