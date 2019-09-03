package com.amazinkart.app.product.service;

import com.amazinkart.app.product.CurrencyRates;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
@Service
@Slf4j
@Data
public class CurrencyExchangeService {

	private static final String CURRENCY_EXCHANGE_URL = "https://api.exchangeratesapi.io/latest";

	@Autowired
	private RestTemplate restTemplate;

	public CurrencyRates getCurrencyRatesFromURL(String url) {
		log.debug("Fetching currency rates from url={}", url);
		ResponseEntity<CurrencyRates> responseEntity = restTemplate.exchange(url,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<CurrencyRates>() {
				});
		return responseEntity.getBody();
	}

	public double getINREquivalentForCurrency(String currency, double price) {
		CurrencyRates currencyRates = getCurrencyRatesFromURL(CURRENCY_EXCHANGE_URL);
		if ("INR".equalsIgnoreCase(currency)) {
			return price;
		}
		/*
		 * Assumption : Considering the base currency will remain EUR
		 * ((1/CURRENCY_CURRENCY)*INR_CURRENCY)*price
		 * */
		return ((1 / currencyRates.getRates().get(currency)) * currencyRates.getRates().get("INR")) * price;
	}

}
