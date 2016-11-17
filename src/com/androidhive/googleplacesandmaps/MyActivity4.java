package com.androidhive.googleplacesandmaps;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

/**
 * Created by User on 13-Jan-15.
 */
public class MyActivity4 extends Activity {


    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




            }




    @Override
    protected void onDestroy() {
        mp.pause();
        super.onDestroy();
    }
}