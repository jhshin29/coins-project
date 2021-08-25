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
