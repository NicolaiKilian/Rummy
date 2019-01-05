package org.kilian.rummy;

import org.kilian.rummy.material.Color;
import org.kilian.rummy.material.NumberTile;
import org.testng.annotations.Test;

public class RowGroupFinderTest {

    @Test
    public void testFindAllRowGroupsIgnoringOverlaps() {
        GroupResolver groupResolver = new GroupResolver();
        groupResolver.putNumberTile(new NumberTile(Color.BLACK, 1));
        groupResolver.putNumberTile(new NumberTile(Color.BLACK, 2));
        groupResolver.putNumberTile(new NumberTile(Color.BLACK, 3));
        groupResolver.putNumberTile(new NumberTile(Color.BLACK, 4));
        groupResolver.putNumberTile(new NumberTile(Color.BLACK, 5));
        groupResolver.putNumberTile(new NumberTile(Color.BLACK, 6));
        groupResolver.putNumberTile(new NumberTile(Color.BLACK, 7));
        groupResolver.putNumberTile(new NumberTile(Color.GRAY, 1));

        RowGroupFinder rowGroupFinder = new RowGroupFinder();
        Groups allRowGroupsIgnoringOverlaps = rowGroupFinder.findAllRowGroupsIgnoringOverlaps(groupResolver.tileMatrix);
        System.out.println(allRowGroupsIgnoringOverlaps);
    }
}