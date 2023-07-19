package org.example;

import java.util.LinkedList;

import static org.example.Line.Direction.E;
import static org.example.Line.Direction.N;
import static org.example.Line.Direction.NE;
import static org.example.Line.Direction.S;
import static org.example.Line.Direction.SW;
import static org.example.Line.Direction.W;

public class Line {


    enum Direction{
            N, NE, E, SE, S, SW, W, NW;

    }
    private final Direction direction;
    private final int length;
    private final Coordinate start;

    public LinkedList<Line> lines = new LinkedList<>();

    public Line(Direction direction, int length) {
        this(direction, length, new Coordinate(0,0));
    }
    public Line(Direction direction, int length, Coordinate start) {
        this.direction = direction;
        this.length = length;
        this.start = start;
    }

    public Coordinate walk(Direction d, Coordinate c) {
        if (d == N) {
            return new Coordinate(c.x, c.y + 1);
        }
        if (d == Direction.E) {
            return new Coordinate(c.x + 1, c.y);
        }
        return c;
    }

    public char pointChar(Direction d) {
        if (d == N || d == S){
            return '|';
        }
        if (d == E || d == W) {
            return '-';
        }
        if (d == NE || d == SW) {
            return '/';
        }
        return '\\';
    }

    public Coordinate getEndPoint() {
        if (direction == N) {
            return new Coordinate(0, length);
        }
        if (direction == Direction.E) {
            return new Coordinate(length, 0);
        }
        return new Coordinate(0,0);
    }

    public void addLine(Line line) {
        lines.add(line);
    }


    public char[][] draw(char[][] coordinateSystem, Coordinate startCoordinate) {
        coordinateSystem[startCoordinate.x][startCoordinate.y] = '+';
        var c = walk(direction, startCoordinate);
        for (int i = 1; i < length; i++) {
            coordinateSystem[c.x][c.y] = pointChar(direction);
            c = walk(direction, c);
        }
        for (var l: lines) {
            l.draw(coordinateSystem, getEndPoint());
        }
        return coordinateSystem;
    }
}
