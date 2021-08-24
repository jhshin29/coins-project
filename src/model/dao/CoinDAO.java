package model.dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.dto.Coin;


public class CoinDAO {
	private EntityManager entityManager;
	
	public Optional<Coin> get(String coinId) {
		return Optional.ofNullable(entityManager.find(Coin.class, coinId));
	}
	
	public List<Coin> getAllCoins(){
		Query query = entityManager.createQuery("SELECT E FROM Coin E");
		return query.getResultList();
	}
	
	public void save(Coin coin) {
		executeInsideTransaction(entityManager -> entityManager.persist(coin));
	}
	
	public void update(Coin coin, Object[] params) {
		coin.setCoinId((String) Objects.requireNonNull(params[0], "���� Id ���� null�� �� �� �����ϴ�."));
		coin.setCoinPrice((Long) Objects.requireNonNull(params[1], "���� ���� null�� �� �� �����ϴ�"));
		coin.setTotalQty((Long) Objects.requireNonNull(params[2], "�� ����� null�Դϴ�."));
		executeInsideTransaction(entityManager -> entityManager.merge(coin));
	}
	
	public void delete(Coin coin) {
		executeInsideTransaction(entityManager -> entityManager.remove(coin));
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
