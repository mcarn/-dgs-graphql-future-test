package com.example.dgstestjava.graphql;

import com.example.dgstestjava.model.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@DgsComponent
public class ShowsDataFetcher {
  private final List<Show> shows = List.of(
      new Show("Stranger Things", 2016),
      new Show("Ozark", 2017),
      new Show("The Crown", 2016),
      new Show("Dead to Me", 2019),
      new Show("Orange is the New Black", 2013)
  );

  @DgsQuery
  public CompletableFuture<List<Show>> shows(@InputArgument String titleFilter) {
    if (titleFilter == null) {
      return CompletableFuture.supplyAsync(() -> shows);
    }

    return CompletableFuture.supplyAsync(() -> shows.stream()
        .filter(s -> s.getTitle().contains(titleFilter))
        .collect(Collectors.toList()));
  }
}
