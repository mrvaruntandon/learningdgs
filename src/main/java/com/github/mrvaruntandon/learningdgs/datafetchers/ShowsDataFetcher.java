package com.github.mrvaruntandon.learningdgs.datafetchers;

import com.github.mrvaruntandon.learningdgs.generated.types.Actor;
import com.github.mrvaruntandon.learningdgs.generated.types.Show;
import com.github.mrvaruntandon.learningdgs.repository.ActorRepository;
import com.github.mrvaruntandon.learningdgs.repository.ShowRepository;
import com.netflix.graphql.dgs.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class ShowsDataFetcher {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ActorRepository actorRepository;

    @DgsQuery
    public List<Show> shows(@InputArgument List<String> idsFilter) {
        List<com.github.mrvaruntandon.learningdgs.entity.Show> shows = showRepository.findAllById(
                idsFilter.stream()
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
        );
        List<Show> response = new ArrayList<>();
        for(com.github.mrvaruntandon.learningdgs.entity.Show show : shows){
            response.add(
                    Show.newBuilder()
                    .id(show.getShowId().toString())
                    .title(show.getTitle())
                    .releaseYear(show.getReleaseYear())
                    .build()
            );
        }
        return response;
    }

    @DgsData(parentType = "Show", field = "leadActor")
    public Actor leadActor(DgsDataFetchingEnvironment dfe) {
        return null;
    }
}
