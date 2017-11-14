package com.wordplay.offer.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

import com.wordplay.offer.dto.input.OfferDto;
import com.wordplay.offer.entity.Offer;
import com.wordplay.offer.exception.BadRequestException;
import com.wordplay.offer.exception.NotFoundException;
import com.wordplay.offer.repository.OfferRepository;
import com.wordplay.shared.Status;

import javax.inject.Inject;

import static java.util.Objects.isNull;

@Service
public class OfferService {

	@Value("${offer.expiryDays}")
	private Integer expiryDays;

	private OfferRepository offerRepository;
	private ObjectMapper mapper;

	@Inject
	public OfferService(OfferRepository offerRepository,
	                    ObjectMapper mapper){

		this.offerRepository = offerRepository;
		this.mapper = mapper;

	}

	@Transactional
	public Offer createNewOffer(OfferDto request){

		Offer entity;

		try {

			entity = mapper.convertValue(request, Offer.class);

		}catch (Exception e){

			throw new BadRequestException(e.getLocalizedMessage());

		}

		entity.setExpiryTimestamp(new DateTime().plusDays(expiryDays).getMillis());

		return offerRepository.save(entity);

	}

	@Transactional(readOnly = true)
	public List<Offer> getAvailableOffers(){

		Long now = new DateTime().getMillis();

		return offerRepository.findByStatusAndExpiryTimestampGreaterThan(Status.OfferStatus.ACTIVE, now);

	}

	@Transactional
	public void deleteOffer(Long offerId){

		Long now = new DateTime().getMillis();

		Offer entity = offerRepository.findByIdAndStatusAndExpiryTimestampGreaterThan(offerId, Status.OfferStatus.ACTIVE, now)
			.orElseThrow(() -> new NotFoundException("offerId does not exist"));

		entity.setStatus(Status.OfferStatus.CANCELLED);

		offerRepository.save(entity);

	}
}
