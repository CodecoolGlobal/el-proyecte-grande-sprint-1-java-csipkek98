package com.WizardsOfTheCoast.magic.JPA;

import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("CustomCardRepository")
public interface CustomCardRepository extends JpaRepository<CustomCardEntity, Long> {

    CustomCardEntity findByName(String name);
}
