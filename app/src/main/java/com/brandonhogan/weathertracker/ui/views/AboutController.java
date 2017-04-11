package com.brandonhogan.weathertracker.ui.views;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.brandonhogan.weathertracker.R;

public class AboutController extends Controller {

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_about, container, false);

        if (getActivity() != null)
            getActivity().setTitle(R.string.app_name);

        return view;
    }

}
