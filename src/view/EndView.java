package view;

import java.util.List;

import model.dto.Orders;

public class EndView {
	
	public static void getOrderView(Orders order) {
		if (order != null) {
			System.out.println(order);
		} else {
			System.out.println("주문 내역이 존재하지 않습니다.");
		}
	}
	
	public static void getAllOrderView(List<Orders> orders) {
		if (!orders.isEmpty()) {
			orders.forEach(v -> System.out.println(v));
		} else {
			System.out.println("주문 리스트 조회가 불가능합니다.");
		}
	}
	
	// 예외가 아닌 단순 메세지 출력
	public static void messageView(String message) {
		System.out.println(message);
	}
	
	
}
