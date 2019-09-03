package com.amazinkart.app.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
	private String category;
	private int inventory;
	private float rating;
	private String currency;
	private double price;
	private String origin;
	@JsonProperty("product")
	private String productName;
	private String arrival;
	private Discount discount;
}
