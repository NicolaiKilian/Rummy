package org.kilian.rummy;

import org.kilian.rummy.material.Color;
import org.kilian.rummy.material.NumberTile;
import org.kilian.rummy.material.Tile;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class GroupResolverTest {

    @Test
    public void testFindGroups() {
        long start = System.currentTimeMillis();
        GroupResolver groupResolver = new GroupResolver();

        for (int i = 1; i <= 3; i++) {
            for (Color color : Color.values()) {
                groupResolver.putNumberTile(new NumberTile(color, i));
            }
        }
//        groupResolver.putNumberTile(new NumberTile(Color.BLACK, 1));
//        groupResolver.putNumberTile(new NumberTile(Color.GRAY, 1));
//        groupResolver.putNumberTile(new NumberTile(Color.MAGENTA, 1));
//        groupResolver.putNumberTile(new NumberTile(Color.GREEN, 1));
//        groupResolver.putNumberTile(new NumberTile(Color.GREEN, 2));
//        groupResolver.putNumberTile(new NumberTile(Color.GREEN, 3));
//        groupResolver.putNumberTile(new NumberTile(Color.GREEN, 4));
//        groupResolver.putNumberTile(new NumberTile(Color.GREEN, 5));

        List<Groups> result = groupResolver.findGroupCombinations();

        System.out.println(System.currentTimeMillis() - start);

        System.out.println(result);
    }

    @Test
    public void testFindGroups2() {
        GroupResolver groupResolver = new GroupResolver();

        groupResolver.putNumberTile(new NumberTile(Color.BLACK, 1));
        groupResolver.putNumberTile(new NumberTile(Color.GRAY, 1));
        groupResolver.putNumberTile(new NumberTile(Color.MAGENTA, 1));
        groupResolver.putNumberTile(new NumberTile(Color.GREEN, 1));
        groupResolver.putNumberTile(new NumberTile(Color.GREEN, 2));
        groupResolver.putNumberTile(new NumberTile(Color.GREEN, 4));
        groupResolver.putNumberTile(new NumberTile(Color.GREEN, 5));

        List<Groups> result = groupResolver.findGroupCombinations();
        assertEquals(result.size(), 5);

    }
}