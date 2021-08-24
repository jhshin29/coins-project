package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import model.dto.Orders;
import util.PublicCommon;

public class OrderDAO {
	
	private static Orders instance = new Orders();
	
	public static Orders getInstance() {
		return instance;
	}
	
	@Test
	public void m1() {
		
		Orders newOrder = new Orders();
		newOrder.setOrderDate("2021-09-23");
		newOrder.setOrderQty(80);
		newOrder.setTotalPrice(1200000);
		
		Orders newOrder2 = new Orders();
		newOrder2.setOrderDate("2021-09-24");
		newOrder2.setOrderQty(20);
		newOrder2.setTotalPrice(200000);
		
		insertOrder(newOrder);
		insertOrder(newOrder2);
		
		System.out.println(getOrder(1));
		System.out.println(getAllOrders());
		
		deleteOrder(2);
		System.out.println(getAllOrders());
		
		updateOrder(1, 1000);
		System.out.println(getOrder(1));
	}
	
	public static Orders getOrder(int orderId) {
		
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Orders order = null;
		
		try {
			tx.begin();
			order = em.find(Orders.class, orderId);
			tx.commit();
		}catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		
		return order;
	}
	
	public List<Orders> getAllOrders() {
		EntityManager em = PublicCommon.getEntityManager();
		List<Orders> orders = null;
		
		try {
			orders = em.createQuery("SELECT E FROM Orders E").getResultList();
		}catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		
		return orders;
	}
	
	public void insertOrder(Orders order) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.persist(order);
			
			tx.commit();
		}catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}
	
	public void updateOrder(int orderId, int orderQty) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Orders findOrder = em.find(Orders.class, orderId);
		
		try {
			tx.begin();

			findOrder.setOrderQty(orderQty);
			
			tx.commit();
		}catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}
	
	public void deleteOrder(int orderId) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Orders findOrder = em.find(Orders.class, orderId);
		try {
			tx.begin();

			em.remove(findOrder);
			
			tx.commit();
		}catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}
	
}
