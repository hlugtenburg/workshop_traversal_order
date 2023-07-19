package org.example;

import java.util.LinkedList;

import static org.example.Line.Direction.E;
import static org.example.Line.Direction.N;
import static org.example.Line.Direction.NE;
import static org.example.Line.Direction.NW;
import static org.example.Line.Direction.S;
import static org.example.Line.Direction.SE;
import static org.example.Line.Direction.SW;
import static org.example.Line.Direction.W;

public class Line {


    enum Direction{
            N, NE, E, SE, S, SW, W, NW;
    }

    private final Direction direction;
    private final int length;
    private final boolean visible;
    public LinkedList<Line> lines = new LinkedList<>();

    public Line(Direction direction, int length) {
        this(direction, length, false);
    }
    public Line(Direction direction, int length, boolean visible) {
        this.direction = direction;
        this.length = length;
        this.visible = visible;
    }

    private Coordinate walk(Direction d, Coordinate c) {
        if (d == N) {
            return new Coordinate(c.x, c.y + 1);
        }
        if (d == NE) {
            return new Coordinate(c.x + 1, c.y + 1);
        }
        if (d == Direction.E) {
            return new Coordinate(c.x + 1, c.y);
        }
        if (d == SE) {
            return new Coordinate(c.x + 1, c.y - 1);
        }
        if (d == S) {
            return new Coordinate(c.x, c.y - 1);
        }
        if (d == SW) {
            return new Coordinate(c.x - 1, c.y - 1);
        }
        if (d == W) {
            return new Coordinate(c.x - 1, c.y);
        }
        if (d == NW) {
            return new Coordinate(c.x - 1, c.y + 1);
        }
        return c;
    }

    private char pointChar(Direction d) {
        if (!visible) {
            return ' ';
        }
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

    private Coordinate getEndPoint() {
        if (direction == N) {
            return new Coordinate(0, length);
        }
        if (direction == NE) {
            return new Coordinate(length, length);
        }
        if (direction == E) {
            return new Coordinate(length, 0);
        }
        if (direction == SE) {
            return new Coordinate(length, -length);
        }
        if (direction == S) {
            return new Coordinate(0, -length);
        }
        if (direction == SW) {
            return new Coordinate(-length, -length);
        }
        if (direction == W) {
            return new Coordinate(-length, 0);
        }
        if (direction == NW) {
            return new Coordinate(-length, length);
        }
        return new Coordinate(0,0);
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    @Override
    public String toString() {
        return "Line{" +
                "direction=" + direction +
                ", length=" + length +
                ", visible=" + visible +
                ", lines=" + lines +
                '}';
    }

    public void draw(char[][] coordinateSystem, Coordinate startCoordinate) {
        coordinateSystem[startCoordinate.x][startCoordinate.y] = '+';
        var c = walk(direction, startCoordinate);
        for (int i = 1; i < length; i++) {
            coordinateSystem[c.x][c.y] = pointChar(direction);
            c = walk(direction, c);
        }
        for (var l: lines) {
            l.draw(coordinateSystem, startCoordinate.add(getEndPoint()));
        }
    }
}
