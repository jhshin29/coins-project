package model.dao;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.fields.FieldIndexSelector;

import model.dto.Member;
import util.PublicCommon;

public class MemberDAO {
	
	//1. 회원가입
	public static void addMember(Member member) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
		
			em.persist(member);
		
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}
	
	//2.  회원정보수정
	public static void updateMember(String memberId, Long holdMoney) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Member member = null;
		
		try {
			tx.begin();
			member = em.find(Member.class, memberId);
			member.setHoldMoney(holdMoney);
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}
	
	//3. 회원 전체 조회
	public static List<Member> getAllMember() {
		EntityManager em = PublicCommon.getEntityManager();
		List<Member> members = null;
		
		try {
			members = em.createQuery("select m from Member m", Member.class).getResultList();
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
			em.close();
			em = null;
		}
		return members;
	}
	
	
	//4. 회원 아이디로 단일 조회
	public static Member getMember(String memberId) {
		EntityManager em = PublicCommon.getEntityManager();
		Member member = null;
		
		try {
			member = (Member)em.find(Member.class, memberId);
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return member;
	}
	
	//5. 회원 삭제
	public static void deleteMember(String memberId) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Member member = null;
		
		tx.begin();
		
		try {
			if(em.contains(memberId)) 
				member = (Member)em.find(Member.class, memberId);

			System.out.println(member);
			em.remove(member);
			tx.commit();
		} catch (Exception e) {
		    tx.rollback();
		    e.printStackTrace();
	    } finally {
	    	em.close();
	    	em = null;
		}
	}
}
