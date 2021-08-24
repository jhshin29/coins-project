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

	//리스트 출력(전체 정보 검색)
		public static void ListView(List list) {
			int length = list.size();
			if( length != 0 ){
				for(int index = 0; index < length; index++){			
					System.out.println("검색정보 " + (index+1) + " - " + list.get(index));
				}
			}
		}

		public static void allView(Object object) {
			if(object == null) {
				System.out.println("해당 정보가 없습니다.");
				return;
			}
			System.out.println(object);
		}

	
}
