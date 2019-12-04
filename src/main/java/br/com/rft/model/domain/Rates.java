package br.com.rft.model.domain;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;

@Data
public class Rates {
	private Map<String, BigDecimal> rates;
	private String base;
	private String date;
}
