package com.company.UzCard.repository;

import com.company.UzCard.entity.CardEntity;
import com.company.UzCard.enums.EntityStatus;
import com.company.UzCard.mapper.CardBalanceMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, String> {
    Optional<CardEntity> findByCardNumberAndStatus(String cardNumber, EntityStatus status);
    Optional<CardEntity> findByUuid(String uuid);
    List<CardEntity> findByPhoneAndStatus(String phone, EntityStatus status);
    List<CardEntity> findByClientIdAndStatus(String clientId, EntityStatus status);

    @Query("select c.balance as balance, c.uuid as id from CardEntity as c")
    CardBalanceMapper getBalanceByCardNumber(@Param("cardNum") String cardNum);

    @Modifying
    @Transactional
    @Query("update CardEntity as c set c.status=:status where c.uuid=:id")
    int updateStatus(@Param("status") EntityStatus status,@Param("id") String id);

    @Modifying
    @Transactional
    @Query("update CardEntity as c set c.phone=:phone where c.uuid=:id")
    int updatePhone(@Param("phone") String phone,@Param("id") String id);

    @Modifying
    @Transactional
    @Query("update CardEntity as c set c.balance=:amount where c.cardNumber=:cardNumber")
    int updateAmount(@Param("amount") long amount, @Param("cardNumber") String cardNumber);
}