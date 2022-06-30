package com.WizardsOfTheCoast.magic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
public class OrderEntity {
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
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<CustomCardEntity> orderedCards = new ArrayList<>();
    @OneToOne(mappedBy = "order")
    @JsonIgnore
    private User user;


}
