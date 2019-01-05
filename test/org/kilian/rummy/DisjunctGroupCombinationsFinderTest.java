package org.kilian.rummy;

import org.kilian.rummy.material.Color;
import org.kilian.rummy.material.NumberTile;
import org.kilian.rummy.material.Tile;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class DisjunctGroupCombinationsFinderTest {

    @Test
    public void testFindCombinations() {
        DisjunctGroupCombinationsFinder disjunctGroupCombinationsFinder = new DisjunctGroupCombinationsFinder();

        Set<List<Tile>> groups = new HashSet<>();
        groups.add(Arrays.asList(
                new NumberTile(Color.BLACK, 1),
                new NumberTile(Color.GRAY, 1),
                new NumberTile(Color.MAGENTA, 1)
        ));
        groups.add(Arrays.asList(
                new NumberTile(Color.BLACK, 1),
                new NumberTile(Color.BLACK, 2),
                new NumberTile(Color.BLACK, 3)
        ));
        groups.add(Arrays.asList(
                new NumberTile(Color.GRAY, 1),
                new NumberTile(Color.GRAY, 2),
                new NumberTile(Color.GRAY, 3)
        ));
        groups.add(Arrays.asList(
                new NumberTile(Color.MAGENTA, 1),
                new NumberTile(Color.MAGENTA, 2),
                new NumberTile(Color.MAGENTA, 3),
                new NumberTile(Color.MAGENTA, 4)
        ));
        groups.add(Arrays.asList(
                new NumberTile(Color.BLACK, 2),
                new NumberTile(Color.GRAY, 2),
                new NumberTile(Color.MAGENTA, 2)
        ));
        groups.add(Arrays.asList(
                new NumberTile(Color.GRAY, 2),
                new NumberTile(Color.GRAY, 3),
                new NumberTile(Color.GRAY, 4),
                new NumberTile(Color.GRAY, 5)
        ));
        groups.add(Arrays.asList(
                new NumberTile(Color.MAGENTA, 7),
                new NumberTile(Color.MAGENTA, 8),
                new NumberTile(Color.MAGENTA, 9)
        ));
        System.out.println(disjunctGroupCombinationsFinder.findCombinations(new Groups(groups)));
    }
}