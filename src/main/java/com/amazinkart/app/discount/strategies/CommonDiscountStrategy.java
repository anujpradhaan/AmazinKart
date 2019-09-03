package com.amazinkart.app.discount.strategies;

import com.amazinkart.app.discount.PromotionDiscountValidator;
import com.amazinkart.app.discount.Promotions;
import com.amazinkart.app.product.Discount;
import com.amazinkart.app.product.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
public abstract class CommonDiscountStrategy extends Promotions {

	public Product applyDiscount(Product product) {
		Discount discount = getDiscountAsPerCommonRules(product)
				.orElseGet(() -> {
					if (PromotionDiscountValidator.isDefaultDiscountApplicable(product.getPrice())) {
						return new Discount(0.02 * product.getPrice(), "get 2% off");
					}
					return null;
				});
		product.setDiscount(discount);
		return product;
	}

	/**
	 * Get Maximum discount of all
	 *
	 * @param product
	 * @return
	 */
	private Optional<Discount> getDiscountAsPerCommonRules(Product product) {
		return getPromotionalDiscount(product)
				.stream()
				.max(Comparator.comparingDouble(Discount::getAmount));
	}

	// Abstract Methods

	abstract List<Discount> getPromotionalDiscount(Product product);
}
