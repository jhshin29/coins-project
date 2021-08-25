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
	}
	
}
