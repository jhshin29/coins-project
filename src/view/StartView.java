package view;

import controller.Controller;
import model.dto.Orders;

public class StartView {
	
	public static void main(String[] args) {
//		System.out.println("======== 3번 주문 조회 ========");
//		Controller.getOneOrder(3);
//		
//		System.out.println("======== 모든 주문 리스트 ==========");
//		Controller.getAllOrders();
//
//		System.out.println("======== 주문 추가 ==========");
//		Controller.addOrder("sjh0326", "Ripple", new Orders("2021-08-25", 3));
//		
//		System.out.println("======== 주문 수량 변경 ==========");
//		Controller.changeOrderQty(16, 50);
//		Controller.getOneOrder(16);
//		
//		System.out.println("======== 주문 삭제 ==========");	//예외처리 필요
//		Controller.deleteOrder(7);
//		Controller.getAllOrders();
		
//--------------------------------------------------------------------------------------		
		
//		System.out.println("***** 1. 회원가입  *****");
//		Controller.addMember("seeun999", "010-1244-5644", "세은", "19jh8");
//
//		System.out.println("***** 2. 회원 정보 수정 - 보유금액 수정  *****");
//		Controller.updateHoldMoney("sjh0326", 1214114L); //널포인터 예외처리
//		
//		System.out.println("***** 3. 회원 정보 전체 검색  *****");
//		Controller.getAllMembers();
//		
//		System.out.println("***** 4. 회원 아이디로 검색  *****");
//		Controller.getMember("sjh0326");
		
		System.out.println("***** 5. 회원 삭제  *****");
		Controller.deleteMember("pse1120");
	}
	
}