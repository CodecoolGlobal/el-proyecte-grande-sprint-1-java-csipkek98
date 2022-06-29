package com.WizardsOfTheCoast.magic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "news")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class NewsEntity {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(
            name = "news_sequence",
            sequenceName = "news_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "news_sequence"
    )
    private Long id;
    @Column(name = "publication_date", nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date publicationDate;
    @Column(name =" description", nullable = false)
    private String description;
}
