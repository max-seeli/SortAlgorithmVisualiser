package com.project.sortvisualisations;

import com.project.sortvisualisations.Algorithms.SortAlgorithm;
import com.project.sortvisualisations.Algorithms.SortAlgorithmFactory;

import java.util.Random;

public class SortArray {

    private int[] array;
    private boolean[] arrayHighlighted;
    private int arrayChanges;

    private SortAlgorithm algorithm;

    private SortVisualisationFragment displayingView;

    public SortArray(int elementCount, int algorithmIndex) {
        array = new int[elementCount];
        arrayHighlighted = new boolean[elementCount];
        arrayChanges = 0;

        populateArrays();

        algorithm = SortAlgorithmFactory.getAlgorithmByIndex(algorithmIndex);
    }
    private void populateArrays() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
            arrayHighlighted[i] = false;
        }
    }

    public void resetBarHighlight() {
        for (int i = 0; i < arrayHighlighted.length; i++) {
            arrayHighlighted[i] = false;
        }
    }

    public void shuffle() {
        displayingView.setAlgorithmName("Shuffling...");
        arrayChanges = 0;
        Random rng = new Random();
        for (int i = 0; i < array.length; i++) {
            int swapWithIndex = rng.nextInt(array.length - 1);
            swap(i, swapWithIndex, 50, true);
        }
        arrayChanges = 0;
        resetBarHighlight();
    }

    public void sort() {
        displayingView.setAlgorithmName(algorithm.getAlgorithmName());
        displayingView.setAlgorithmDelay(algorithm.getAlgorithmDelay());
        algorithm.runSort(this);
    }

    public void swap(int firstIndex, int secondIndex, long millisecondDelay, boolean isStep) {

        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;

        arrayHighlighted[firstIndex] = true;
        arrayHighlighted[secondIndex] = true;

        finalizeChange(millisecondDelay, isStep);
    }

    public void updateSingle(int index, int value, long millisecondDelay, boolean isStep) {
        array[index] = value;
        arrayHighlighted[index] = true;

        finalizeChange(millisecondDelay, isStep);
    }

    private void finalizeChange(long delay, boolean isStep) {
        displayingView.invalidate();
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (isStep) {
            arrayChanges++;
            displayingView.setSteps(arrayChanges);
        }
    }

    public int getSize() {
        return array.length;
    }

    public int getValue(int index) {
        return array[index];
    }

    public boolean isHighlighted(int index) {
        return arrayHighlighted[index];
    }

    public void setDisplayingFragment(SortVisualisationFragment displayingView) {
        this.displayingView = displayingView;
    }
}
