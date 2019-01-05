package org.kilian.rummy;

import org.kilian.rummy.material.Color;
import org.kilian.rummy.material.Constants;
import org.kilian.rummy.material.NumberTile;
import org.kilian.rummy.material.Tile;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class UtilsTest {

    @Test
    public void testCollectCombinations() {
        Set<List<Tile>> combinations = Utils.collectCombinations(
                new Tile[]{
                        new NumberTile(Color.BLACK, 2),
                        new NumberTile(Color.MAGENTA, 2),
                        new NumberTile(Color.GRAY, 2),
                        new NumberTile(Color.GREEN, 2)
                },
                3);

        System.out.println(combinations);
    }

    @Test
    public void testCalculateSubGroupsWithoutCoherence() {
        List<Tile> testGroup = Arrays.asList(new Tile[]{
                new NumberTile(Color.BLACK, 2),
                new NumberTile(Color.MAGENTA, 2),
                new NumberTile(Color.GRAY, 2),
                new NumberTile(Color.GREEN, 2)
        });
        Set<List<Tile>> groups = Utils.calculateSubGroupsWithoutCoherence(testGroup, Constants.MIN_GROUP_SIZE);
        System.out.println(groups);
    }
}