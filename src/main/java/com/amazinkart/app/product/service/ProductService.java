package com.amazinkart.app.product.service;

import com.amazinkart.app.discount.strategies.CommonDiscountStrategy;
import com.amazinkart.app.discount.service.DiscountService;
import com.amazinkart.app.discount.strategies.DiscountStrategyStrategyA;
import com.amazinkart.app.discount.strategies.DiscountStrategyStrategyB;
import com.amazinkart.app.product.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
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

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CurrencyExchangeService currencyExchangeService;

	@Autowired
	private DiscountStrategyStrategyA promotionStrategyA;

	@Autowired
	private DiscountStrategyStrategyB promotionStrategyB;

	@Autowired
	private DiscountService discountService;

	/**
	 * Fetch Products from given productName api and Keep in memory
	 *
	 * @param url
	 * @return
	 */
	public List<Product> getProductsFromURL(String url) {
		log.debug("Fetching products from url={}", url);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(PRODUCT_API,
				HttpMethod.GET,
				entity,
				new ParameterizedTypeReference<List<Product>>() {
				});
		log.debug("Available Products= {}", responseEntity.getBody());
		return convertCurrencyToINR(responseEntity.getBody());
	}

	/**
	 * @param productList
	 * @return
	 */
	private List<Product> convertCurrencyToINR(List<Product> productList) {
		return productList.stream().map(product -> {
			double inrPrice = currencyExchangeService.getINREquivalentForCurrency(product.getCurrency(), product.getPrice());
			product.setPrice(inrPrice);
			product.setCurrency("INR");
			return product;
		}).collect(Collectors.toList());

	}

	public List<Product> getProductsUsingPromotionType(String promotionType) {
		CommonDiscountStrategy commonDiscountStrategy = getPromotionFactoryUsingType(promotionType);
		return discountService.getProductsAfterDiscounts(getProductsFromURL(PRODUCT_API), commonDiscountStrategy);
	}

	private CommonDiscountStrategy getPromotionFactoryUsingType(String promotionType) {
		switch (promotionType) {
			case "promotionSetA":
				return promotionStrategyA;
			case "promotionSetB":
				return promotionStrategyB;
			default:
				throw new UnsupportedOperationException("Promotion type not supported");
		}
	}

}
