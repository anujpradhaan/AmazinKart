package com.amazinkart.app.discount.rules;

import com.amazinkart.app.product.Discount;
import com.amazinkart.app.product.Product;

import java.util.Optional;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-04}
 */
public interface DiscountRule {
	String GET_X_TYPE_OFF = "get %s %s off";
	Optional<Discount> getDiscount(Product product);

}
