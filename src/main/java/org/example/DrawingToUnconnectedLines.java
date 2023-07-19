package org.example;

import java.util.LinkedList;

public class DrawingToUnconnectedLines {

    private final Drawing drawing;

    LinkedList<LineParentPair> linesPerLevel = new LinkedList<>();

    private int enumurator;

    public DrawingToUnconnectedLines(Drawing drawing) {
        this.drawing = drawing;
        addLinesPerLevel(this.drawing, 0);
    }


    void addLinesPerLevel(Drawing d, int parent) {

        linesPerLevel.add(new LineParentPair(parent, new Line(Line.Direction.valueOf(d.direction), d.length, d.visible), ++enumurator));
        var parentId = enumurator;
        for (var child: d.adjacentLine){
            addLinesPerLevel(child, parentId);
        }

    }

    public LinkedList<LineParentPair> getLinesPerLevel() {
        return linesPerLevel;
    }
}
