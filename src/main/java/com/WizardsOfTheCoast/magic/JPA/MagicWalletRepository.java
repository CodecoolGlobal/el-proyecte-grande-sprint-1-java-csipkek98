package com.WizardsOfTheCoast.magic.JPA;

import com.WizardsOfTheCoast.magic.entity.MagicWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagicWalletRepository extends JpaRepository<MagicWallet,Long> {
}
