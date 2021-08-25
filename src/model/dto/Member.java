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
		return "memberId:" + memberId + " 님의 정보입니다. [phoneNum =" + phoneNum + ", realName=" + realName + ", zipcode="
				+ zipcode + ", holdMoney=" + holdMoney + "]";
	}
	
}

