package com.WizardsOfTheCoast.magic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long id;
    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<CustomCardEntity> cards = new ArrayList<>();


}
