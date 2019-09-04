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
public class AfricanOriginRule implements DiscountRule {

	/**
	 * 7 % Discount if African Origin
	 *
	 * @param product
	 * @return
	 */
	@Override public Optional<Discount> getDiscount(Product product) {
		if (PromotionDiscountValidator.isAfricanOrigin(product.getOrigin())) {
			return Optional.of(new Discount(0.07 * product.getPrice(), "get 7% off"));
		}
		return Optional.empty();
	}
}
