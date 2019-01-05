package org.kilian.rummy;

import org.kilian.rummy.material.Tile;

import java.util.*;

public class DisjunctGroupCombinationsFinder {
    public List<Groups> findCombinations(Groups groups) {
        Set<List<Tile>> origGroups = new HashSet<>(groups.getGroups());

        List<Groups> result = new ArrayList<>();

        MutuallyExcludingGroupsCalculator mutuallyExcludingGroupsCalculator = new MutuallyExcludingGroupsCalculator(origGroups).invoke();

        Set<Set<List<Tile>>> resultSet = new HashSet<>();
        for (List<Tile> group : mutuallyExcludingGroupsCalculator.getMutuallyExcludingGroupSet()) {
            computeAllDisjointCombinations(resultSet, mutuallyExcludingGroupsCalculator.getMutuallyExcludingGroupSet(), mutuallyExcludingGroupsCalculator.getMutuallyExcludingGroupMap(), new HashSet<>(), group);
        }

        @SuppressWarnings("UnnecessaryLocalVariable")
        Set<List<Tile>> originallyDisjointGroups = origGroups;
        originallyDisjointGroups.removeAll(mutuallyExcludingGroupsCalculator.getMutuallyExcludingGroupSet());
        for (Set<List<Tile>> combination : resultSet) {
            combination.addAll(originallyDisjointGroups);
            result.add(new Groups(combination));
        }
        if (mutuallyExcludingGroupsCalculator.getMutuallyExcludingGroupSet().isEmpty()) {
            result.add(new Groups(originallyDisjointGroups));
        }

        return result;
    }

    // in order to build all possible combinations of disjoint groups, we need to
    // 1. remove all incompatible groups of the currently looked-at group from the current combination,
    // 2. go into recursion with every remaining group of the current combination, because order matter,
    // 3. if every group in the working set has already been processed, recursion stops.
    private void computeAllDisjointCombinations(
            Set<Set<List<Tile>>> resultSet,
            Set<List<Tile>> workingSet,
            Map<List<Tile>, Set<List<Tile>>> exclusionMap,
            Set<List<Tile>> alreadyHandled,
            List<Tile> currentGroup) {
        if (alreadyHandled.containsAll(workingSet)) {
            resultSet.add(new HashSet<>(workingSet));
            return;
        }
        if (!alreadyHandled.contains(currentGroup)) {
            Set<List<Tile>> newAlreadyHandled = new HashSet<>(alreadyHandled);
            newAlreadyHandled.add(currentGroup);
            Set<List<Tile>> newWorkingSet = new HashSet<>(workingSet);
            newWorkingSet.removeAll(exclusionMap.get(currentGroup));
            for (List<Tile> groupInNewWorkingSet : newWorkingSet) {
                computeAllDisjointCombinations(resultSet, newWorkingSet, exclusionMap, newAlreadyHandled, groupInNewWorkingSet);
            }
        }
    }


    private class MutuallyExcludingGroupsCalculator {
        private Set<List<Tile>> origGroups;
        private Set<List<Tile>> mutuallyExcludingGroupSet;
        private Map<List<Tile>, Set<List<Tile>>> mutuallyExcludingGroupMap;

        MutuallyExcludingGroupsCalculator(Set<List<Tile>> origGroups) {
            this.origGroups = origGroups;
        }

        Set<List<Tile>> getMutuallyExcludingGroupSet() {
            return mutuallyExcludingGroupSet;
        }

        Map<List<Tile>, Set<List<Tile>>> getMutuallyExcludingGroupMap() {
            return mutuallyExcludingGroupMap;
        }

        MutuallyExcludingGroupsCalculator invoke() {
            mutuallyExcludingGroupSet = new HashSet<>();
            mutuallyExcludingGroupMap = new HashMap<>();
            for (List<Tile> origGroup : origGroups) {
                Set<List<Tile>> excludingGroupsForThisGroup = new HashSet<>();
                for (List<Tile> origGroup2 : origGroups) {
                    if (!origGroup.equals(origGroup2) && !Collections.disjoint(origGroup, origGroup2)) {
                        excludingGroupsForThisGroup.add(origGroup2);
                    }
                }
                if (excludingGroupsForThisGroup.size() > 0) {
                    mutuallyExcludingGroupSet.add(origGroup);
                    mutuallyExcludingGroupSet.addAll(excludingGroupsForThisGroup);
                    mutuallyExcludingGroupMap.put(origGroup, excludingGroupsForThisGroup);
                }
            }
            return this;
        }
    }
}
