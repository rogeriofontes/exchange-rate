package br.com.rft.model.service;

import br.com.rft.model.domain.Rate;

public interface RateService {
	Rate findRate(String base, String coin); 
	
}
