package com.WizardsOfTheCoast.magic.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="MagicCurrency")
@Table(name = "currency")
public class MagicWallet {

    @Id
    @Column(name = "id")
    private Long id;
    @OneToOne(mappedBy = "currency")
    private User user;
    private int amount;
}
