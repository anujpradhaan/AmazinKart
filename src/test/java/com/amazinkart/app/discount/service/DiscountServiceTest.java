package com.amazinkart.app.discount.service;

import com.amazinkart.app.product.Product;
import com.amazinkart.app.product.service.ProductDataGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceTest {

	private DiscountService discountService;

	@Before
	public void init() {
		discountService = new DiscountService();
		discountService.init();
	}

	@Test
	public void test_DiscountApplicationForPromotionA() {
		Product product = ProductDataGenerator.getSampleProduct();
		product.setRating(1.5f);
		product.setPrice(100);
		List<Product> discountedProducts = discountService.getProductsAfterDiscounts(Collections.singletonList(product), "promotionSetA");
		assertEquals(1, discountedProducts.size());
		assertNotNull(discountedProducts.get(0));
		assertEquals(8.0, discountedProducts.get(0).getDiscount().getAmount(), 0.0);
		assertTrue("get 8 % off".equalsIgnoreCase(discountedProducts.get(0).getDiscount().getDiscountTag()));
	}

	@Test
	public void test_NoDiscountApplicationForPromotionA() {
		Product product = ProductDataGenerator.getSampleProduct();
		product.setRating(2.5f);
		product.setPrice(100);
		List<Product> discountedProducts = discountService.getProductsAfterDiscounts(Collections.singletonList(product), "promotionSetA");
		assertEquals(1, discountedProducts.size());
		assertNull(discountedProducts.get(0).getDiscount());
	}

	@Test
	public void test_DefaultDiscountApplicationForPromotionA() {
		Product product = ProductDataGenerator.getSampleProduct();
		product.setRating(2.5f);
		product.setPrice(1000);
		List<Product> discountedProducts = discountService.getProductsAfterDiscounts(Collections.singletonList(product), "promotionSetA");
		assertEquals(1, discountedProducts.size());
		assertNotNull(discountedProducts.get(0));
		assertEquals(new Double(100.0), discountedProducts.get(0).getDiscount().getAmount());
		assertTrue("get 100 FLAT off".equalsIgnoreCase(discountedProducts.get(0).getDiscount().getDiscountTag()));
	}

	@Test
	public void test_DiscountApplicationForPromotionB() {
		Product product = ProductDataGenerator.getSampleProduct();
		product.setInventory(25);
		product.setPrice(100);
		List<Product> discountedProducts = discountService.getProductsAfterDiscounts(Collections.singletonList(product), "promotionSetB");
		assertEquals(1, discountedProducts.size());
		assertNotNull(discountedProducts.get(0).getDiscount());
		assertEquals(12.0, discountedProducts.get(0).getDiscount().getAmount(), 0.0);
		assertTrue("get 12 % off".equalsIgnoreCase(discountedProducts.get(0).getDiscount().getDiscountTag()));
	}

	@Test
	public void test_NoDiscountApplicationForPromotionB() {
		Product product = ProductDataGenerator.getSampleProduct();
		product.setRating(2.5f);
		product.setPrice(100);
		List<Product> discountedProducts = discountService.getProductsAfterDiscounts(Collections.singletonList(product), "promotionSetB");
		assertEquals(1, discountedProducts.size());
		assertNull(discountedProducts.get(0).getDiscount());
	}

	@Test
	public void test_DefaultDiscountApplicationForPromotionB() {
		Product product = ProductDataGenerator.getSampleProduct();
		product.setPrice(2000);
		List<Product> discountedProducts = discountService.getProductsAfterDiscounts(Collections.singletonList(product), "promotionSetB");
		assertEquals(1, discountedProducts.size());
		assertNotNull(discountedProducts.get(0).getDiscount());
		assertEquals(40.0, discountedProducts.get(0).getDiscount().getAmount(), 0.0);
		assertTrue("get 2 % off".equalsIgnoreCase(discountedProducts.get(0).getDiscount().getDiscountTag()));
	}

}
