package model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
public class Order {
	@Id
	@Column(name="order_id")
	private String orderId;
	
	@Column(name="order_date")
	private String orderDate;
	
	@Column(name="order_qty")
	private int orderQty;
	
	@Column(name="total_price")
	private int totalPrice;
	
	@Column(name="member_id")
	private Member memberId;
	
	@Column(name="coin_id")
	private Coin coinId;
	
}
