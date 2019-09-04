package com.amazinkart.app.discount;

import com.amazinkart.app.discount.rules.PromotionDiscountValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PromotionDiscountValidatorTest {

	@Test
	public void test_successfulDiscountApplicableForInventory() {
		boolean isDiscountApplicable = PromotionDiscountValidator.isInventoryDiscountApplicable(200);
		assertEquals(Boolean.TRUE, isDiscountApplicable);
	}

	@Test
	public void test_unsuccessfulDiscountApplicableForInventory() {
		boolean isDiscountApplicable = PromotionDiscountValidator.isInventoryDiscountApplicable(10);
		assertEquals(Boolean.FALSE, isDiscountApplicable);
	}

	@Test
	public void test_successfulDiscountApplicableForProductArrival() {
		boolean isDiscountApplicable = PromotionDiscountValidator.isProductArrivalDiscountApplicable("new");
		assertEquals(Boolean.TRUE, isDiscountApplicable);
	}

	@Test
	public void test_unsuccessfulDiscountApplicableForProductArrival() {
		boolean isDiscountApplicable = PromotionDiscountValidator.isProductArrivalDiscountApplicable("old");
		assertEquals(Boolean.FALSE, isDiscountApplicable);
	}

	@Test
	public void test_successfulDiscountApplicableForOrigin() {
		boolean isDiscountApplicable = PromotionDiscountValidator.isOriginDiscountApplicable("africa");
		assertEquals(Boolean.TRUE, isDiscountApplicable);
	}

	@Test
	public void test_unsuccessfulDiscountApplicableForOrigin() {
		boolean isDiscountApplicable = PromotionDiscountValidator.isOriginDiscountApplicable("asia");
		assertEquals(Boolean.FALSE, isDiscountApplicable);
	}

	@Test
	public void test_successfulDiscountApplicableForRating() {
		boolean isDiscountApplicable = PromotionDiscountValidator.isRatingDiscountApplicable(1);
		assertEquals(Boolean.TRUE, isDiscountApplicable);
	}

	@Test
	public void test_unsuccessfulDiscountApplicableForRating() {
		boolean isDiscountApplicable = PromotionDiscountValidator.isRatingDiscountApplicable(3);
		assertEquals(Boolean.FALSE, isDiscountApplicable);
	}

	@Test
	public void test_successfulDiscountApplicableForElectronicsAndPrice_600() {
		boolean isDiscountApplicable = PromotionDiscountValidator.isCategoryDiscountedApplicable("electronics", 600);
		assertEquals(Boolean.TRUE, isDiscountApplicable);
	}

	@Test
	public void test_successfulDiscountApplicableForElectronicsAndPrice_100() {
		boolean isDiscountApplicable = PromotionDiscountValidator.isCategoryDiscountedApplicable("electronics", 200);
		assertEquals(Boolean.FALSE, isDiscountApplicable);
	}

	@Test
	public void test_successfulDefaultDiscountApplicablePrice_6000() {
		boolean isDiscountApplicable = PromotionDiscountValidator.isDefaultDiscountApplicable(6000);
		assertEquals(Boolean.TRUE, isDiscountApplicable);
	}

	@Test
	public void test_unsuccessfulDefaultDiscountApplicablePrice_600() {
		boolean isDiscountApplicable = PromotionDiscountValidator.isDefaultDiscountApplicable(600);
		assertEquals(Boolean.FALSE, isDiscountApplicable);
	}

}
