package controller;

import java.io.InputStreamReader;
import java.util.Scanner;

import model.dao.CoinDAO;
import model.dao.MemberDAO;
import model.dao.OrderDAO;
import model.dto.Orders;
import view.EndView;

public class Controller {

	// 1. 1개의 주문 조회
	public static void getOneOrder(int orderId) {
		EndView.getOrderView(OrderDAO.getOrder(orderId));
	}

	public static void getIdOrders(String memberId) {
		try {
			EndView.getAllOrderView(OrderDAO.getIdOrders(memberId));
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("존재하지 않는 회원 ID입니다.");
		}
	}

	// 2. 전체 주문 리스트 조회
	public static void getAllOrders() {
		EndView.getAllOrderView(OrderDAO.getAllOrders());
	}

	// 3. 주문 등록
	public static void addOrder(String memberId, String coinId, Orders order) {
		try {
			OrderDAO.insertOrder(memberId, coinId, order);
		} catch (NullPointerException e) {
			e.printStackTrace();
			EndView.messageView("주문에 실패하였습니다.");
		}

	}

	// 4. 주문 수량 변경 (update)
	public static void changeOrderQty(int orderId, int orderQty) {
		try {
			OrderDAO.updateOrder(orderId, orderQty);
			EndView.getOrderView(OrderDAO.getOrder(orderId));
		} catch (NullPointerException e) {
			e.printStackTrace();
			EndView.messageView("수량 변경에 실패하였습니다.");
		}

	}

	// 5. 주문 삭제
	public static void deleteOrder(int orderId) {
		try {
			OrderDAO.deleteOrder(orderId);
			EndView.messageView("주문 삭제 완료");
		} catch (NullPointerException e) {
			e.printStackTrace();
			EndView.messageView("없는 주문번호입니다. 다시 확인해주세요.");
		}
	}

//---------------------------------------------------------------------------		
	// 1. 회원가입
	public static void addMember(String memberId, String phoneNum, String realName, String zipcode) {
		try {
			if (MemberDAO.addMember(memberId, phoneNum, realName, zipcode) == true)
				EndView.messageView("회원가입 완료");
			else
				EndView.messageView("존재하는 아이디입니다.");
		} catch (Exception e) {
			e.printStackTrace();
			EndView.messageView("회원가입에 실패하였습니다.");
		}
	}

	// 2. 회원정보수정 - 보유금액수정(입금)
	public static void updateHoldMoney(String memberId, Long holdMoney) {
		try {
			if (MemberDAO.updateHoldMoney(memberId, holdMoney) == true) {
				EndView.messageView("보유금액 변경 완료");
				getMember(memberId);
			} else
				EndView.messageView("일치하는 회원아이디가 없습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			EndView.messageView("금액 변경에 실패하였습니다.");
		}
	}

	// 3. 회원 전체 조회
	public static void getAllMembers() {
		try {
			EndView.getAllMemberView(MemberDAO.getAllMembers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 4. 회원 아이디로 단일 조회
	public static void getMember(String memberId) {
		try {
			EndView.getMemberView(MemberDAO.getMember(memberId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 5. 회원 삭제
	public static void deleteMember(String memberId) {
		try {
			if (MemberDAO.deleteMember(memberId) == true)
				EndView.messageView("회원 삭제 완료");
			else
				EndView.messageView("일치하는 회원아이디가 없습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			EndView.messageView("회원삭제에 실패하였습니다.");
		}
	}

	// ---------------------------------------------------------------------------

	// 1. 1개의 코인 조회
	public static void getOneCoin(String coinId) {
		try {
			EndView.getCoinView(CoinDAO.getCoin(coinId));
		} catch (Exception e) {
			e.printStackTrace();
			EndView.messageView("코인이 존재하지 않습니다");
		}
	}

	// 2. 전체 코인 리스트 조회
	public static void getAllCoins() {
		try {
			EndView.getAllCoinView(CoinDAO.getAllCoins());			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	// 3. 코인 가격 변경
	public static void changeCoinPrice(String coinId, Long coinPrice) {
		try {
			if (CoinDAO.updateCoin(coinId, coinPrice) == true) {
				EndView.messageView("코인 가격 변경 조회를 합니다.");
				getOneCoin(coinId);}
			else {
				EndView.messageView("일치하는 코인 아디가 존재하지 않습니다.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			EndView.messageView("코인 삭제에 실패하였습니다.");
		}
	}

	// 4. 코인 추가
	public static void addCoin(String coinId, Long coinPrice, Long totalQty) {
		try {
			if(CoinDAO.addCoin(coinId, coinPrice, totalQty) == true){
				EndView.messageView("코인기입을 합니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
			EndView.messageView("코인 추가에 실패하였습니다");
		}
	}

	// 5. 코인 삭제
	public static void deleteCoin(String coinId) {
		try {
			if (CoinDAO.deleteCoin(coinId) == true) {
				EndView.messageView("해당 코인 삭제 완료");
			}else {
				EndView.messageView("코인 삭제 완료");				
			}
		} catch (Exception e) {
			e.printStackTrace();
			EndView.messageView("코인삭제에 실패하였습니다");
		}
	}

	// ---------------------------------------------------------------------------

	// 콘솔 메뉴 선택
	public static void getInputConsole() {
		System.out.println("***** 안녕하세요! 플레이코인입니다. *****");
		int choice = -1;
		Scanner sc = new Scanner(new InputStreamReader(System.in));

		while (true) {
			System.out.println("\n0. 프로그램 종료\r\n" + "\r\n" + "------ 사용자 -------\r\n" + "\r\n"
					+ "1. 회원가입	2. 보유 금액 변경		3. 전체 코인 리스트\r\n" + "4. 코인 주문	5. 주문 수량 변경		6. 주문 취소\r\n"
					+ "7. 나의 주문 조회	8. 회원 ID 조회		9. 회원 탈퇴\r\n" + "\r\n" + "------ 관리자 -------\r\n"
					+ "10. 전체 회원 조회	11. 전체 주문 조회		12. 코인 추가\r\n" + "13. 코인 수량 수정	14. 코인 삭제");
			System.out.println("메뉴를 선택해주세요.");

			try {
				choice = Integer.parseInt(sc.next());
			} catch (NumberFormatException e) {
				System.out.println("위 메뉴의 숫자를 입력해주세요.");
			}

			if (choice == 0) {
				System.out.println("다음에 또 만나요!");
				break;
			} else if (choice == 1) {
				System.out.println("가입하실 ID를 입력해주세요.");
				String memberId = sc.next();
				System.out.println("전화번호를 입력해주세요. ex) 010-1111-1111");
				String phoneNum = sc.next();
				System.out.println("성함을 입력해주세요");
				String realName = sc.next();
				System.out.println("우편번호를 입력해주세요");
				String zipcode = sc.next();

				addMember(memberId, phoneNum, realName, zipcode);

			} else if (choice == 2) {
				try {
					System.out.println("회원 ID를 입력해주세요.");
					String memberId = sc.next();
					System.out.println("변경하실 보유 금액을 입력해주세요.");
					Long holdMoney = Long.parseLong(sc.next());
					updateHoldMoney(memberId, holdMoney);
				} catch (NumberFormatException e) {
					System.out.println("잘못입력하셨습니다. 금액을 숫자로 입력해주세요");
				}

			} else if (choice == 3) {
				System.out.println("====== 플레이코인 거래 가능 코인 리스트 ======");
				getAllCoins();

			} else if (choice == 4) {
				System.out.println("회원 ID를 입력해주세요.");
				String memberId = sc.next();
				System.out.println("주문하실 코인이름을 입력해주세요. ex) Bitcoin, Ripple, ...");
				getAllCoins();
				String coinId = sc.next();
				System.out.println("주문 날짜를 입력해주세요. ex) 2021-08-25");
				String orderDate = sc.next();

				try {
					System.out.println("주문 수량을 숫자로 입력해주세요. ex) 10");
					int orderQty = Integer.parseInt(sc.next());

					addOrder(memberId, coinId, new Orders(orderDate, orderQty));
				} catch (NumberFormatException e) {
					System.out.println("주문 수량을 정수로 입력해주세요.");
				}

			} else if (choice == 5) {

				try {
					System.out.println("주문 번호를 숫자로 입력해주세요.");
					int orderId = Integer.parseInt(sc.next());
					System.out.println("변경할 수량을 숫자로 입력해주세요.");
					int orderQty = Integer.parseInt(sc.next());
					changeOrderQty(orderId, orderQty);
				} catch (NumberFormatException e) {
					System.out.println("번호 or 수량을 숫자로 입력해주세요.");
				}

			} else if (choice == 6) {

				try {
					System.out.println("취소할 주문 번호를 숫자로 입력해주세요.");
					int orderId = Integer.parseInt(sc.next());
					deleteOrder(orderId);
				} catch (NumberFormatException e) {
					System.out.println("취소 주문 번호를 숫자로 입력해주세요.");
				}

			} else if (choice == 7) {

				try {
					System.out.println("회원 ID를 입력해주세요.");
					String memberId = sc.next();
					getIdOrders(memberId);
				} catch (NullPointerException e) {
					System.out.println("정확한 회원 ID를 입력해주세요.");
				}

			} else if (choice == 8) {
				System.out.println("조회할 회원 ID를 입력해주세요.");
				String memberId = sc.next();

				getMember(memberId);

			} else if (choice == 9) {
				System.out.println("삭제할 회원 ID를 입력해주세요.");
				String memberId = sc.next();

				deleteMember(memberId);

			} else if (choice == 10) {
				System.out.println("====== 전체 회원 리스트 ======");
				getAllMembers();

			} else if (choice == 11) {
				System.out.println("====== 전체 주문 리스트 ======");
				try {
					getAllOrders();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}

			} else if (choice == 12) {
				System.out.println("등록할 코인 이름을 입력해주세요.");
				String coinId = sc.next();
				System.out.println("코인 가격을 입력해주세요.");
				Long coinPrice = Long.parseLong(sc.next());
				System.out.println("코인 총 수량을 입력해주세요.");
				Long totalQty = Long.parseLong(sc.next());

				addCoin(coinId, coinPrice, totalQty);

			} else if (choice == 13) {
				System.out.println("가격을 수정할 코인 이름을 입력해주세요.");
				String coinId = sc.next();
				System.out.println("변경 가격을 숫자로 입력해주세요.");
				Long coinPrice = Long.parseLong(sc.next());

				changeCoinPrice(coinId, coinPrice);

			} else if (choice == 14) {
				System.out.println("삭제할 코인 이름을 입력해주세요.");
				String coinId = sc.next();

				deleteCoin(coinId);

			}
		}

		sc.close();
		sc = null;
	}
}
