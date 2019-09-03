package com.amazinkart.app.discount.strategies;

import com.amazinkart.app.product.Discount;
import com.amazinkart.app.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
@Component
public class DiscountStrategyStrategyB extends CommonDiscountStrategy {

	@Override List<Discount> getPromotionalDiscount(Product product) {
		List<Discount> discounts = new ArrayList<>();

		getInventoryDiscount(product).ifPresent(discounts::add);
		getProductArrivalDiscount(product).ifPresent(discounts::add);

		return discounts;
	}

}
