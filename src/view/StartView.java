package view;

import org.junit.jupiter.api.Test;

import controller.Controller;

import model.dto.Orders;

public class StartView {
	
	@Test
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
	
}
