package com.amazinkart.app.product.service;

import com.amazinkart.app.product.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.*;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Test
	public void getProductAfterDiscountsWithoutAnyStrategy() {
		List<Product> productList = productService.getProductsUsingPromotionType("");
		productList = productList.stream().filter(p -> p.getDiscount() != null)
				.collect(Collectors.toList());

		assertTrue(!productList.isEmpty());
		productList.forEach(product -> {
			assertNotNull(product.getDiscount());
			assertTrue("get 2 % off".equalsIgnoreCase(product.getDiscount().getDiscountTag()));
			assertEquals(product.getDiscount().getAmount(), product.getPrice() * 0.02);
		});

	}

	@Test
	public void getProductAfterDiscountsWithPromotionStrategyA() {
		List<Product> productList = productService.getProductsUsingPromotionType("promotionSetA");
		productList = productList.stream().filter(p -> p.getDiscount() != null)
				.collect(Collectors.toList());

		assertTrue(!productList.isEmpty());
		productList.forEach(product -> {
			assertNotNull(product.getDiscount());
			assertNotNull(product.getDiscount().getDiscountTag());
			assertNotNull(product.getDiscount().getAmount());
		});

	}

	@Test
	public void getProductAfterDiscountsWithPromotionStrategyB() {
		List<Product> productList = productService.getProductsUsingPromotionType("promotionSetB");
		productList = productList.stream().filter(p -> p.getDiscount() != null)
				.collect(Collectors.toList());

		assertTrue(!productList.isEmpty());
		productList.forEach(product -> {
			assertNotNull(product.getDiscount());
			assertNotNull(product.getDiscount().getDiscountTag());
			assertNotNull(product.getDiscount().getAmount());
		});

	}
}
