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
public class ProductCategoryRule implements DiscountRule {
	@Override public Optional<Discount> getDiscount(Product product) {
		if (PromotionDiscountValidator.isCategoryDiscountedApplicable(product.getCategory(), product.getPrice())) {
			return Optional.of(new Discount(100.0, String.format(GET_X_TYPE_OFF, "100", "FLAT")));
		}
		return Optional.empty();
	}
}
