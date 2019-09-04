package com.amazinkart.app.product.service;

import com.amazinkart.app.product.Discount;
import com.amazinkart.app.product.Product;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-04}
 */
public class ProductDataGenerator {

	private ProductDataGenerator() {

	}

	public static Product getSampleProduct() {
		Product product = new Product();
		product.setDiscount(new Discount());
		product.setCurrency("INR");
		product.setPrice(10.0);
		product.setArrival("OLD");
		product.setCategory("electronics");
		product.setInventory(10);
		product.setOrigin("Asia");
		product.setProductName("ABC");
		product.setRating(2.5f);
		return product;
	}
}
