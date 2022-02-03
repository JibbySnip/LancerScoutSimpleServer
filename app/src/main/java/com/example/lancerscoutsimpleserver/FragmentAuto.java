package com.example.lancerscoutsimpleserver;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAuto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAuto extends Fragment {

    private ScoutingViewModel mViewModel;

    public FragmentAuto() {
        // Required empty public constructor
    }

    public static FragmentAuto newInstance(String param1, String param2) {
        FragmentAuto fragment = new FragmentAuto();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auto, container, false);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ScoutingViewModel.class);

        initializeSpinners(view);
        initializeCheckbox(view);

        return view;

    }

    private void initializeCheckbox(View view) {
        final CheckBox didTaxi = (CheckBox) view.findViewById(R.id.taxied_checkbox);
        didTaxi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mViewModel.didTaxi = isChecked;
            }
        });
    }

    private void initializeSpinners(View view) {
        Spinner highScored = (Spinner) Objects.requireNonNull(view).findViewById(R.id.num_balls_shot_top_auto);
        ArrayAdapter adapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_item,
                Collections.singletonList(IntStream.range(0, 8 + 1).toArray()));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        highScored.setAdapter(adapter);
        highScored.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.autoHighBalls = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mViewModel.autoHighBalls = 0;
            }
        });

        Spinner lowScored = (Spinner) Objects.requireNonNull(view).findViewById(R.id.num_balls_shot_low_auto);
        lowScored.setAdapter(adapter);
        lowScored.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.autoLowBalls = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mViewModel.autoLowBalls = 0;
            }
        });
    }
}