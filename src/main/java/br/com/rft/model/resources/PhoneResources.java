package br.com.rft.model.resources;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rft.model.domain.Phone;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/phones")
public class PhoneResources {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Phone> add(@Valid @RequestBody Phone phone) {
		log.debug("Phone: " + phone.toString());

		// if (phone != null) {
		// log.info(Constants.TOTAL + result.toString());
		return ResponseEntity.ok(phone);
		// } else {
		// return ResponseEntity.noContent().build();
		// }
	}

}
