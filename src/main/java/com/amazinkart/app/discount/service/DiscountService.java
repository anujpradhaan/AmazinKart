package com.amazinkart.app.discount.service;

import com.amazinkart.app.discount.strategies.CommonDiscountStrategy;
import com.amazinkart.app.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
@Service
public class DiscountService {

	public List<Product> getProductsAfterDiscounts(List<Product> productList, CommonDiscountStrategy commonDiscountStrategy) {
		return productList
				.stream()
				.map(commonDiscountStrategy::applyDiscount)
				.collect(Collectors.toList());
	}
}
