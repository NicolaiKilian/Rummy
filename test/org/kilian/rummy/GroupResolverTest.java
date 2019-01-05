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
        GroupResolver groupResolver = new GroupResolver();

        groupResolver.putNumberTile(new NumberTile(Color.BLACK, 1));
        groupResolver.putNumberTile(new NumberTile(Color.GRAY, 1));
        groupResolver.putNumberTile(new NumberTile(Color.MAGENTA, 1));
        groupResolver.putNumberTile(new NumberTile(Color.GREEN, 1));
        groupResolver.putNumberTile(new NumberTile(Color.GREEN, 2));
        groupResolver.putNumberTile(new NumberTile(Color.GREEN, 3));
        groupResolver.putNumberTile(new NumberTile(Color.GREEN, 4));
        groupResolver.putNumberTile(new NumberTile(Color.GREEN, 5));

        Set<List<Tile>> result = groupResolver.findGroups().getGroups();
        assertEquals(result.size(), 11);
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.BLACK, 1),
                new NumberTile(Color.GRAY, 1),
                new NumberTile(Color.GREEN, 1)
        )));
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.BLACK, 1),
                new NumberTile(Color.GRAY, 1),
                new NumberTile(Color.MAGENTA, 1)
        )));
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.BLACK, 1),
                new NumberTile(Color.GREEN, 1),
                new NumberTile(Color.MAGENTA, 1)
        )));
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.GRAY, 1),
                new NumberTile(Color.GREEN, 1),
                new NumberTile(Color.MAGENTA, 1)
        )));
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.BLACK, 1),
                new NumberTile(Color.GRAY, 1),
                new NumberTile(Color.GREEN, 1),
                new NumberTile(Color.MAGENTA, 1)
        )));

        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.GREEN, 1),
                new NumberTile(Color.GREEN, 2),
                new NumberTile(Color.GREEN, 3),
                new NumberTile(Color.GREEN, 4),
                new NumberTile(Color.GREEN, 5)
        )));
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.GREEN, 1),
                new NumberTile(Color.GREEN, 2),
                new NumberTile(Color.GREEN, 3),
                new NumberTile(Color.GREEN, 4)
        )));
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.GREEN, 2),
                new NumberTile(Color.GREEN, 3),
                new NumberTile(Color.GREEN, 4),
                new NumberTile(Color.GREEN, 5)
        )));
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.GREEN, 1),
                new NumberTile(Color.GREEN, 2),
                new NumberTile(Color.GREEN, 3)
        )));
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.GREEN, 2),
                new NumberTile(Color.GREEN, 3),
                new NumberTile(Color.GREEN, 4)
                )));
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.GREEN, 3),
                new NumberTile(Color.GREEN, 4),
                new NumberTile(Color.GREEN, 5)
        )));

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

        Set<List<Tile>> result = groupResolver.findGroups().getGroups();
        assertEquals(result.size(), 5);
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.BLACK, 1),
                new NumberTile(Color.GRAY, 1),
                new NumberTile(Color.GREEN, 1)
        )));
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.BLACK, 1),
                new NumberTile(Color.GRAY, 1),
                new NumberTile(Color.MAGENTA, 1)
        )));
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.BLACK, 1),
                new NumberTile(Color.GREEN, 1),
                new NumberTile(Color.MAGENTA, 1)
        )));
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.GRAY, 1),
                new NumberTile(Color.GREEN, 1),
                new NumberTile(Color.MAGENTA, 1)
        )));
        assertTrue(result.contains(Arrays.asList(
                new NumberTile(Color.BLACK, 1),
                new NumberTile(Color.GRAY, 1),
                new NumberTile(Color.GREEN, 1),
                new NumberTile(Color.MAGENTA, 1)
        )));
    }
}