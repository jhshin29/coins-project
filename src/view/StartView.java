package view;

import controller.Controller;
import model.dao.MemberDAO;
import model.dto.Orders;

public class StartView {

	public static void main(String[] args) {
		System.out.println("======== 3번 주문 조회 ========");
		Controller.getOneOrder(3);
		
		System.out.println("======== 모든 주문 리스트 ==========");
		Controller.getAllOrders();

		System.out.println("======== 주문 추가 ==========");
		Controller.addOrder("sjh0326", "Ripple", new Orders("2021-08-25", 3));
		
		System.out.println("======== 주문 수량 변경 ==========");
		Controller.changeOrderQty(16, 50);
		Controller.getOneOrder(16);
		
		System.out.println("======== 주문 삭제 ==========");	//예외처리 필요
		Controller.deleteOrder(7);
		Controller.getAllOrders();
	}
	
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

}
