package org.kilian.rummy;

import org.kilian.rummy.material.Tile;

import java.util.List;
import java.util.Set;

class Groups {
    Set<List<Tile>> groups;

    public Groups(Set<List<Tile>> groups) {
        this.groups = groups;
    }

    public Set<List<Tile>> getGroups() {
        return groups;
    }

    public void merge(Groups otherGroups) {
        groups.addAll(otherGroups.getGroups());
    }

    @Override
    public String toString() {
        return "Groups{" +
                groups +
                '}';
    }
}
