package br.com.rft.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import br.com.rft.model.domain.Phone;
import br.com.rft.model.service.RateService;
import br.com.rft.model.service.SmsService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

	private final static String ACCOUNT_SID = "";
	private final static String AUTH_ID = "";
	private static final String TWILIO_NUMBER = "+";
	private static final String SEND_PHONE = "+";
	private static final String SEND_PHONE2 = "+";

	static {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}

	@Autowired
	private RateService service;

	@Override
	@Async
	@Scheduled(cron = "0 0 12 * * ?")
	public String sendSms() {

		List<Phone> phones = new ArrayList<Phone>();

		Phone phoneRogerio = new Phone();
		phoneRogerio.setName("Rogério Fontes");
		phoneRogerio.setPhoneNumber(SEND_PHONE);

		phones.add(phoneRogerio);

		Phone phoneSotero = new Phone();
		phoneSotero.setName("Rafael Sotero");
		phoneSotero.setPhoneNumber(SEND_PHONE2);

		phones.add(phoneSotero);

		String otp = generateOTP();

		String rateMessage = service.findRate("BRL", "USD");
		log.info("Rate: " + rateMessage);

		for (Phone phone : phones) {
			Message.creator(new PhoneNumber(phone.getPhoneNumber()), new PhoneNumber(TWILIO_NUMBER),
					rateMessage + " | OTP: " + otp).create();
		}

		return otp;
	}

	private String generateOTP() {
		return String.valueOf(new Random().nextInt(95509));
	}

}
