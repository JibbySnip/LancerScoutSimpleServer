package com.example.lancerscoutsimpleserver;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProvider;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.lancerscoutsimpleserver.ui.main.SectionsPagerAdapter;
import com.example.lancerscoutsimpleserver.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ScoutingViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ScoutingViewModel.class);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder s = new StringBuilder();
                s.append("High Balls: ");
                s.append(mViewModel.autoHighBalls);
                s.append(" Low Balls: ");
                s.append(mViewModel.autoLowBalls);
                s.append(" Did Taxi: ");
                s.append(mViewModel.didTaxi);
                Snackbar.make(view, s, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
