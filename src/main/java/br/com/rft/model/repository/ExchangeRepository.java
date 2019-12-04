package br.com.rft.model.repository;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.rft.model.domain.Rates;

@FeignClient(name = "exchange", url = "https://api.exchangeratesapi.io/latest")//?base=USD
public interface ExchangeRepository {

	@RequestMapping(method = RequestMethod.GET)
    Optional<Rates> getCoin(@RequestParam("base") String base);
   
}
