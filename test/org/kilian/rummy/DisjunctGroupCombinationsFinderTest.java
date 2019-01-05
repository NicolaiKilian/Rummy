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
    public void testFindCombinationsNoOverlaps() {
        DisjunctGroupCombinationsFinder disjunctGroupCombinationsFinder = new DisjunctGroupCombinationsFinder();

        Set<List<Tile>> groups = new HashSet<>();
        List<Tile> group1 = Arrays.asList(
                new NumberTile(Color.BLACK, 1),
                new NumberTile(Color.GRAY, 1),
                new NumberTile(Color.MAGENTA, 1)
        );
        groups.add(group1);

        List<Tile> group2 = Arrays.asList(
                new NumberTile(Color.MAGENTA, 7),
                new NumberTile(Color.MAGENTA, 8),
                new NumberTile(Color.MAGENTA, 9)
        );
        groups.add(group2);

        Set<Groups> combinations = new HashSet<>(disjunctGroupCombinationsFinder.findCombinations(new Groups(groups)));
        assertEquals(combinations.size(), 1);
        assertTrue(combinations.contains(new Groups(groups)));
    }

    @Test
    public void testFindCombinationsComplex() {
        DisjunctGroupCombinationsFinder disjunctGroupCombinationsFinder = new DisjunctGroupCombinationsFinder();

        Set<List<Tile>> groups = new HashSet<>();
        List<Tile> group1 = Arrays.asList(
                new NumberTile(Color.BLACK, 1),
                new NumberTile(Color.GRAY, 1),
                new NumberTile(Color.MAGENTA, 1)
        );
        groups.add(group1);
        List<Tile> group2 = Arrays.asList(
                new NumberTile(Color.BLACK, 1),
                new NumberTile(Color.BLACK, 2),
                new NumberTile(Color.BLACK, 3)
        );
        groups.add(group2);
        List<Tile> group3 = Arrays.asList(
                new NumberTile(Color.GRAY, 1),
                new NumberTile(Color.GRAY, 2),
                new NumberTile(Color.GRAY, 3)
        );
        groups.add(group3);
        List<Tile> group4 = Arrays.asList(
                new NumberTile(Color.MAGENTA, 1),
                new NumberTile(Color.MAGENTA, 2),
                new NumberTile(Color.MAGENTA, 3),
                new NumberTile(Color.MAGENTA, 4)
        );
        groups.add(group4);
        List<Tile> group5 = Arrays.asList(
                new NumberTile(Color.BLACK, 2),
                new NumberTile(Color.GRAY, 2),
                new NumberTile(Color.MAGENTA, 2)
        );
        groups.add(group5);
        List<Tile> group6 = Arrays.asList(
                new NumberTile(Color.GRAY, 2),
                new NumberTile(Color.GRAY, 3),
                new NumberTile(Color.GRAY, 4),
                new NumberTile(Color.GRAY, 5)
        );
        groups.add(group6);
        // the only group which is disjoint from all others
        List<Tile> group7 = Arrays.asList(
                new NumberTile(Color.MAGENTA, 7),
                new NumberTile(Color.MAGENTA, 8),
                new NumberTile(Color.MAGENTA, 9)
        );
        groups.add(group7);
        Set<Groups> combinations = new HashSet<>(disjunctGroupCombinationsFinder.findCombinations(new Groups(groups)));
        assertEquals(combinations.size(), 4);
        assertTrue(combinations.contains(new Groups(new HashSet<>(Arrays.asList(
                group1, group5, group7
        )))));
        assertTrue(combinations.contains(new Groups(new HashSet<>(Arrays.asList(
                group1, group6, group7
        )))));
        assertTrue(combinations.contains(new Groups(new HashSet<>(Arrays.asList(
                group2, group3, group4, group7
        )))));
        assertTrue(combinations.contains(new Groups(new HashSet<>(Arrays.asList(
                group2, group4, group6, group7
        )))));

    }
}