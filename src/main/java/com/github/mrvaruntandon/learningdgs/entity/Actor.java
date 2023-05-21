package com.github.mrvaruntandon.learningdgs.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Actor")
@Getter
@Setter
@NoArgsConstructor
public class Actor {
    @Id
    @Column(name = "actorId")
    private Integer actorId;

    @Column(name = "name", nullable = false)
    private String name;
}

