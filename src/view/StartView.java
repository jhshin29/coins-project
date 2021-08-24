package view;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import model.dao.MemberDAO;
import model.dto.Coin;
import model.dto.Member;
import model.dto.Orders;
import util.PublicCommon;

public class StartView {
	@Test
	void m1() {

//		System.out.println("***** 1. 회원가입  *****");
//		Member mem1 = new Member();
//		mem1.setMemberId("shinji03264");
//		mem1.setPhoneNum("010-2382-2938");
//		mem1.setRealName("신지혜");
//		mem1.setZipcode("M3M0N2");
//		mem1.setHoldMoney(127894000L);
//		
//		MemberDAO.addMember(mem1);

		//		System.out.println("***** 2. 회원정보수정  *****");
//		MemberDAO.updateMember("pse1120", 6600000L);
		
//		System.out.println("***** 3. 회원정보전체확인  *****");
//		System.out.println(MemberDAO.getAllMember());
//		
//		System.out.println("***** 4. 회원정보 1명 확인  *****");
//		System.out.println(MemberDAO.getMember("mkj0238"));
//		
		System.out.println("***** 5. 회원삭제  *****");
		MemberDAO.deleteMember("shinji03264");
	}

//	@Test
//	void createTable() {
//		EntityManager em = PublicCommon.getEntityManager();
//		
//		EntityTransaction tx = em.getTransaction();
//		
//		tx.begin();
//		
//		try {
//			Coin bitcoin = new Coin();
//			bitcoin.setCoinId("bitcoin");
//			bitcoin.setCoinPrice(2910L);
//			bitcoin.setTotalQty(129432L);
//			
//			Member mem1 = new Member();
//			mem1.setMemberId("shinji03264");
//			mem1.setPhoneNum("010-2382-2938");
//			mem1.setRealName("신지혜");
//			mem1.setZipcode("M3M0N2");
//			mem1.setHoldMoney(127894000L);
//			
//			Member mem2 = new Member();
//			mem2.setMemberId("seeun222");
//			mem2.setPhoneNum("010-2124-2938");
//			mem2.setRealName("박세은");
//			mem2.setZipcode("D5J9N4");
//			mem2.setHoldMoney(1341414L);
//			
//			Orders o1 = new Orders();
//			o1.setOrderDate("2021-09-21");
//			o1.setOrderQty(12);
//			o1.setTotalPrice(394980);
//			o1.setCoinId(bitcoin);
//			o1.setMemberId(mem1);
//			
//			Orders o2 = new Orders();
//			o2.setOrderDate("2021-08-21");
//			o2.setOrderQty(32);
//			o2.setTotalPrice(1230);
//			o2.setCoinId(bitcoin);
//			o2.setMemberId(mem2);
//						
//			bitcoin.getOrders().add(o1);
//			bitcoin.getOrders().add(o2);
//			mem1.getOrders().add(o1);
//			mem2.getOrders().add(o2);
//
//			
//			em.persist(bitcoin);
//			em.persist(mem1);
//			em.persist(mem2);
//			em.persist(o1);
//			em.persist(o2);
//
//			tx.commit();
//		} catch (Exception e) {
//			tx.rollback();
//			e.printStackTrace();
//		} finally {
//			em.close();
//			em = null;
//		}
//	}
}
