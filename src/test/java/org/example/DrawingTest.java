package org.example;

import static org.assertj.core.api.Assertions.assertThat;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DrawingTest {
    static List<LineParentPair> lineParentPairs;

    static String[] expectedDrawing =
           {
            "                                        " ,
            "                                        " ,
            "                                        " ,
            "                                        " ,
            "                                        " ,
            "                                        " ,
            "                                        " ,
            "                                        " ,
            "                                        " ,
            "               +                        " ,
            "              / \\                       " ,
            "             /   \\                      " ,
            "            /     \\                     " ,
            "           /       \\                    " ,
            "          /         \\                   " ,
            "         /           \\                  " ,
            "        /             \\                 " ,
            "       /               \\                " ,
            "      /                 \\               " ,
            "     +-------------------+              " ,
            "     |                   |              " ,
            "     |                   |              " ,
            "     |                   |              " ,
            "     |                   |              " ,
            "     |                   |              " ,
            "     |                   |              " ,
            "     |                   |              " ,
            "     |                   |              " ,
            "     |                   |              " ,
            "+    +-------------------+              "} ;
    @BeforeAll
    static void beforeAll() throws IOException {
        var drawing = parse();
        var x = new DrawingToUnconnectedLines(drawing);
        lineParentPairs = x.getLinesPerLevel().stream().sorted().collect(Collectors.toList());
    }

    static Drawing parse() throws IOException {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        var tree = objectMapper.readTree(
                Drawing.class.getResourceAsStream("/house.json"));

        return objectMapper.treeToValue(tree, Drawing.class);
    }

    @Test
    void basic() throws IOException {
        var drawing = parse();
        var line = Factory.addLines(drawing);
        var consolePaint = new ConsolePaint(40,30, line);
        assertThat(consolePaint.equals(convertTo2dCharArray(expectedDrawing))).isTrue();
        consolePaint.draw();

    }


    // A Line has a direction, length and a list (0 or more elements) of lines that need to be drawn from the end of the line.
    // This is a tree structure, similar to src/main/resources/house.json

    // In this exercise, the lines are retrieved starting from the leaf nodes going toward the root.
    // The lines do not reference each other. Each LineParentPair gives a unique id to each line, and the id of the parent.
    // If 2 adjacent lines are created, the parent can call `addLine(child)`.
    // Except for the root line, for each line 1 call needs to be made to `addLine()`

    // The exercise is to correctly do the calls to addLine.

    @Test
    void exercise() throws IOException {
        // Hint, the lines that aren't added to a parent, need to be buffered
        TreeMap<Integer, Line> orphanedLines = new TreeMap<>();

        Line result = null;
        while (true) {
            var lineParentPair = getNextElement();
            if (lineParentPair == null) {
                // Set result to the root Line
                break;
            }
            System.out.println(lineParentPair);
            // Build the tree in the right order here....
        }

        if (false) {
            var drawing = parse();
            result = Factory.addLines(drawing);
        }

        var consolePaint = new ConsolePaint(40,30, result);
        assertThat(consolePaint.equals(convertTo2dCharArray(expectedDrawing))).isTrue();

        consolePaint.draw();
    }

    LineParentPair getNextElement() {
        if (lineParentPairs.isEmpty()) {
            return null;
        }
        var returnVal = lineParentPairs.get(0);
        lineParentPairs.remove(0);
        return returnVal;
    }

    static char[][] convertTo2dCharArray(String[] in) {
        var retVal = new char[in[0].length()][in.length];
        for (int i=0; i < in.length; i++){
            for (int j=0; j < in[0].length(); j++) {
                // static String[] expectedDrawing is 90 deg turned and mirrored compared to the alignment of the
                // coordinateSystem in ConsolePaint. With the magic below it's compensated.
                retVal[j][in.length - 1 - i] = in[i].toCharArray()[j];
            }
        }
        return retVal;
    }
}
