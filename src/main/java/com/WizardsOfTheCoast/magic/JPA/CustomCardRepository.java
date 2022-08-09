package com.WizardsOfTheCoast.magic.JPA;

import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("CustomCardRepository")
public interface CustomCardRepository extends JpaRepository<CustomCardEntity, Long> {

    List<CustomCardEntity>findAllById(Long collectionId);
    CustomCardEntity findCardByName(String name);
}
