package com.project.sortvisualisations;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.sortvisualisations.CustomView.DataGraph;

import java.util.Timer;
import java.util.TimerTask;

public class SortVisualisationFragment extends Fragment {

    private static final String ELEMENTS_COUNT_KEY = "Number of Elements - Integer";
    private static final String SORT_ALGORITHM = "index number of Sort Algorithm";

    private static final String INVALID_ARGUMENT_TAG = "INVALID ARGUMENT -> NullPointerException";

    private SortArray sortArray;

    public SortVisualisationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            int elementCount = getArguments().getInt(ELEMENTS_COUNT_KEY);
            int algorithmIndex = getArguments().getInt(SORT_ALGORITHM);
            sortArray = new SortArray(elementCount, algorithmIndex);
        } catch (NullPointerException npe) {
            Log.e(INVALID_ARGUMENT_TAG, "onCreate: arguments were invalid or null", npe);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_sort_visualisation, container, false);
        TextView algorithmName = (TextView) layout.findViewById(R.id.algorithm_name);
        TextView algorithmDelay = (TextView) layout.findViewById(R.id.algorithm_delay);
        final DataGraph dataGraphView = (DataGraph) layout.findViewById(R.id.data_graph_view);

        algorithmName.append(sortArray.getAlgorithmName());
        algorithmDelay.append(String.valueOf(sortArray.getAlgorithmDelay()));

        dataGraphView.setData(sortArray.getArray(), sortArray.getArrayHighlighted());
        sortArray.setDisplayingView(dataGraphView);

        return layout;
    }

    public void shuffle() {
        sortArray.shuffle();
    }

    public void startSorting() {
        sortArray.sort();
    }

    public static SortVisualisationFragment getSortVisualisationFragmentInstance(int elementsCount, int sortAlgorithm) {
        SortVisualisationFragment sortVisualisationFragment = new SortVisualisationFragment();

        Bundle args = new Bundle();
        args.putInt(ELEMENTS_COUNT_KEY, elementsCount);
        args.putInt(SORT_ALGORITHM, sortAlgorithm);
        sortVisualisationFragment.setArguments(args);

        return sortVisualisationFragment;
    }
}
