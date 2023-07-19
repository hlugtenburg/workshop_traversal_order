package org.example;

public class Factory {

    static public Line createSingleLine(Drawing d) {
        return new Line(Line.Direction.valueOf(d.direction), d.length, d.visible);
    }

    static public Line addLines(Drawing d) {
        var line = createSingleLine(d);
        for (var adjacent: d.adjacentLine) {
            line.addLine(addLines(adjacent));
        }
        return line;
    }
}
