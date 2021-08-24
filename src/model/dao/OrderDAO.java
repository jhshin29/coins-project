package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.dto.Coin;
import model.dto.Member;
import model.dto.Orders;
import util.PublicCommon;

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

	public static List<Orders> getAllOrders() {
		EntityManager em = PublicCommon.getEntityManager();
		List<Orders> orders = null;

		try {
			orders = em.createQuery("SELECT E FROM Orders E").getResultList();
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
			
			em.persist(newOrder);
			
			member.setHoldMoney(member.getHoldMoney()-newOrder.getTotalPrice());

			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
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

		try {
			tx.begin();

			findOrder.setOrderQty(orderQty);
			member.setHoldMoney(member.getHoldMoney()+findOrder.getTotalPrice());
			findOrder.setTotalPrice(findOrder.getOrderQty()*findOrder.getCoinId().getCoinPrice());
			member.setHoldMoney(member.getHoldMoney()-findOrder.getTotalPrice());
			
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}

	public static void deleteOrder(int orderId){
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Orders findOrder = em.find(Orders.class, orderId);
		try {
			tx.begin();

			em.remove(findOrder);

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
