package com.WizardsOfTheCoast.magic.JPA;

import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomCardRepository extends JpaRepository<CustomCardEntity, Long> {
}
