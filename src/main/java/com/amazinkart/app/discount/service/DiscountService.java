package com.amazinkart.app.discount.service;

import com.amazinkart.app.discount.rules.DiscountRule;
import com.amazinkart.app.discount.rules.DiscountRuleBuilder;
import com.amazinkart.app.discount.rules.PromotionDiscountValidator;
import com.amazinkart.app.discount.rules.types.*;
import com.amazinkart.app.product.Discount;
import com.amazinkart.app.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

import static com.amazinkart.app.discount.rules.DiscountRule.GET_X_TYPE_OFF;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
@Service
@Slf4j
public class DiscountService {

	private Map<String, List<DiscountRule>> discountRuleMap;

	@PostConstruct
	public void init() {
		discountRuleMap = new HashMap<>();
		discountRuleMap.put("promotionSetA", new DiscountRuleBuilder()
				.add(new AfricanOriginRule())
				.add(new ProductRatingRule())
				.add(new ProductCategoryRule())
				.build());

		discountRuleMap.put("promotionSetB", new DiscountRuleBuilder()
				.add(new ProductInventoryRule())
				.add(new ProductArrivalStatusRule())
				.build());
	}

	public List<Product> getProductsAfterDiscounts(List<Product> productList, String strategy) {
		return productList
				.stream()
				.map(product -> this.applyDiscount(product, strategy))
				.collect(Collectors.toList());
	}

	private Product applyDiscount(Product product, String strategy) {
		Discount discount = getDiscountAsPerProvidedStrategy(product, strategy)
				.orElseGet(() -> {
					log.info("No promotional discount is applicable trying default discount");
					if (PromotionDiscountValidator.isDefaultDiscountApplicable(product.getPrice())) {
						return new Discount(0.02 * product.getPrice(), String.format(GET_X_TYPE_OFF, "2", "%"));
					}
					return null;
				});
		product.setDiscount(discount);
		return product;
	}

	/**
	 * Get Maximum discount of all
	 *
	 * @param product - Product to which discount has to be applied
	 * @return - returns an Optional of Discount as Discount might or might not be applicable
	 */
	private Optional<Discount> getDiscountAsPerProvidedStrategy(Product product, String strategy) {
		return getAllValidDiscountsAsPerStrategy(product, strategy)
				.stream()
				.max(Comparator.comparingDouble(Discount::getAmount));
	}

	private List<Discount> getAllValidDiscountsAsPerStrategy(Product product, String strategy) {
		if (!discountRuleMap.containsKey(strategy)) {
			return Collections.emptyList();
		}
		return discountRuleMap.get(strategy)
				.stream()
				.map(discountRule -> discountRule.getDiscount(product))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());

	}
}
