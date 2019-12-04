package br.com.rft.model.domain;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rate {
	private String coin;
	private BigDecimal value;
}
