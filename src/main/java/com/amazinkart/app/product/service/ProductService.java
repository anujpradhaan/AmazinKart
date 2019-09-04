package com.amazinkart.app.product.service;

import com.amazinkart.app.discount.service.DiscountService;
import com.amazinkart.app.product.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
@Service
@Slf4j
@Data
public class ProductService {

	public static final String PRODUCT_API = "https://api.jsonbin.io/b/5d31a1c4536bb970455172ca/latest";

	private final RestTemplate restTemplate;

	private final DiscountService discountService;

	private final CurrencyExchangeService currencyExchangeService;

	@Autowired
	public ProductService(RestTemplate restTemplate, CurrencyExchangeService currencyExchangeService, DiscountService discountService) {
		this.restTemplate = restTemplate;
		this.currencyExchangeService = currencyExchangeService;
		this.discountService = discountService;
	}

	/**
	 * Fetch Products from given URL on basis of provided discount strategy
	 * @param promotionType
	 * @return
	 */
	public List<Product> getProductsUsingPromotionType(String promotionType) {
		return discountService.getProductsAfterDiscounts(getProductsFromURL(), promotionType);
	}

	/**
	 * Fetch Products from given productName api and Keep in memory
	 *
	 * @return - Returns the list of products
	 */
	private List<Product> getProductsFromURL() {
		log.debug("Fetching products from url={}", ProductService.PRODUCT_API);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(PRODUCT_API,
				HttpMethod.GET,
				entity,
				new ParameterizedTypeReference<List<Product>>() {
				});
		log.debug("Available Products= {}", responseEntity.getBody());
		return convertCurrencyToINR(Objects.requireNonNull(responseEntity.getBody()));
	}

	private List<Product> convertCurrencyToINR(List<Product> productList) {
		return productList.stream().peek(product -> {
			double inrPrice = currencyExchangeService.getINREquivalentForCurrency(product.getCurrency(), product.getPrice());
			product.setPrice(inrPrice);
			product.setCurrency("INR");
		}).collect(Collectors.toList());

	}

}
