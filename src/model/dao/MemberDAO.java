package model.dao;

import java.util.List;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.dto.Member;


public class MemberDAO {
	private EntityManager entityManager;
	
	public Optional<Member> get(String memberId) {
		return Optional.ofNullable(entityManager.find(Member.class, memberId));
	}
	
	@SuppressWarnings("unchecked")
	public List<Member> getAllCoins(){
		Query query = entityManager.createQuery("SELECT E FROM Member E");
		return query.getResultList();
	}
	
	public void save(Member member) {
		executeInsideTransaction(entityManager -> entityManager.persist(member));
	}
	
	public void update(Member member, Object[] params) {
		member.setPhoneNum((String) Objects.requireNonNull(params[0], "���� Id ���� null�� �� �� �����ϴ�."));
		member.setRealName((String) Objects.requireNonNull(params[1], "���� ���� null�� �� �� �����ϴ�"));
		member.setZipcode((String) Objects.requireNonNull(params[2], "�� ����� null�Դϴ�."));
		member.setHoldMoney((Long) Objects.requireNonNull(params[3], "�� ���� null�Դϴ�."));		
		executeInsideTransaction(entityManager -> entityManager.merge(member));
	}
	
	public void delete(Member member) {
		executeInsideTransaction(entityManager -> entityManager.remove(member));
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