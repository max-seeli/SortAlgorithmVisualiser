package com.project.sortvisualisations.Algorithms;

import com.project.sortvisualisations.SortArray;

public class InsertionSort implements SortAlgorithm {

    private static final String ALGORITHM_NAME = "InsertionSort";
    private static final int ALGORITHM_DELAY = 5;

    @Override
    public void runSort(SortArray unsortedArray) {
        for (int i = 0; i < unsortedArray.getSize(); i++) {
            int key = unsortedArray.getValue(i);
            int j = i - 1;
            while (j >= 0 && unsortedArray.getValue(j) > key) {
                unsortedArray.updateSingle(j + 1, unsortedArray.getValue(j), getAlgorithmDelay(), true);
                j--;
                unsortedArray.resetBarHighlight();
            }
            unsortedArray.updateSingle(j + 1, key, getAlgorithmDelay(), true);
        }
    }

    @Override
    public String getAlgorithmName() {
        return ALGORITHM_NAME;
    }

    @Override
    public int getAlgorithmDelay() {
        return ALGORITHM_DELAY;
    }
}
