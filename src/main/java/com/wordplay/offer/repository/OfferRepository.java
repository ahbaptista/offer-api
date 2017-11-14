package com.wordplay.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.wordplay.offer.entity.*;
import com.wordplay.shared.Status;

public interface OfferRepository extends JpaRepository<Offer, Long> {

	List<Offer> findByStatusAndExpiryTimestampGreaterThan(Status.OfferStatus status, Long expiryTimestamp);

	Optional<Offer> findByIdAndStatusAndExpiryTimestampGreaterThan(Long id, Status.OfferStatus status, Long expiryTimestamp);
}
