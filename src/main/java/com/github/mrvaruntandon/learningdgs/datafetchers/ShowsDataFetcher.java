package com.github.mrvaruntandon.learningdgs.datafetchers;

import com.github.mrvaruntandon.learningdgs.dataloaders.ActorDataLoader;
import com.github.mrvaruntandon.learningdgs.generated.types.Actor;
import com.github.mrvaruntandon.learningdgs.generated.types.Show;
import com.github.mrvaruntandon.learningdgs.repository.ActorRepository;
import com.github.mrvaruntandon.learningdgs.repository.ShowRepository;
import com.netflix.graphql.dgs.*;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@DgsComponent
public class ShowsDataFetcher {

    @Autowired
    private ShowRepository showRepository;

    @DgsQuery
    public List<Show> shows(@InputArgument List<String> idsFilter) {
        List<com.github.mrvaruntandon.learningdgs.entity.Show> shows = showRepository.findAllById(
                idsFilter.stream()
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
        );
        List<Show> response = new ArrayList<>();
        for(com.github.mrvaruntandon.learningdgs.entity.Show show : shows){
            Actor actor = Actor.newBuilder().id(String.valueOf(show.getLeadActorId())).build();
            response.add(
                    Show.newBuilder()
                    .id(show.getShowId().toString())
                    .title(show.getTitle())
                    .releaseYear(show.getReleaseYear())
                    .leadActor(actor)
                    .build()
            );
        }
        return response;
    }

    //Child Data Fetcher
    @DgsData(parentType = "Show", field = "leadActor")
    public CompletableFuture<Actor> leadActor(DgsDataFetchingEnvironment dfe) {
        Show show = dfe.getSource();
        DataLoader<String, Actor> dataLoader = dfe.getDataLoader("actors");
        return dataLoader.load(show.getLeadActor().getId());
    }
}
