package com.WizardsOfTheCoast.magic.JPA;

import com.WizardsOfTheCoast.magic.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
