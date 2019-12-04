package br.com.rft.model.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rft.model.domain.Rate;
import br.com.rft.model.service.RateService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rates")
public class RateResources {

	@Autowired
	private RateService service;

	@GetMapping
	public ResponseEntity<Rate> getRateByCoin(@RequestParam("base") String base, @RequestParam("coin") String coin) {
		if ((base != null && base.length() > 1) && (coin != null && coin.length() > 1)) {
			Rate rateValue = service.findRate(base, coin);
			log.info(rateValue.toString());
			return ResponseEntity.ok(rateValue);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
