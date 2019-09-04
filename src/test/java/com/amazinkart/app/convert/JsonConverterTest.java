package com.amazinkart.app.convert;

import com.amazinkart.app.product.Discount;
import com.amazinkart.app.product.Product;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JsonConverterTest {

	@Mock
	JsonConverter jsonConverter;

	@Before
	public void init() {
		jsonConverter = new JsonConverter();
	}

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void convertToJsonWithoutInitialize() throws IOException {
		thrown.expect(NullPointerException.class);
		Product product = getProduct();
		jsonConverter.toJson(product);
		verify(jsonConverter).toJson(product);
	}

	@Test
	public void convertToJsonWithInitialize() throws IOException {
		Product product = getProduct();
		jsonConverter.init();
		String json = jsonConverter.toJson(product);
		Product convertedProduct = jsonConverter.fromJson(json, Product.class);
		assertEquals(product, convertedProduct);

	}

	private Product getProduct() {
		Product product = new Product();
		product.setDiscount(new Discount());
		product.setCurrency("INR");
		product.setPrice(10.0);
		product.setArrival("NEW");
		product.setCategory("electronics");
		product.setInventory(10);
		product.setOrigin("Asia");
		product.setProductName("ABC");
		product.setRating(2.5f);
		return product;
	}
}
