package model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member {
	
	@Id
	@Column(name="member_id")
	private String memberId;
	
	@Column(name="phone_num")
	private String phoneNum;
	
	@Column(name="real_name")
	private String realName;
	
	private String zipcode;
	
	@Column(name="hold_money")
	private Long holdMoney;
	
	@OneToMany(mappedBy="memberId", fetch = FetchType.EAGER)
	List<Orders> orders = new ArrayList<>();

	@Override
	public String toString() {
		return "회원 아이디 = " + memberId + " 회원 전화번호 = " + phoneNum + " 회원 이름 = " + realName + " 우편번호 = " + zipcode +
				" 보유하고 있는 머니 = " + holdMoney;
	}
	
}

