package br.com.rft.model.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rft.model.domain.Rate;
import br.com.rft.model.domain.Rates;
import br.com.rft.model.repository.ExchangeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RateServiceImpl implements RateService {

	@Autowired
	private ExchangeRepository exchangeRepository;

	@Override
	public Rate findRate(String base, String coin) {
		List<Rate> rs = new ArrayList<>();
		
		findRateFromExternalAPI(coin, rs);
		
		return rs.stream().filter(rate -> rate.getCoin().equals(base)).findAny().orElse(null);
	}

	private Optional<Rates> findRateFromExternalAPI(String coin, List<Rate> rs) {
		Optional<Rates> rates = exchangeRepository.getCoin(coin);
		
		rates.get().getRates().entrySet().stream().forEach(entry -> {
			Rate rate = Rate.builder().coin(entry.getKey()).value(entry.getValue().setScale(2, BigDecimal.ROUND_HALF_UP)).build();
			rs.add(rate);
		});
		
		log.debug("Rate Rates: " + rates.toString());
		return rates;
	}

}
