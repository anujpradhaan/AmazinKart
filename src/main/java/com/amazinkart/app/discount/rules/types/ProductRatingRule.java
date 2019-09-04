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
public class ProductRatingRule implements DiscountRule {

	/**
	 * Get 8% discount if rating less than 2,
	 * Get 4% if equals 2, else no discount
	 *
	 * @param product
	 * @return
	 */
	@Override public Optional<Discount> getDiscount(Product product) {
		if (PromotionDiscountValidator.isRatingDiscountApplicable(product.getRating())) {
			if (product.getRating() < 2) {
				return Optional.of(new Discount(0.08 * product.getPrice(), "get 8% off"));
			} else if (product.getRating() == 2) {
				return Optional.of(new Discount(0.04 * product.getPrice(), "get 4% off"));
			}
		}
		return Optional.empty();
	}
}
