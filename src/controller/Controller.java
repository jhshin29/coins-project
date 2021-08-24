package controller;

import model.dao.OrderDAO;
import model.dto.Orders;
import view.EndView;

public class Controller {

	//1개의 주문 조회
	public static void getOneOrder(int orderId) {
		EndView.getOrderView(OrderDAO.getOrder(orderId));
	}
	
	//전체 주문 리스트 조회
	public static void getAllOrders() {
		EndView.getAllOrderView(OrderDAO.getAllOrders());
	}
	
	//주문 등록
	public static void addOrder(String memberId, String coinId, Orders order) {
		try {
			OrderDAO.insertOrder(memberId, coinId, order);
			EndView.messageView("주문 등록 완료");
		} catch (NullPointerException e) {
			e.printStackTrace();
			EndView.messageView("주문에 실패하였습니다.");
		}
		
	}

	//주문 수량 변경 (update)
	public static void changeOrderQty(int orderId, int orderQty) {
		try {
			OrderDAO.updateOrder(orderId, orderQty);
			EndView.messageView("수량 변경 완료");
		} catch (NullPointerException e) {
			e.printStackTrace();
			EndView.messageView("수량 변경에 실패하였습니다.");
		}
		
	}

	//주문 삭제
	public static void deleteOrder(int orderId) {
		try {
			OrderDAO.deleteOrder(orderId);
			EndView.messageView("주문 삭제 완료");
		} catch (NullPointerException e) {
			EndView.messageView("없는 주문번호입니다. 다시 확인해주세요.");
		} 
	}
}
