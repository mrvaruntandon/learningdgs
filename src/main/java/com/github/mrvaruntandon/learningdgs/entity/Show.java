package com.github.mrvaruntandon.learningdgs.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Show")
@Getter
@Setter
@NoArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "showId")
    private Integer showId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "releaseYear")
    private Integer releaseYear;

    @Column(name = "leadActorId")
    private Integer leadActorId;
}

