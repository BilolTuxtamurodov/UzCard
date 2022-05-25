package com.company.UzCard.repository;

import com.company.UzCard.entity.CardEntity;
import com.company.UzCard.enums.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, String> {
    Optional<CardEntity> findByCardNumberAndStatus(String cardNumber, EntityStatus status);
}