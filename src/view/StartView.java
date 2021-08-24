package view;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import controller.Controller;
import lombok.extern.slf4j.Slf4j;
import model.dao.CoinDAO;
import model.dao.MemberDAO;
import model.dto.Coin;
import model.dto.Member;
import model.dto.Orders;
import util.PublicCommon;

public class StartView {
	
	@Test
	void memberTest() {

//		System.out.println("***** 1. 회원가입  *****");
//		Member mem1 = new Member();
//		mem1.setMemberId("shinji03264");
//		mem1.setPhoneNum("010-2382-2938");
//		mem1.setRealName("신지혜");
//		mem1.setZipcode("M3M0N2");
//		mem1.setHoldMoney(127894000L);
//		
//		MemberDAO.addMember(mem1);
//
//		System.out.println("***** 2. 회원 정보 수정  *****");
//		MemberDAO.updateMember("pse1120", 6600000L);
//		
//		System.out.println("***** 3. 회원 정보 전체 검색  *****");
//		EndView.ListView(Controller.getAllMembers());
//		
//		System.out.println("***** 4. 회원 아이디로 검색  *****");
//		EndView.allView(Controller.getMember(null));
//		
//		System.out.println("***** 5. 회원 삭제  *****");
//		MemberDAO.deleteMember("shinji03264");
	}


}
