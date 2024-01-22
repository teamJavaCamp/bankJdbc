DROP TABLE IF EXISTS tbl_user;
CREATE TABLE IF NOT EXISTS tbl_user
(
	id VARCHAR(20) COMMENT '아이디',
    pwd VARCHAR(20) NOT NULL COMMENT '비밀번호',
    name VARCHAR(10) NOT NULL COMMENT '이름',
    account VARCHAR(7) NOT NULL COMMENT '계좌번호',
	gender VARCHAR(1) NOT NULL COMMENT '성별',
    age INT NOT NULL COMMENT '나이',
    balance	LONG NOT NULL COMMENT '잔고',
    CONSTRAINT pk_id PRIMARY KEY(id)
) ENGINE=INNODB COMMENT '회원';

drop table if exists tbl_history;
CREATE TABLE IF NOT EXISTS tbl_history
(
	dates VARCHAR(30) NOT NULL COMMENT '날짜',
	id VARCHAR(20) COMMENT '아이디',
	transactions VARCHAR(4) NOT NULL COMMENT '타입',
    amount LONG NOT NULL COMMENT '변동금액',
    CONSTRAINT pk_date_id PRIMARY KEY(dates, id),
    CONSTRAINT fk_id FOREIGN KEY (id) REFERENCES tbl_user (id)
) ENGINE=INNODB COMMENT '내역';

DROP TABLE IF EXISTS tbl_product;
CREATE TABLE IF NOT EXISTS tbl_product 
(
	product_id INT AUTO_INCREMENT COMMENT '상품 번호',
	id VARCHAR(20) NOT NULL COMMENT '회원 아이디',
	typee INT NOT NULL COMMENT '타입',
    CHECK(typee IN ('1', '2')),
    CONSTRAINT pk_product_id PRIMARY KEY(product_id),
    CONSTRAINT fk_pId FOREIGN KEY(id) REFERENCES tbl_user (id)
) ENGINE=INNODB COMMENT '상품';

DROP TABLE IF EXISTS tbl_savings;
CREATE TABLE IF NOT EXISTS tbl_savings -- created a savings table
(
	product_id INT PRIMARY KEY COMMENT '상품 번호',
    interest_rate DOUBLE NOT NULL COMMENT '금리',
    duration INT NOT NULL COMMENT '기간',
    savings_amount LONG NOT NULL COMMENT '가입금액',
    CONSTRAINT fk_product_id_s FOREIGN KEY(product_id) REFERENCES tbl_product (product_id)
) ENGINE=INNODB COMMENT '적금';

DROP TABLES if exists tbl_loan;
CREATE TABLE IF NOT EXISTS tbl_loan
(
	product_id INT PRIMARY KEY COMMENT '상품 이름',
    interest_rate DOUBLE NOT NULL COMMENT '금리',
    duration INT NOT NULL COMMENT '기간',
    loan_amount LONG NOT NULL COMMENT '대출금액',
    CONSTRAINT fk_product_id_l FOREIGN KEY(product_id) REFERENCES tbl_product (product_id)
) ENGINE=INNODB COMMENT '대출';