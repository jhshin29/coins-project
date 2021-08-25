package controller;

import java.util.List;

import model.dao.MemberDAO;
import model.dao.OrderDAO;
import model.dto.Member;
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

//---------------------------------------------------------------------------		
		//1. 회원가입
		public static void addMember(String memberId, String phoneNum, String realName, String zipcode) {
			try {
				MemberDAO.addMember(memberId, phoneNum, realName, zipcode);
				EndView.messageView("회원가입 완료");
			} catch (NullPointerException e) {
				e.printStackTrace();
				EndView.messageView("회원가입에 실패하였습니다.");
			}
			
		}

		//. 회원정보수정 - 보유금액수정(입금)
		public static void updateHoldMoney(String memberId, Long holdMoney) {
			try {
				MemberDAO.updateHoldMoney(memberId, holdMoney);
				EndView.messageView("보유금액 변경 완료");
			} catch (NullPointerException e) {
				e.printStackTrace();
				EndView.messageView("금액 변경에 실패하였습니다.");
			}
		}

	
		//3. 회원 전체 조회
		public static void getAllMembers() {
			try {
				EndView.getAllMemberView(MemberDAO.getAllMembers());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//4. 회원 아이디로 단일 조회
		public static void getMember(String memberId) {
			try {
				EndView.getMemberView(MemberDAO.getMember(memberId));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//5. 회원 삭제
		public static void deleteMember(String memberId) {
			try {
				MemberDAO.deleteMember(memberId);
				EndView.messageView("회원 삭제 완료");
			} catch (Exception e) {
				e.printStackTrace();
				EndView.messageView("회원아이디가 존재하지 않습니다. 다시 확인해주세요.");
			}
		}
}
