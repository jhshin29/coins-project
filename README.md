# 📈플레이비트
### 일반회원 -  코인 종목을 조회/주문/변경/취소하고,

### 관리자 - 코인 추가/수량 수정/삭제 및 전체 리스트 조회를 할 수 있는 프로그램

## 구성원

- 신지혜 : 주문 관련 기능 담당
- 박세은 : 회원 관련 기능, Log 관련 기능 담당
- 민경준 : 코인 관련 기능 담당

## 1. Modeling

![Untitled](https://user-images.githubusercontent.com/29134944/130893858-18cf1b39-0f8a-4786-9533-3dc3cc7f1aee.png)


## 2. 테이블 생성

## ☑️DDL

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

## ☑️DML

```sql
-- coin insert [코인 정보 저장]
insert into COIN values('Bitcoin', 57800000, 128000);
insert into COIN values('Doge', 367, 19200);
insert into COIN values('Ripple', 1450, 39400);

-- member insert[멤버 정보 저장]
insert into "MEMBER" values('sjh0326', 'Shin', '010-3729-2379', '04235', 1000000000);
insert into "MEMBER" values('mkj0238', 'Min', '010-1283-9234', '90144', 2000000000);
insert into "MEMBER" values('pse1120', 'Park', '010-0773-1784', '29480', 2500000000);

-- order insert[주문 정보 저장]
insert into ORDERS values(order_seq_id.NEXTVAL, '2021-05-27', 2, 2900, 'Ripple', 'sjh0326');
insert into ORDERS values(order_seq_id.NEXTVAL, '2021-06-08', 1, 57800000, 'Bitcoin', 'mkj0238');
insert into ORDERS values(order_seq_id.NEXTVAL, '2021-05-27', 5, 1835, 'Doge', 'pse1120');

commit;
```

## 3. Function

## ☑️ 사용자

1. 회원가입
2. 보유 금액 변경
3. 전체 코인 리스트 검색
4. 코인 주문
5. 주문수량변경
6. 주문 취소
7. 나의 주문 조회
8. 내 정보 조회
9. 회원탈퇴

## ☑️ 관리자

1. 전체 회원 조회
2. 전체 주문 조회
3. 코인 추가
4. 코인 수량 수정
5. 코인 삭제

## 4. Issue

### 💡 **회원을 삭제할 때 그 회원의 주문내역까지 삭제할 것인지?**

- cascade 적용하여 회원 탈퇴 시 회원의 주문내역까지 삭제하기로 결정

### 💡 **Member(회원), Orders (상품 주문), Coin (코인 종류) 매핑 전략**

1. 주문 ID를 Primay Key로 선정 후, 각각의 회원과 코인 ID를 Foreign Key로 지정
2. @OneToMany → @ManyToOne : 즉시로딩(FetchType.EAGER) 사용
    - 특별한 fetch 옵션을 주지 않았을 시, 아래 에러 발생하여 EAGER 옵션 부여

        > failed to lazily initialize a collection, could not initialize proxy - no Session

    - 연관된 엔티티를 따로따로 조회하지 않고 조인을 이용해 하나의 쿼리로 데이터를 가져옴
    - Member, Coin의 Fetch 옵션을 Lazy로 할 경우, Orders 포함한 전체 정보 조회 불가능

### **💡 Member, Coin 테이블의 코인 수량, 주문 금액 제한**

1. **Member → 보유 금액 제한 관련**
    - 코인 주문 금액은 회원의 보유 금액을 초과하지 않아야 함.
    - 코인 주문 수량 변경 시 또한 계산된 주문 금액이 회원의 보유 금액을 초과하지 않아야 함.
    - 코인 주문 취소 시, 주문 금액은 회원에게 환불 되어야 함.
    - 코인 주문 총 금액은 회원의 보유 금액을 초과 할 수 없음.
2. **Coin → 총 수량 제한 관련**
    - 코인 주문 수량은 해당 코인의 전체 총 수량을 초과하지 않아야 함. (수량 변경 시에도 동일)
    - 코인 주문 취소 시, 취소 수량은 코인 전체 총 수량으로 반환되어야 함.
    - 코인 주문 수량은 코인 전체 총 수량을 초과할 수 없음.
- 예시 코드 (OrderDAO.java → insertOrder 메서드 중)

    ```java
    if ((member.getHoldMoney() - newOrder.getTotalPrice()) >= 0) {  //보유 금액 제한 확인
       if ((coin.getTotalQty()-order.getOrderQty()) >= 0) {      //총 수량 제한 확인
    	       em.persist(newOrder);
    		member.setHoldMoney(member.getHoldMoney() - newOrder.getTotalPrice());
    		coin.setTotalQty(coin.getTotalQty()-order.getOrderQty());
    		System.out.println("주문 등록 완료");
    		log.info(memberId+" 님이" +coinId+ " 를 구매하셨습니다.");
    	} else {
    		System.out.println("주문 수량이 전체 코인 갯수보다 많습니다. 수량을 줄여주세요.");
    	}
    } else {
    	System.out.println("주문 금액이 현재 보유 금액을 초과합니다. 보유 금액을 확인해주세요.");
    }
    ```

## 5. Code Review

1. 관리자 입장에서 로그 기록을 회원 / 코인 / 주문 기록으로 각각의 파일에 저장할 수 있는지?

## 6. 개선할 부분

💡 회원, 코인 테이블 정보 삭제 시 Orders 테이블 정보가 cascade로 삭제되는데, 해당 정보들을 남기면서 회원,코인 정보만 삭제할 수 없는지에 대한 고민
