package model.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.dto.Coin;
import util.PublicCommon;

public class CoinDAO {
	
	// 코인 아이디로 단일 조회
	public static Coin getCoin(String coinId) throws Exception{
		EntityManager em = PublicCommon.getEntityManager();
		Coin coin = null;
		try {
			coin = em.find(Coin.class, coinId);
		} finally {
			em.close();
			em = null;
		}
		return coin;
	}
	
	// 코인 전체 조회
	public static List<Coin> getAllCoins() {
		EntityManager em = PublicCommon.getEntityManager();
		List<Coin> coins = null;
		try {
			coins = em.createQuery("SELECT E FROM Coin E", Coin.class).getResultList();
		} finally {
			em.close();
			em = null;
		}
		return coins;
	}
	
	// 코인 넣기
	public static void insertCoin(Coin coin) {
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
	
	// 코인 수정
	public static void updateCoin(String coinId, Long totalQty) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Coin findCoin = null;
		try {
			tx.begin();
			findCoin = em.find(Coin.class, totalQty);
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
	
	// 코인 삭제
	public static void deleteCoin(String coinId) throws Exception {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Coin findcoin = null;
		try {
			tx.begin();
			if (em.contains(coinId)) {
				findcoin = em.find(Coin.class, coinId);
			}
			em.remove(findcoin);
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
