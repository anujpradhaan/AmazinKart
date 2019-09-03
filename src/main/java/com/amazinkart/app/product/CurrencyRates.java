package com.amazinkart.app.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-03}
 */
@Data
public class CurrencyRates {

	private Map<String,Double> rates;
	private String base;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;

}
