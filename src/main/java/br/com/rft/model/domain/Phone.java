package br.com.rft.model.domain;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Phone {
	@NotNull
	private String name;
	@NotNull
	private String phoneNumber;
}
