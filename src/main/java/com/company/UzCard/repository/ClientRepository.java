package com.company.UzCard.repository;

import com.company.UzCard.entity.ClientEntity;
import com.company.UzCard.enums.EntityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, String> {
    Optional<ClientEntity> findByPhoneAndStatus(String phone, EntityStatus status);
    Page<ClientEntity> findByProfileUserName(String profileUserName, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update ClientEntity as c set c.status=:status where c.uuid=:id")
    int updateStatus(@Param("status") EntityStatus status, @Param("id") String id);

    @Modifying
    @Transactional
    @Query("update ClientEntity as c set c.name=:name, c.surname=:surname where c.uuid=:id")
    int update(@Param("name") String name,@Param("surname") String surname, @Param("id") String id);

    @Modifying
    @Transactional
    @Query("update ClientEntity as c set c.phone=:phone where c.uuid=:id")
    int updatePhone(@Param("phone") String phone, @Param("id") String id);
}