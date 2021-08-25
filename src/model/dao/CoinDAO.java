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
	public static List<Coin> getAllCoins() throws Exception{
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
	public static boolean addCoin(String coinId, Long coinPrice, Long totalQty) throws Exception{
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Coin coin = em.find(Coin.class, coinId);
		
		try {
			tx.begin();
			if (coin == null) {
				Coin newCoin = new Coin();
				newCoin.setCoinId(coinId);			
				newCoin.setCoinPrice(coinPrice);
				newCoin.setTotalQty(totalQty);
			
				em.persist(newCoin);
				tx.commit();
				return true;
			}
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return false;
	}
	
	// 코인 수정
	public static boolean updateCoin(String coinId, Long coinPrice) throws Exception {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Coin findCoin = em.find(Coin.class, coinId);
		
		try {
			tx.begin();
			if (findCoin != null) {
				findCoin.setCoinPrice(coinPrice);}
				tx.commit();
				return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return false;
	}
	
	// 코인 삭제
	public static boolean deleteCoin(String coinId) throws Exception{
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Coin findcoin = em.find(Coin.class, coinId);
		
		try {
			tx.begin();
			if (findcoin != null) {
				em.remove(findcoin);
				tx.commit();
				return true;
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return false;
	}
}

