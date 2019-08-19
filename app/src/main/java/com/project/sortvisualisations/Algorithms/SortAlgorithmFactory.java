package com.project.sortvisualisations.Algorithms;

public class SortAlgorithmFactory {
    public static SortAlgorithm getAlgorithmByIndex(int index) {
        switch (index) {
            case 0:
                return new InsertionSort();
            default:
                return null;
        }
    }
}
