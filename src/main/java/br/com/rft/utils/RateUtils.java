package br.com.rft.utils;

import java.math.BigDecimal;

import br.com.rft.model.domain.CoinSimbol;

public class RateUtils {
	
	private RateUtils() {
		
	}

	public static String getMessage(String coin, String toCoin, BigDecimal coinRateValue) {
		String coinName = CoinSimbol.getCoinName(coin);
		String toCoinName = CoinSimbol.getCoinName(toCoin);

		return "Cotação do(a) " + coinName + " frente ao " + toCoinName + " Valor: " + coinRateValue.toString();
	}
}
