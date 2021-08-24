package model.dao;

import java.sql.SQLException;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.dto.Member;
import util.PublicCommon;


public class MemberDAO {
	
	public static Member getMember(String memberID) {
		Member member = null;
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			member = em.find(Member.class, memberID);
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
			em = null;
		}
		return member;
	}
	
	public static List<Member> getAllMembers() throws SQLException{
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		List<Member> list = null;
		
		tx.begin();
		
		try {
			list = em.createQuery("select m from member m", Member.class).getResultList();
		} catch(Exception e) {
			tx.rollback();
		} finally {
			em.close();
			em = null;
		}
		return list;
	}
	
	public static void addMember(Member member) {
		
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			em.persist(member);
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
			em = null;
		}
	}
	
	public static Member selectMember(String memberId) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Member selectedMember = null;
		
		tx.begin();
		
		try {
			selectedMember = em.find(Member.class, memberId);
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
			em = null;
		}
		return selectedMember;
	}
	
	public static void deleteMember(Member member) {
		
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
	
		try {
			em.remove(member);
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
			em = null;
		}
	}
}
