package com.amazinkart.app.product;

import lombok.Data;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
@Data
public class Discount {
	private Double amount;
	private String discountTag;

	public Discount() {
		super();
	}

	public Discount(Double amount, String discountTag) {
		this.amount = amount;
		this.discountTag = discountTag;
	}

}
