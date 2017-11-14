package com.wordplay.offer.controller;

import com.wordplay.offer.dto.input.OfferDto;
import com.wordplay.offer.dto.output.ResponseDto;
import com.wordplay.offer.service.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/offer")
public class OfferController {

	private OfferService offerService;

	@Inject
	public OfferController(OfferService offerService){

		this.offerService = offerService;

	}

	@RequestMapping(method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object createOffer(@RequestBody @Valid OfferDto request){

		return offerService.createNewOffer(request);

	}

	@RequestMapping(method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getAvailableOffers(){

		return offerService.getAvailableOffers();

	}

	@RequestMapping(method = RequestMethod.DELETE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object deleteOffer(@RequestParam(name = "id") @NotNull Long offerId){

		offerService.deleteOffer(offerId);

		return new ResponseDto("Deleted");


	}

}
