package com.project.sortvisualisations;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.project.sortvisualisations.CustomView.DataGraph;

public class SortVisualisationFragment extends Fragment {

    private static final String ELEMENTS_COUNT_KEY = "Number of Elements - Integer";
    private static final String SORT_ALGORITHM = "index number of Sort Algorithm";

    private static final String INVALID_ARGUMENT_TAG = "INVALID ARGUMENT -> NullPointerException";

    private TextView algorithmName;
    private TextView stepsText;
    private TextView algorithmDelay;
    private DataGraph dataGraphView;

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

        algorithmName = (TextView) layout.findViewById(R.id.algorithm_name);
        stepsText = (TextView) layout.findViewById(R.id.step_count);
        algorithmDelay = (TextView) layout.findViewById(R.id.algorithm_delay);
        dataGraphView = (DataGraph) layout.findViewById(R.id.data_graph_view);

        dataGraphView.setData(sortArray);
        sortArray.setDisplayingFragment(this);

        return layout;
    }

    public void shuffle() {
        sortArray.shuffle();
    }

    public void startSorting() {
        sortArray.sort();
    }

    public void setAlgorithmName(final String name) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String baseText = getResources().getString(R.string.algorithm_name);
                algorithmName.setText(String.format("%s %s", baseText, name));
            }
        });
    }

    public void setAlgorithmDelay(final int delay) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String baseText = getResources().getString(R.string.delay);
                algorithmDelay.setText(String.format("%s %s", baseText, delay));
            }
        });
    }

    public void setSteps(final int steps) {
        System.out.println("-------------");
        //TODO: Problem with steps (Going distinct after a while)
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String baseText = getResources().getString(R.string.steps);
                stepsText.setText(String.format("%s %s", baseText, steps));
            }
        });
    }

    public void invalidate() {
        dataGraphView.invalidate();
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
