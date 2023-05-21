package com.github.mrvaruntandon.learningdgs.repository;

import com.github.mrvaruntandon.learningdgs.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
