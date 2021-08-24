package model.dao;

import java.sql.Date;
import java.util.List;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.dto.Orders;


public class OrderDAO {
	private EntityManager entityManager;
	
	public Optional<Orders> get(String coinId) {
		return Optional.ofNullable(entityManager.find(Orders.class, coinId));
	}
	
	@SuppressWarnings("unchecked")
	public List<Orders> getAllOrders(){
		Query query = entityManager.createQuery("SELECT E FROM Member E");
		return query.getResultList();
	}
	
	public void save(Orders order) {
		executeInsideTransaction(entityManager -> entityManager.persist(order));
	}
	
	public void update(Orders order, Object[] params) {
		order.setOrderId((int) Objects.requireNonNull(params[0], "���� Id ���� null�� �� �� �����ϴ�."));
		order.setOrderDate((String) Objects.requireNonNull(params[1], "���� ���� null�� �� �� �����ϴ�"));
		order.setOrderQty((int) Objects.requireNonNull(params[2], "�� ����� null�Դϴ�."));
		order.setTotalPrice((int) Objects.requireNonNull(params[3], "�� ���� null�Դϴ�."));		
		executeInsideTransaction(entityManager -> entityManager.merge(order));
	}
	
	public void delete(Orders order) {
		executeInsideTransaction(entityManager -> entityManager.remove(order));
	}
	
	private void executeInsideTransaction(Consumer<EntityManager> action) {
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			action.accept(entityManager);
			tx.commit();
		}catch (RuntimeException e) {
			tx.rollback();
			throw e;
		}
	}
}