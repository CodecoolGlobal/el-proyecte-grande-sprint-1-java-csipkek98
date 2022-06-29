package com.WizardsOfTheCoast.magic.JPA;

import com.WizardsOfTheCoast.magic.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {
}
