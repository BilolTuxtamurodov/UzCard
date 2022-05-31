package com.company.UzCard.repository.custom;

import com.company.UzCard.dto.CardFilterDTO;
import com.company.UzCard.dto.request.CardDTO;
import com.company.UzCard.dto.response.ResponseCardDTO;
import com.company.UzCard.entity.CardEntity;
import com.company.UzCard.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CardCustomRespository {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    @Lazy
    private CardService cardService;

    public List<ResponseCardDTO> filter(CardFilterDTO filter){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT c FROM CardEntity as c");
        if (filter != null){
            sql.append(" where c.visible = true and c.status = '" + filter.getStatus().name() + "'");
        } else {
            sql.append(" where c.status = 'ACTIVE'");
        }

        if (filter.getCardId() != null) {
            sql.append(" AND  c.uuid = '" + filter.getCardId() + "'");
        }
        if (filter.getCardNumber() != null) {
            sql.append(" AND  c.number = " + filter.getCardId());
        }

        if (filter.getFromBalance() != null && filter.getToBalance() != null) {
            sql.append(" AND  c.balance between " + filter.getFromBalance() + " AND " + filter.getToBalance());
        } else if (filter.getFromBalance() != null) {
            sql.append(" AND  c.balance > " + filter.getFromBalance());
        } else if (filter.getToBalance() != null) {
            sql.append(" AND  c.balance < " + filter.getToBalance());
        }

        if (filter.getProfileName() != null) {
            sql.append(" AND  c.profile_name = '" + filter.getProfileName() + "'");
        }

        Query query = entityManager.createQuery(sql.toString(), CardEntity.class);
        List<ResponseCardDTO> cardDTOS = new ArrayList<>();
        List<CardEntity> cardEntities = query.getResultList();
        cardEntities.forEach(entity -> {
            cardDTOS.add(cardService.toDTO(entity));
        });
        return cardDTOS;
    }

}
