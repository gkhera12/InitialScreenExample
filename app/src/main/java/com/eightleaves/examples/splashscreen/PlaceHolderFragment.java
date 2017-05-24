package com.eightleaves.examples.splashscreen;

import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements Observer{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    TextView textView;
    ImageView rocketImage;
    private NetworkReceiver receiver;
    boolean isActive = false;
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

    @Override
    public void onResume() {
        super.onResume();
        NetworkReceiver.getObservable().addObserver(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        NetworkReceiver.getObservable().deleteObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tabbed, container, false);
        rocketImage= (ImageView) rootView.findViewById(R.id.imageView);
        textView = (TextView) rootView.findViewById(R.id.section_label);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkReceiver();
        this.getContext().registerReceiver(receiver, filter);
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Drawable getImageDrawableFromScreen(int anInt) {
        HashMap<Integer, Drawable> imageColor = new HashMap<>();
        imageColor.put(1,getContext().getDrawable(R.drawable.rocket_thrust));
        imageColor.put(2,getContext().getDrawable(R.drawable.rocket_thrust1));
        imageColor.put(3,getContext().getDrawable(R.drawable.rocket_thrust2));
        imageColor.put(4, getContext().getDrawable(R.drawable.ic_network_error));
        return isActive?imageColor.get(anInt):imageColor.get(4);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregisters BroadcastReceiver when app is destroyed.
        if (receiver != null) {
            this.getContext().unregisterReceiver(receiver);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void update(Observable observable, Object isActive) {
        this.isActive = (boolean) isActive;
        refreshDisplay();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void refreshDisplay() {
        Log.i("Fragment",String.valueOf(isActive));
        if(isActive) {
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        } else {
            textView.setText(getString(R.string.network_error));
        }
        rocketImage.setImageDrawable(getImageDrawableFromScreen(getArguments().getInt(ARG_SECTION_NUMBER)));
    }
}
;