package com.project.sortvisualisations;

import android.view.View;

import com.project.sortvisualisations.Algorithms.SortAlgorithm;
import com.project.sortvisualisations.Algorithms.SortAlgorithmFactory;
import com.project.sortvisualisations.CustomView.DataGraph;

import java.util.Collections;
import java.util.Random;

public class SortArray {

    private int[] array;
    private boolean[] arrayHighlighted;
    private int arrayChanges;

    private SortAlgorithm algorithm;

    private View dispalyingView;

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

    public void shuffle() {
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
        algorithm.runSort(this);
    }

    private void finaliseChange(long delay, boolean isStep) {
        dispalyingView.invalidate();
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (isStep) arrayChanges++;
    }

    public void swap(int firstIndex, int secondIndex, long millisecondDelay, boolean isStep) {

        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;

        arrayHighlighted[firstIndex] = true;
        arrayHighlighted[secondIndex] = true;

        finaliseChange(millisecondDelay, isStep);
    }

    public void updateSingle(int index, int value, long millisecondDelay, boolean isStep) {
        array[index] = value;
        arrayHighlighted[index] = true;

        finaliseChange(millisecondDelay, isStep);
    }

    public void highlight(int index) {
        arrayHighlighted[index] = true;
    }

    public void resetBarHighlight() {
        for (int i = 0; i < arrayHighlighted.length; i++) {
            arrayHighlighted[i] = false;
        }
    }

    public int getSize() {
        return array.length;
    }

    public int getValue(int index) {
        return array[index];
    }

    public String getAlgorithmName() {
        return algorithm.getAlgorithmName();
    }

    public int getAlgorithmDelay() {
        return algorithm.getAlgorithmDelay();
    }

    public int[] getArray() {
        return array;
    }

    public boolean[] getArrayHighlighted() {
        return arrayHighlighted;
    }

    public void setDisplayingView(DataGraph displayingView) {
        this.dispalyingView = displayingView;
    }
}
