package org.kilian.rummy;

import org.kilian.rummy.material.Tile;

import java.util.List;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Groups groups1 = (Groups) o;
        return groups.equals(groups1.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groups);
    }
}
