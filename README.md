# ๐ํ๋ ์ด๋นํธ
### ์ผ๋ฐํ์ -  ์ฝ์ธ ์ข๋ชฉ์ ์กฐํ/์ฃผ๋ฌธ/๋ณ๊ฒฝ/์ทจ์ํ๊ณ ,

### ๊ด๋ฆฌ์ - ์ฝ์ธ ์ถ๊ฐ/์๋ ์์ /์ญ์  ๋ฐ ์ ์ฒด ๋ฆฌ์คํธ ์กฐํ๋ฅผ ํ  ์ ์๋ ํ๋ก๊ทธ๋จ

## ๊ตฌ์ฑ์

### ๐ง๐ป ์ ์งํ : ์ฃผ๋ฌธ ๊ด๋ จ ๊ธฐ๋ฅ ๋ด๋น

### ๐ฉ๐ป ๋ฐ์ธ์ : ํ์ ๊ด๋ จ ๊ธฐ๋ฅ, Log ๊ด๋ จ ๊ธฐ๋ฅ ๋ด๋น

### ๐ง๐ป ๋ฏผ๊ฒฝ์ค : ์ฝ์ธ ๊ด๋ จ ๊ธฐ๋ฅ ๋ด๋น

## 1. Modeling

![Untitled](https://user-images.githubusercontent.com/29134944/130906666-812c79b2-907a-440a-b67c-9e35ccf09ceb.png)

## 2. ํ์ด๋ธ ์์ฑ

## โ๏ธDDL

```sql
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
```

## โ๏ธDML

```sql
-- coin insert [์ฝ์ธ ์ ๋ณด ์ ์ฅ]
insert into COIN values('Bitcoin', 57800000, 128000);
insert into COIN values('Doge', 367, 19200);
insert into COIN values('Ripple', 1450, 39400);

-- member insert[๋ฉค๋ฒ ์ ๋ณด ์ ์ฅ]
insert into "MEMBER" values('sjh0326', 'Shin', '010-3729-2379', '04235', 1000000000);
insert into "MEMBER" values('mkj0238', 'Min', '010-1283-9234', '90144', 2000000000);
insert into "MEMBER" values('pse1120', 'Park', '010-0773-1784', '29480', 2500000000);

-- order insert[์ฃผ๋ฌธ ์ ๋ณด ์ ์ฅ]
insert into ORDERS values(order_seq_id.NEXTVAL, '2021-05-27', 2, 2900, 'Ripple', 'sjh0326');
insert into ORDERS values(order_seq_id.NEXTVAL, '2021-06-08', 1, 57800000, 'Bitcoin', 'mkj0238');
insert into ORDERS values(order_seq_id.NEXTVAL, '2021-05-27', 5, 1835, 'Doge', 'pse1120');

commit;
```

## 3. ์ฃผ์ ๊ธฐ๋ฅ

## โ๏ธ ์ฌ์ฉ์

1. ํ์๊ฐ์
2. ๋ณด์  ๊ธ์ก ๋ณ๊ฒฝ
3. ์ ์ฒด ์ฝ์ธ ๋ฆฌ์คํธ
4. ์ฝ์ธ ์ฃผ๋ฌธ
5. ์ฃผ๋ฌธ ์๋ ๋ณ๊ฒฝ
6. ์ฃผ๋ฌธ ์ทจ์
7. ๋์ ์ฃผ๋ฌธ ๋ฆฌ์คํธ ์กฐํ
8. ํ์ ID ์กฐํ
9. ํ์ ํํด

## โ๏ธ ๊ด๋ฆฌ์

1. ์ ์ฒด ํ์ ์กฐํ
2. ์ ์ฒด ์ฃผ๋ฌธ ์กฐํ
3. ์ฝ์ธ ์ถ๊ฐ
4. ์ฝ์ธ ์๋ ์์ 
5. ์ฝ์ธ ์ญ์ 

## 4. Issue

### ๐ก **ํ์์ ์ญ์ ํ  ๋ ๊ทธ ํ์์ ์ฃผ๋ฌธ๋ด์ญ๊น์ง ์ญ์ ํ  ๊ฒ์ธ์ง?**

- cascade ์ ์ฉํ์ฌ ํ์ ํํด ์ ํ์์ ์ฃผ๋ฌธ๋ด์ญ๊น์ง ์ญ์ ํ๊ธฐ๋ก ๊ฒฐ์ 

### ๐ก **Member(ํ์), Orders (์ํ ์ฃผ๋ฌธ), Coin (์ฝ์ธ ์ข๋ฅ) ๋งคํ ์ ๋ต**

1. ์ฃผ๋ฌธ ID๋ฅผ Primay Key๋ก ์ ์  ํ, ๊ฐ๊ฐ์ ํ์๊ณผ ์ฝ์ธ ID๋ฅผ Foreign Key๋ก ์ง์ 
2. @OneToMany โ @ManyToOne : ์ฆ์๋ก๋ฉ(FetchType.EAGER) ์ฌ์ฉ
    - ํน๋ณํ fetch ์ต์์ ์ฃผ์ง ์์์ ์, ์๋ ์๋ฌ ๋ฐ์ํ์ฌ EAGER ์ต์ ๋ถ์ฌ

        > failed to lazily initialize a collection, could not initialize proxy - no Session

    - ์ฐ๊ด๋ ์ํฐํฐ๋ฅผ ๋ฐ๋ก๋ฐ๋ก ์กฐํํ์ง ์๊ณ  ์กฐ์ธ์ ์ด์ฉํด ํ๋์ ์ฟผ๋ฆฌ๋ก ๋ฐ์ดํฐ๋ฅผ ๊ฐ์ ธ์ด
    - Member, Coin์ Fetch ์ต์์ Lazy๋ก ํ  ๊ฒฝ์ฐ, Orders ํฌํจํ ์ ์ฒด ์ ๋ณด ์กฐํ ๋ถ๊ฐ๋ฅ

### **๐ก Member, Coin ํ์ด๋ธ์ ์ฝ์ธ ์๋, ์ฃผ๋ฌธ ๊ธ์ก ์ ํ**

1. **Member โ ๋ณด์  ๊ธ์ก ์ ํ ๊ด๋ จ**
    - ์ฝ์ธ ์ฃผ๋ฌธ ๊ธ์ก์ ํ์์ ๋ณด์  ๊ธ์ก์ ์ด๊ณผํ์ง ์์์ผ ํจ.
    - ์ฝ์ธ ์ฃผ๋ฌธ ์๋ ๋ณ๊ฒฝ ์ ๋ํ ๊ณ์ฐ๋ ์ฃผ๋ฌธ ๊ธ์ก์ด ํ์์ ๋ณด์  ๊ธ์ก์ ์ด๊ณผํ์ง ์์์ผ ํจ.
    - ์ฝ์ธ ์ฃผ๋ฌธ ์ทจ์ ์, ์ฃผ๋ฌธ ๊ธ์ก์ ํ์์๊ฒ ํ๋ถ ๋์ด์ผ ํจ.
    - ์ฝ์ธ ์ฃผ๋ฌธ ์ด ๊ธ์ก์ ํ์์ ๋ณด์  ๊ธ์ก์ ์ด๊ณผ ํ  ์ ์์.
2. **Coin โ ์ด ์๋ ์ ํ ๊ด๋ จ**
    - ์ฝ์ธ ์ฃผ๋ฌธ ์๋์ ํด๋น ์ฝ์ธ์ ์ ์ฒด ์ด ์๋์ ์ด๊ณผํ์ง ์์์ผ ํจ. (์๋ ๋ณ๊ฒฝ ์์๋ ๋์ผ)
    - ์ฝ์ธ ์ฃผ๋ฌธ ์ทจ์ ์, ์ทจ์ ์๋์ ์ฝ์ธ ์ ์ฒด ์ด ์๋์ผ๋ก ๋ฐํ๋์ด์ผ ํจ.
    - ์ฝ์ธ ์ฃผ๋ฌธ ์๋์ ์ฝ์ธ ์ ์ฒด ์ด ์๋์ ์ด๊ณผํ  ์ ์์.
- ์์ ์ฝ๋ (OrderDAO.java โ insertOrder ๋ฉ์๋ ์ค)

    ```java
    	if ((member.getHoldMoney() - newOrder.getTotalPrice()) >= 0) {  //๋ณด์  ๊ธ์ก ์ ํ ํ์ธ
    			if ((coin.getTotalQty()-order.getOrderQty()) >= 0) {      //์ด ์๋ ์ ํ ํ์ธ
    					em.persist(newOrder);
    					member.setHoldMoney(member.getHoldMoney() - newOrder.getTotalPrice());
    					coin.setTotalQty(coin.getTotalQty()-order.getOrderQty());
    					System.out.println("์ฃผ๋ฌธ ๋ฑ๋ก ์๋ฃ");
    					log.info(memberId+" ๋์ด" +coinId+ " ๋ฅผ ๊ตฌ๋งคํ์จ์ต๋๋ค.");
    			} else {
    					System.out.println("์ฃผ๋ฌธ ์๋์ด ์ ์ฒด ์ฝ์ธ ๊ฐฏ์๋ณด๋ค ๋ง์ต๋๋ค. ์๋์ ์ค์ฌ์ฃผ์ธ์.");
    			}
    	} else {
    				System.out.println("์ฃผ๋ฌธ ๊ธ์ก์ด ํ์ฌ ๋ณด์  ๊ธ์ก์ ์ด๊ณผํฉ๋๋ค. ๋ณด์  ๊ธ์ก์ ํ์ธํด์ฃผ์ธ์.");
    	}
    ```

## 5. Code Review

1. ๊ด๋ฆฌ์ ์์ฅ์์ ๋ก๊ทธ ๊ธฐ๋ก์ ํ์ / ์ฝ์ธ / ์ฃผ๋ฌธ ๊ธฐ๋ก์ผ๋ก ๊ฐ๊ฐ์ ํ์ผ์ ์ ์ฅํ  ์ ์๋์ง?

## 6. ๊ฐ์ ํ  ๋ถ๋ถ

๐ก ํ์, ์ฝ์ธ ํ์ด๋ธ ์ ๋ณด ์ญ์  ์ Orders ํ์ด๋ธ ์ ๋ณด๊ฐ cascade๋ก ์ญ์ ๋๋๋ฐ, ํด๋น ์ ๋ณด๋ค์ ๋จ๊ธฐ๋ฉด์ ํ์,์ฝ์ธ ์ ๋ณด๋ง ์ญ์ ํ  ์ ์๋์ง์ ๋ํ ๊ณ ๋ฏผ
