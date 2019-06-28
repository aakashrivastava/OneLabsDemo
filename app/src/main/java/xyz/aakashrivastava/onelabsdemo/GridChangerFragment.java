package xyz.aakashrivastava.onelabsdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.ContentValues.TAG;


public class GridChangerFragment extends DialogFragment implements View.OnClickListener{

    SharedPreferenceManager sharedPreferenceManager;
    public int lol = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_grid_changer, container, false);
        sharedPreferenceManager = new SharedPreferenceManager(getContext());
        return rootView;
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.twotwo:
                lol = 2;
                sharedPreferenceManager.writeGridSpanSize(2);
                Log.d(TAG, "onClick: " + sharedPreferenceManager.readGridSpanSize());
                break;
            case R.id.threethree:
                lol = 3;
                sharedPreferenceManager.writeGridSpanSize(3);
                Log.d(TAG, "onClick: " + sharedPreferenceManager.readGridSpanSize());
                break;
            case R.id.fourfour:
                lol = 4;
                sharedPreferenceManager.writeGridSpanSize(4);
                Log.d(TAG, "onClick: " + sharedPreferenceManager.readGridSpanSize());
                break;
        }
    }

    public int returnGridSpan() {
        return lol;
    }
}
