package com.eightleaves.examples.splashscreen;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tabbed, container, false);
        ImageView rocketImage= (ImageView) rootView.findViewById(R.id.imageView);
        rocketImage.setImageDrawable(getImageDrawableFromScreen(getArguments().getInt(ARG_SECTION_NUMBER)));
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Drawable getImageDrawableFromScreen(int anInt) {
        HashMap<Integer, Drawable> imageColor = new HashMap<>();
        imageColor.put(1,getContext().getDrawable(R.drawable.rocket_thrust));
        imageColor.put(2,getContext().getDrawable(R.drawable.rocket_thrust1));
        imageColor.put(3,getContext().getDrawable(R.drawable.rocket_thrust2));
        return imageColor.get(anInt);
    }
}
