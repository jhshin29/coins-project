package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import lombok.extern.slf4j.Slf4j;
import model.dto.Member;
import util.PublicCommon;

@Slf4j
public class MemberDAO {
	
	
	//1. 회원가입
	public static boolean addMember(String memberId, String phoneNum,String realName,String zipcode)  throws Exception{
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Member member = em.find(Member.class, memberId);
		
		try {
			tx.begin();
			if (member == null) {
				Member newMember = new Member();
				newMember.setMemberId(memberId);
				newMember.setPhoneNum(phoneNum);
				newMember.setRealName(realName);
				newMember.setZipcode(zipcode);
				newMember.setHoldMoney(0L);
				
				em.persist(newMember);
				log.info(memberId+" 님이 가입하셨습니다.");
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
	
	//2. 회원정보수정 - 보유금액수정(입출금)
	public static boolean updateHoldMoney(String memberId, Long holdMoney) throws Exception{
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Member member = em.find(Member.class, memberId);
		
		try {
			tx.begin();
			if (member != null) {
				member.setHoldMoney(holdMoney);
				log.info(memberId + "님이 금액을 " + holdMoney+" 로 수정하셨습니다.");
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
	public static boolean deleteMember(String memberId) throws Exception {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Member member = em.find(Member.class, memberId);
		
		try {
			tx.begin();
			if (member != null) {
				em.remove(member);
				
				log.info(memberId +" 님이 탈퇴하셨습니다.");
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