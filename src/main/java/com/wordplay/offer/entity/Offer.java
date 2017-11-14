package com.wordplay.offer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wordplay.shared.Status;

import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OFFER")
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "PRICE", nullable = false)
	private BigDecimal price;

	@Column(name = "CURRENCY", nullable = false)
	private Currency currency;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	@JsonIgnore
	private Status.OfferStatus status;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "EXPIRY_TIMESTAMP")
	@JsonIgnore
	private Long expiryTimestamp;

	public Offer(){
		this.price = BigDecimal.ZERO;
		this.status = Status.OfferStatus.ACTIVE;
	}

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

	public Long getExpiryTimestamp() {
		return expiryTimestamp;
	}

	public void setExpiryTimestamp(Long expiryTimestamp) {
		this.expiryTimestamp = expiryTimestamp;
	}
}
