package com.amazinkart.app.discount;

import com.amazinkart.app.product.Discount;
import com.amazinkart.app.product.Product;

import java.util.Optional;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
public class Promotions {

	/**
	 * 7 % Discount if African Origin
	 *
	 * @param product
	 * @return
	 */
	protected Optional<Discount> getOriginDiscount(Product product) {
		if (PromotionDiscountValidator.isAfricanOrigin(product.getOrigin())) {
			return Optional.of(new Discount(0.07 * product.getPrice(), "get 7% off"));
		}
		return Optional.empty();
	}

	/**
	 * Get 8% discount if rating less than 2,
	 * Get 4% if equals 2, else no discount
	 *
	 * @param product
	 * @return
	 */
	protected Optional<Discount> getRatingDiscount(Product product) {
		if (PromotionDiscountValidator.isRatingDiscountApplicable(product.getRating())) {
			if (product.getRating() < 2) {
				return Optional.of(new Discount(0.08 * product.getPrice(), "get 8% off"));
			} else if (product.getRating() == 2) {
				return Optional.of(new Discount(0.04 * product.getPrice(), "get 4% off"));
			}
		}
		return Optional.empty();
	}

	protected Optional<Discount> getCategoryDiscount(Product product) {
		if (PromotionDiscountValidator.isCategoryDiscountedApplicable(product.getCategory(), product.getPrice())) {
			return Optional.of(new Discount(100.0, "get 100 Flat off"));
		}
		return Optional.empty();
	}

	protected Optional<Discount> getInventoryDiscount(Product product) {
		if (PromotionDiscountValidator.isInventoryDiscountApplicable(product.getInventory())) {
			return Optional.of(new Discount(0.12 * product.getPrice(), "get 12% off"));
		}
		return Optional.empty();
	}

	protected Optional<Discount> getProductArrivalDiscount(Product product) {
		if (PromotionDiscountValidator.isProductArrivalDiscountApplicable(product.getArrival())) {
			return Optional.of(new Discount(0.07 * product.getPrice(), "get 7% off"));
		}
		return Optional.empty();
	}
}
