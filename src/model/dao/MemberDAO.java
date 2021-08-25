package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.dto.Member;
import util.PublicCommon;

public class MemberDAO {
	
	//1. 회원가입
	public static void addMember(String memberId, String phoneNum,String realName,String zipcode)  throws NullPointerException{
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Member newMember = new Member();
		
		try {
			tx.begin();
		
			newMember.setMemberId(memberId);
			newMember.setPhoneNum(phoneNum);
			newMember.setRealName(realName);
			newMember.setZipcode(zipcode);
			newMember.setHoldMoney(0L);
			
			em.persist(newMember);
		
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}
	
	//2. 회원정보수정 - 보유금액수정(입금)
	public static void updateHoldMoney(String memberId, Long holdMoney) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Member member = em.find(Member.class, memberId);
		
		try {
			tx.begin();

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
	public static List<Member> getAllMembers() throws Exception {
		EntityManager em = PublicCommon.getEntityManager();
		List<Member> members = null;
		
		try {
			members = em.createQuery("select m from Member m", Member.class).getResultList();
        } finally {
			em.close();
			em = null;
		}
		return members;
	}
	
	
	//4. 회원 아이디로 단일 조회
	public static Member getMember(String memberId) throws Exception{
		EntityManager em = PublicCommon.getEntityManager();
		Member member = null;
		
		try {
			member = (Member)em.find(Member.class, memberId);
		} finally {
			em.close();
			em = null;
		}
		return member;
	}
	
	//5. 회원 삭제
	public static void deleteMember(String memberId) throws Exception {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Member member = em.find(Member.class, memberId);
		
		try {
			tx.begin();
			
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