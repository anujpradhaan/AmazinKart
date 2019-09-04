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
public class ProductInventoryRule implements DiscountRule {
	@Override public Optional<Discount> getDiscount(Product product) {
		if (PromotionDiscountValidator.isInventoryDiscountApplicable(product.getInventory())) {
			return Optional.of(new Discount(0.12 * product.getPrice(), "get 12% off"));
		}
		return Optional.empty();
	}
}
