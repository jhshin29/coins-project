package model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@ToString
@Entity
public class Member {
	
	@Id
	@Column(name="member_id")
	private String memberId;
	
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "order")
//	@JoinColumn(name = "member_id")
//	private Collection<Member> member = new ArrayList<>();
	
	@Column(name="phone_num")
	private String phoneNum;
	
	@Column(name="real_name")
	private String realName;
	
	private String zipcode;
	
	@Column(name="hold_money")
	private Long holdMoney;
	
	@OneToMany(mappedBy="memberId")
	List<Orders> orders = new ArrayList<>();
	
}

