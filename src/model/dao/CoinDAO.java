package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import lombok.extern.slf4j.Slf4j;
import model.dto.Coin;
import util.PublicCommon;

@Slf4j
public class CoinDAO {
	private static Coin instance = new Coin();
	public static Coin getInstance() {
		return instance;
	}
	public static Coin getCoin(String coinId) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Coin coin = null;
		try {
			tx.begin();
			coin = em.find(Coin.class, coinId);
			tx.commit();
		}catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return coin;
	}
	
	public List<Coin> getAllCoins() {
		EntityManager em = PublicCommon.getEntityManager();
		List<Coin> coins = null;
		
		try {
			coins = em.createQuery("SELECT E FROM Coin E").getResultList();
		}catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return coins;
	}
	
	public void insertCoin(Coin coin) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(coin);
			tx.commit();
		}catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}
	
	public void updateCoin(String coinId, long totalQty) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Coin findCoin = em.find(Coin.class, totalQty);
		try {
			tx.begin();
			findCoin.setTotalQty(totalQty);
			tx.commit();
		}catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}
	
	public void deleteCoin(String coinId) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Coin findCoin = em.find(Coin.class, coinId);
		try {
			tx.begin();
			
			em.remove(findCoin);
			
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

