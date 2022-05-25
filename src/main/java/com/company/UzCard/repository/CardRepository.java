package com.company.UzCard.repository;

import com.company.UzCard.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity, String> {
}