package view;

import java.util.List;

import model.dto.Coin;
import model.dto.Member;
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
	
	public static void getMemberView(Member member) {
		if (member != null) {
			System.out.println(member);
		} else {
			System.out.println("회원이 존재하지 않습니다.");
		}
	}
	
	public static void getAllMemberView(List<Member> member) {
		if (!member.isEmpty()) {
			member.forEach(v -> System.out.println(v));
		} else {
			System.out.println("전체 회원 조회가 불가능합니다.");
		}
	}
	
	public static void getCoinView(Coin coin) {
		if (coin != null) {
			System.out.println(coin);
		}else {
			System.out.println("코인 내역이 존재하지 않습니다.");
		}
	}
	
	public static void getAllCoinView(List<Coin> coins) {
		if (!coins.isEmpty()) {
			coins.forEach(v -> System.out.println(v));
		}else {
			System.out.println("코인 리스트 조회가 불가능합니다.");
		}
	}
	
	public static void messageView(String message) {
		System.out.println(message);
	}
}