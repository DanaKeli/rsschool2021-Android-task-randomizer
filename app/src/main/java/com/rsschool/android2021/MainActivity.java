package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnGenerateButtonPressedListener, SecondFragment.OnBackButtonPressedListener {
    private boolean isSecondFragment = false;
    private int previousValue = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment).commit();
        // TODO: invoke function which apply changes of the transaction
        isSecondFragment = false;
    }

    private void openSecondFragment(int min, int max) {
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment).commit();
        // TODO: implement it
        isSecondFragment = true;
    }


    @Override
    public void onGenerateButtonPressed(int min, int max) {
        openSecondFragment(min, max);
    }

    @Override
    public void onBackButtonPressed(int previousNumber) {
        openFirstFragment(previousNumber);
    }

    @Override
    public void onSystemBackButtonPressed(int previousNumber) {
        this.previousValue = previousNumber;
    }

    @Override
    public void onBackPressed() {
        if (isSecondFragment) {
            onBackButtonPressed(previousValue);
        } else {
            super.onBackPressed();
        }
    }
}
