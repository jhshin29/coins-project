DROP TABLE member cascade constraint;
DROP TABLE coin cascade constraint;
DROP TABLE orders cascade constraint;

DROP SEQUENCE order_seq_id;

CREATE TABLE member (
       member_id          	VARCHAR2(50) PRIMARY KEY,
       real_name            VARCHAR2(50),
       phone_num    		VARCHAR2(50),
       zipcode              VARCHAR2(50),
       hold_money			NUMBER(19,0)
);

CREATE TABLE coin (
       coin_id        		VARCHAR2(50) PRIMARY KEY,
       coin_price           NUMBER(19,0),
       total_qty          	NUMBER(19,0)
);


CREATE TABLE orders (
       order_id       		NUMBER PRIMARY KEY,
       order_date     		VARCHAR2(50),
       order_qty  			NUMBER,
       total_price			NUMBER(19,0),
       coin_id				VARCHAR2(50),
       member_id			VARCHAR2(50)       
);

ALTER TABLE orders ADD FOREIGN KEY (coin_id) REFERENCES coin (coin_id) ON DELETE CASCADE;
ALTER TABLE orders ADD FOREIGN KEY (member_id) REFERENCES member (member_id) ON DELETE CASCADE ;

CREATE SEQUENCE order_seq_id START WITH 1 INCREMENT BY 1 MAXVALUE 10000000 CYCLE NOCACHE;