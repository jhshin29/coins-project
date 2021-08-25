package view;

import controller.Controller;
import model.dto.Orders;

public class StartView {
	
	public static void main(String[] args) {
		System.out.println("======== 3번 주문 조회 ========");
		Controller.getOneOrder(3);
		
		System.out.println("======== 모든 주문 리스트 ==========");
		Controller.getAllOrders();

		System.out.println("======== 주문 추가 ==========");
		Controller.addOrder("sjh0326", "Doge", new Orders("2021-08-25", 5));
		
		System.out.println("======== 주문 수량 변경 ==========");
		Controller.changeOrderQty(22, 5);
		Controller.getOneOrder(22);
		
		System.out.println("======== 주문 삭제 ==========");
		Controller.deleteOrder(22);
		Controller.getAllOrders();
	
		
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
//		
//		System.out.println("***** 5. 회원 삭제  *****");
//		Controller.deleteMember("pse1120");
		
//		System.out.println("======== 존재하지 않는 코인 조회 =======");
//		Controller.getOneCoin("123");
//		
//		System.out.println("========= 존재하는 코인 조회 ==========");
//		Controller.getOneCoin("Bitcoin");
//		
//		System.out.println("======= 모든 코인 리스트");
//		Controller.getAllCoins();
//		
//		System.out.println("=============코인 추가=======");
//		Controller.deleteCoin("Bitcoin");
//		
//		System.out.println("=========삭제 후 코인 조회======");
//		Controller.getAllCoins();
//		
//		System.out.println("========코인 가격 업데이트 ======");
//		Controller.changeCoinPrice("Doge", 234656L);
//		Controller.getAllCoins();
//		
//		System.out.println("=========코인  종류 추가 ========");
//		Controller.addCoin("Amugae", 12346123L, 439816L);
//		Controller.getAllCoins();
	}
}	
