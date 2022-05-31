package com.company.UzCard.repository;

import com.company.UzCard.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

    @Query("select t from TransactionEntity as t where t.fromCard=:card or t.toCard=:card")
    Page<TransactionEntity> getByCard(@Param("card") String card, Pageable pageable);

    Page<TransactionEntity> findByFromCard(String card, Pageable pageable);
    Page<TransactionEntity> findByToCard(String card, Pageable pageable);

}