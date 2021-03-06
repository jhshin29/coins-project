package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import lombok.extern.slf4j.Slf4j;
import model.dto.Coin;
import model.dto.Member;
import model.dto.Orders;
import util.PublicCommon;

@Slf4j
public class OrderDAO {

	public static Orders getOrder(int orderId) {

		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Orders order = null;

		try {
			tx.begin();
			order = em.find(Orders.class, orderId);
			tx.commit();
			
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}

		return order;
	}
	
	public static List<Orders> getIdOrders(String memberId) {
		EntityManager em = PublicCommon.getEntityManager();
		List<Orders> orders = null;
		Member member = em.find(Member.class, memberId);
		
		try {
			orders = member.getOrders();
		} catch (RuntimeException e) {
		} finally {
			em.close();
			em = null;
		}
		
		return orders;
	}

	public static List<Orders> getAllOrders() {
		EntityManager em = PublicCommon.getEntityManager();
		List<Orders> orders = null;

		try {
			orders = em.createQuery("SELECT E FROM Orders E ORDER BY E.orderId").getResultList();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}

		return orders;
	}

	public static void insertOrder(String memberId, String coinId, Orders order) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Orders newOrder = order;
		Member member = em.find(Member.class, memberId);
		Coin coin = em.find(Coin.class, coinId);

		try {
			tx.begin();

			newOrder.setMemberId(member);
			newOrder.setCoinId(coin);
			newOrder.setTotalPrice(coin.getCoinPrice() * order.getOrderQty());
			
			if ((member.getHoldMoney() - newOrder.getTotalPrice()) >= 0) {
				if ((coin.getTotalQty()-order.getOrderQty()) >= 0) {
					em.persist(newOrder);
					member.setHoldMoney(member.getHoldMoney() - newOrder.getTotalPrice());
					coin.setTotalQty(coin.getTotalQty()-order.getOrderQty());
					System.out.println("?????? ?????? ??????");
					log.info(memberId+" ??????" +coinId+ " ??? ?????????????????????.");
				} else {
					System.out.println("?????? ????????? ?????? ?????? ???????????? ????????????. ????????? ???????????????.");
				}
			} else {
				System.out.println("?????? ????????? ?????? ?????? ????????? ???????????????. ?????? ????????? ??????????????????.");
			}
			
			
			tx.commit();
			
		} catch (RuntimeException e) {
			tx.rollback();
			System.out.println("?????? ????????? ?????? ??????????????????.");
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}

	public static void updateOrder(int orderId, int orderQty) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Orders findOrder = em.find(Orders.class, orderId);
		Member member = findOrder.getMemberId();
		Coin coin = findOrder.getCoinId();

		try {
			tx.begin();

			member.setHoldMoney(member.getHoldMoney() + findOrder.getTotalPrice());
			coin.setTotalQty(coin.getTotalQty() + findOrder.getOrderQty());
			findOrder.setOrderQty(orderQty);
			findOrder.setTotalPrice(findOrder.getOrderQty() * findOrder.getCoinId().getCoinPrice());
			
			if ((member.getHoldMoney() - findOrder.getTotalPrice()) >= 0) {
				if ((coin.getTotalQty() - findOrder.getOrderQty()) >= 0) {
					member.setHoldMoney(member.getHoldMoney() - findOrder.getTotalPrice());
					coin.setTotalQty(coin.getTotalQty() - findOrder.getOrderQty());
					System.out.println("?????? ?????? ??????");
					
					tx.commit();
				} else {
					System.out.println("?????? ????????? ?????? ?????? ???????????? ????????????. ????????? ???????????????.");
				}
			} else {
				System.out.println("????????? ?????? ????????? ?????? ?????? ????????? ???????????????. ?????? ????????? ??????????????????.");
			}
			
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}

	public static void deleteOrder(int orderId) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Orders findOrder = em.find(Orders.class, orderId);
		Member member = findOrder.getMemberId();
		Coin coin = findOrder.getCoinId();
		
		try {
			tx.begin();

			member.setHoldMoney(member.getHoldMoney() + findOrder.getTotalPrice());
			coin.setTotalQty(coin.getTotalQty() + findOrder.getOrderQty());
			
			em.remove(findOrder);

			log.info(orderId +" ??? ????????? ?????????????????????.");
			tx.commit();
			
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}

}