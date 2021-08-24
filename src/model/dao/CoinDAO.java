package model.dao;

import java.sql.SQLException;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.dto.Coin;
import model.dto.Member;
import util.PublicCommon;


public class CoinDAO {
	
	public static Coin getCoin(String coinId) {
		Coin coin = null;
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			coin = em.find(Coin.class, coinId);
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
			em = null;
		}
		return coin;
	}
	
	public static void updateCoin(String coinId) {
		Coin coin =null;
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
		}catch(Exception e) {
		}finally {
			em.close();
			em = null;
		}
	}
	
	public static List<Coin> getAllMembers() throws SQLException{
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Coin> list = null;
		tx.begin();
		try {
			list = em.createQuery("select m from coin m", Coin.class).getResultList();
		} catch(Exception e) {
			tx.rollback();
		} finally {
			em.close();
			em = null;
		}
		return list;
	}
	
	public static void addCoin(Coin coin) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(coin);
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
			em = null;
		}
	}
	
	public static Coin selectCoin(String coinId) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Coin selectedCoin = null;
		tx.begin();
		try {
			selectedCoin = em.find(Coin.class, coinId);
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
			em = null;
		}
		return selectedCoin;
	}
	
	public static void deleteCoin(Coin coin) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.remove(coin);
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
			em = null;
		}
	}
}
