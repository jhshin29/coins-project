package view;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import model.dto.Coin;
import model.dto.Member;
import model.dto.Orders;
import util.PublicCommon;

public class StartView {

//	@Test
	void m1() {
		EntityManager em = PublicCommon.getEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			Coin bitcoin = new Coin();
			bitcoin.setCoinId("bitcoin");
			bitcoin.setCoinPrice(2910L);
			bitcoin.setTotalQty(129432L);
			
			Member mem1 = new Member();
			mem1.setMemberId("shinji03264");
			mem1.setPhoneNum("010-2382-2938");
			mem1.setRealName("신지혜");
			mem1.setZipcode("M3M0N2");
			mem1.setHoldMoney(127894000L);
			
			Orders o1 = new Orders();
			o1.setOrderDate("2021-09-21");
			o1.setOrderQty(12);
			o1.setTotalPrice(394980);
			o1.setCoinId(bitcoin);
			o1.setMemberId(mem1);
						
			bitcoin.getOrders().add(o1);
			mem1.getOrders().add(o1);
			
			em.persist(bitcoin);
			em.persist(mem1);
			em.persist(o1);
			
			System.out.println(bitcoin);
			System.out.println(mem1);
			System.out.println(o1);
			Orders find = em.find(Orders.class, 1);
			System.out.println(find);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}
}
