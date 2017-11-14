package com.wordplay.offer.dto.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wordplay.shared.Status;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonInclude(Include.NON_NULL)
public class OfferDto implements Serializable{

	private Long id;

	@NotNull
	private BigDecimal price;

	@NotNull
	private Currency currency;

	private Status.OfferStatus status;

	@Valid
	private String name;

	@Valid
	private String description;

	public OfferDto(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Status.OfferStatus getStatus() {
		return status;
	}

	public void setStatus(Status.OfferStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
