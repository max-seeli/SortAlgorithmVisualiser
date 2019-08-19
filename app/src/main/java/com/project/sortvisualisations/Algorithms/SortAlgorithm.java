package com.project.sortvisualisations.Algorithms;

import com.project.sortvisualisations.SortArray;

import java.util.ArrayList;

public interface SortAlgorithm {

    void runSort(SortArray unsortedArray);

    String getAlgorithmName();

    int getAlgorithmDelay();
}
