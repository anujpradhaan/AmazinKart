package com.amazinkart.app.discount.rules.types;

import com.amazinkart.app.discount.rules.PromotionDiscountValidator;
import com.amazinkart.app.discount.rules.DiscountRule;
import com.amazinkart.app.product.Discount;
import com.amazinkart.app.product.Product;

import java.util.Optional;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-04}
 */
public class ProductArrivalStatusRule implements DiscountRule {
	@Override public Optional<Discount> getDiscount(Product product) {
		if (PromotionDiscountValidator.isProductArrivalDiscountApplicable(product.getArrival())) {
			return Optional.of(new Discount(0.07 * product.getPrice(), String.format(GET_X_TYPE_OFF, "7", "%")));
		}
		return Optional.empty();
	}
}
