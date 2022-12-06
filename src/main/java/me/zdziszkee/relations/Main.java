package me.zdziszkee.relations;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        final Set<Set<Pair<Integer, Integer>>> allPossibleRelationsForCartesianProduct =
                Relations.getAllPossibleRelationsForCartesianProduct(
                Set.of(1, 2, 3));
        System.out.println(allPossibleRelationsForCartesianProduct.size());
        System.out.println(
                Relations.countTransitiveRelations(allPossibleRelationsForCartesianProduct));
    }

}