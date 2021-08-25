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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
public class Coin {

	@Id
	@Column(name="coin_id")
	private String coinId;
	
	@Column(name="coin_price")
	private Long coinPrice;
	
	@Column(name="total_qty")
	private Long totalQty;
	
	@OneToMany(mappedBy="coinId")
	List<Orders> orders = new ArrayList<>();
	
	@Override
	public String toString() {
		return "코인 ID : " + coinId + " / 코인 가격 : " + coinPrice + " / 총 수량 : " + totalQty;
	}
}
