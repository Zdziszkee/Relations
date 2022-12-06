package me.zdziszkee.relations;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Relations {

    public static <T> int countTransitiveRelations(Set<Set<Pair<T, T>>> relations) {
        int counter = 0;
        for (final Set<Pair<T, T>> relation : relations) {
            if (isRelationTransitive(relation)) {
                counter++;
            }
        }
        return counter;
    }

    //xRy AND yRz => xRz
    public static <T, V> boolean isRelationTransitive(Set<Pair<T, V>> relation) {
        // Property 1
        if (relation.isEmpty()) {
            return true;
        }
        for (final Pair<T, V> firstPair : relation) {
            final T firstFirst = firstPair.first();
            final V secondFirst = firstPair.second();
            for (final Pair<T, V> secondPair : relation) {
                final T firstSecond = secondPair.first();
                final V secondSecond = secondPair.second();
                if (secondFirst != firstSecond) {
                    continue;
                }
                if (!relation.contains(new Pair<>(firstFirst, secondSecond))) {
                   return false;
                }
            }
        }
        return true;
    }

    public static <T> Set<Set<Pair<T, T>>> getAllPossibleRelationsForCartesianProduct(Set<T> set) {
        return getAllPossibleRelationsForCartesianProduct(set, set);
    }

    //(a)
    //(a)
    //(a,a)
    //-------------
    //(a,b)
    //(a,b)
    //(a,a),(a,b),(b,a),(b,b)
    //---------------------------
    //(a,b,c)
    //(a,b,c)
    //(a,a),(a,b),(a,c),(b,a),(b,b),(b,c),(c,a),(c,b),(c,c)
    ///---------------------------------------
    public static <T, V> Set<Set<Pair<T, V>>> getAllPossibleRelationsForCartesianProduct(Set<T> set1, Set<V> set2) {
        final Set<Pair<T, V>> orderedPairs = new HashSet<>();
        for (final T firstElement : set1) {
            for (final V secondElement : set2) {
                orderedPairs.add(new Pair<>(firstElement, secondElement));
            }
        }
        //good
        return subset(orderedPairs);
    }

    public static <T> Set<Set<T>> subset(Set<T> set) {
        set = Collections.unmodifiableSet(set);
        final Set<Set<T>> subsets = new LinkedHashSet<>();
        final int length = set.size();
        int size = (int) Math.pow(2, length);
        for (int i = 0; i < size; i++) {
            final Set<T> subset = new LinkedHashSet<>();
            final Iterator<T> iterator = set.iterator();
            int j = 0;
            while (iterator.hasNext()) {
                final T next = iterator.next();
                if ((i & (1 << j)) > 0) {
                    subset.add(next);
                }
                j++;
            }
            subsets.add(subset);
        }
        return Collections.unmodifiableSet(subsets);
    }

}
