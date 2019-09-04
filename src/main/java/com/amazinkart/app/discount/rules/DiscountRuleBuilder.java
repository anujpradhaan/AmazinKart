package com.amazinkart.app.discount.rules;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-04}
 */
public class DiscountRuleBuilder<DiscountRule> {
	private List<DiscountRule> discountRuleList;

	public DiscountRuleBuilder() {
		discountRuleList = new LinkedList<>();
	}

	public DiscountRuleBuilder add(DiscountRule discountRule) {
		discountRuleList.add(discountRule);
		return this;
	}

	public List<DiscountRule> build() {
		return discountRuleList;
	}
}
