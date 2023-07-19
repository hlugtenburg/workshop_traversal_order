package org.example;

public class LineParentPair implements Comparable<LineParentPair> {
    public final int parent;
    public final Line line;
    public final int id;
    public LineParentPair(int parent, Line line, int id){
        this.parent = parent;

        this.line = line;
        this.id = id;
    }

    @Override
    public String toString() {
        return "LineParentPair{" +
                "parent=" + parent +
                ", line=" + line +
                ", id=" + id +
                '}';
    }

    @Override
    public int compareTo(LineParentPair lineParentPair) {
        return Integer.compare(lineParentPair.parent, parent);
    }
}
