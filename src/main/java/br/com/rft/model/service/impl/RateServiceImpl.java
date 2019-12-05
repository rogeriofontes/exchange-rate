package br.com.rft.model.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rft.model.domain.Rate;
import br.com.rft.model.domain.Rates;
import br.com.rft.model.repository.ExchangeRepository;
import br.com.rft.model.service.RateService;
import br.com.rft.utils.RateUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RateServiceImpl implements RateService {

	@Autowired
	private ExchangeRepository exchangeRepository;

	@Override
	public String findRate(String base, String coin) {
		List<Rate> rates = findRateFromExternalAPI(coin);

		Rate rateValue = getRate(base, rates);
		log.debug("Valores de Cotaçõe: " + rateValue);

		return RateUtils.getMessage(rateValue.getCoin(), coin, rateValue.getValue());
	}

	private List<Rate> findRateFromExternalAPI(String coin) {
		List<Rate> ratesList = new ArrayList<>();

		Optional<Rates> rates = exchangeRepository.getCoin(coin);

		rates.get().getRates().entrySet().stream().forEach(entry -> {
			Rate rate = Rate.builder().coin(entry.getKey())
					.value(entry.getValue().setScale(2, BigDecimal.ROUND_HALF_UP)).build();
			ratesList.add(rate);
		});

		return ratesList;
	}

	private Rate getRate(String base, List<Rate> rates) {
		return rates.stream().filter(rate -> rate.getCoin().equals(base)).findAny().orElse(null);
	}

}
