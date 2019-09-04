package com.amazinkart.app.discount.rules;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
public class PromotionDiscountValidator {

	private PromotionDiscountValidator() {
		//Not supported for static
	}

	public static boolean isInventoryDiscountApplicable(int inventory) {
		return inventory > 20;
	}

	public static boolean isProductArrivalDiscountApplicable(String arrivalStatus) {
		return "new".equalsIgnoreCase(arrivalStatus);
	}

	public static boolean isOriginDiscountApplicable(String origin) {
		return "africa".equalsIgnoreCase(origin);
	}

	public static boolean isRatingDiscountApplicable(double rating) {
		return rating <= 2.0;
	}

	public static boolean isCategoryDiscountedApplicable(String category, double price) {
		return ("electronics".equalsIgnoreCase(category) ||
				"furnishing".equalsIgnoreCase(category)) && price >= 500.0;
	}

	public static boolean isDefaultDiscountApplicable(double price) {
		return price > 1000.0;
	}
}
