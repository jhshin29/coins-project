package model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@SequenceGenerator(name="order_seq_gen", sequenceName="order_seq_id",initialValue=1, allocationSize=1)
public class Orders {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="order_seq_gen")
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="order_date")
	private String orderDate;
	
	@Column(name="order_qty")
	private int orderQty;
	
	@Column(name="total_price")
	private long totalPrice;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member memberId;
	
	@ManyToOne
	@JoinColumn(name="coin_id")
	private Coin coinId;
	
	public Orders(String orderDate, int orderQty) {
		this.orderDate = orderDate;
		this.orderQty = orderQty;
	}

	@Override
	public String toString() {
		return " 주문 아이디 = " + orderId + " 주문 날짜 = " + orderDate + " 주문 양 = " + orderQty + "총 가격 = " + totalPrice +
				" 회원 아이디 = " + memberId + " 코인 아이디 " + coinId;
	}
}
