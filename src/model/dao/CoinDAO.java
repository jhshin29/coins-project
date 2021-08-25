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
	public static void addCoin(String coinId, Long coinPrice, Long totalQty){
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Coin newCoin = new Coin();
		
		try {
			tx.begin();
			newCoin.setCoinId(coinId);			
			newCoin.setCoinPrice(coinPrice);
			newCoin.setTotalQty(totalQty);
			em.persist(newCoin);
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
	}
	
	// 코인 수정
	public static void updateCoin(String coinId, Long coinPrice) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Coin findCoin = null;
		try {
			tx.begin();
			findCoin = em.find(Coin.class, coinId);
			findCoin.setCoinPrice(coinPrice);
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
	public static void deleteCoin(String coinId){
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Coin findcoin = em.find(Coin.class, coinId);
		try {
			tx.begin();
			findcoin = (Coin)em.find(Coin.class, coinId);
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

