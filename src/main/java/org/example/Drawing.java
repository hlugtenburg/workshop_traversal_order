package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Drawing {
    String direction;
    Integer length;
    Boolean visible;
    List<Drawing> adjacentLine;

    @JsonCreator
    public Drawing(
            @JsonProperty("direction") String direction,
            @JsonProperty("length") int length,
            @JsonProperty("visible") boolean visible,
            @JsonProperty("adjacentLine") List<Drawing> adjacentLine) {
        this.direction = direction;
        this.length = length;
        this.visible = visible;
        this.adjacentLine = adjacentLine;
    }

}
