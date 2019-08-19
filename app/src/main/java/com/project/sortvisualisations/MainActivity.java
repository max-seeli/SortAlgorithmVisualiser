package com.project.sortvisualisations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SortVisualisationFragment visualisationFragment = SortVisualisationFragment.getSortVisualisationFragmentInstance(200, 0);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container, visualisationFragment).commit();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                visualisationFragment.shuffle();
                visualisationFragment.startSorting();
            }
        }, 2000);

    }
}
