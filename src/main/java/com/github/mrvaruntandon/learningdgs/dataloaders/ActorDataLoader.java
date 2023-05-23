package com.github.mrvaruntandon.learningdgs.dataloaders;

import com.github.mrvaruntandon.learningdgs.generated.types.Actor;
import com.github.mrvaruntandon.learningdgs.repository.ActorRepository;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@DgsDataLoader(name = "actors")
public class ActorDataLoader implements BatchLoader<String, Actor> {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public CompletionStage<List<Actor>> load(List<String> keys) {
        return CompletableFuture.supplyAsync(() -> {
            List<com.github.mrvaruntandon.learningdgs.entity.Actor> actors = actorRepository.findAllById(
                    keys.stream().map(Integer::parseInt).collect(Collectors.toList())
            );
            List<Actor> actorList = new ArrayList<>();
            for(com.github.mrvaruntandon.learningdgs.entity.Actor actor : actors){
                actorList.add(Actor.newBuilder().id(actor.getActorId().toString()).name(actor.getName()).build());
            }
            return actorList;
        });
    }
}
