package com.WizardsOfTheCoast.magic.JPA;

import com.WizardsOfTheCoast.magic.entity.DeckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckRepository extends JpaRepository<DeckEntity, Long> {
}
