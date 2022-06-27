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
@Entity(name ="CustomCard")
public class CustomCardEntity {
    @Id
    @SequenceGenerator(
            name = "custom_card_sequence",
            sequenceName = "custom_card_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "custom_card_sequence"
    )
    @Column(
            name ="id",
            updatable = false,
            nullable = false
    )
    private Long id;

}
