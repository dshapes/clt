package com.techmate.woocommerce.control;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class MyStepperAdapter extends AbstractFragmentStepAdapter {

    String title = "";

    public MyStepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        final StepFragmentSample step = new StepFragmentSample();
        Bundle b = new Bundle();
        b.putInt("step_position", position);
        step.setArguments(b);
        return step;
    }



    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types

        switch (position){
            case 0:
                title = "Product";
                break;
            case 1:
                title = "Shipping";
                break;
            case 2:
                title = "Payment";
                break;
            case 3:
                title = "Success";
                break;
        }

        return new StepViewModel.Builder(context)
                .setTitle(title) //can be a CharSequence instead
                .create();
    }
}