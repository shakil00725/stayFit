package com.androidhive.googleplacesandmaps;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import com.example.STAYFIT.R;


/**
 *
 */
public class MyActivity2 extends Activity {
    /**
     * Called when the activity is first created
     * .
     */

    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button exit;

    MediaPlayer mp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);



        button2= (Button) findViewById(R.id.button);
        button3= (Button) findViewById(R.id.button2);
        button4= (Button) findViewById(R.id.button3);
        button5= (Button) findViewById(R.id.button4);
        //exit= (Button) findViewById(R.id.button44);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp=MediaPlayer.create(MyActivity2.this,R.raw.click);
                mp.start();
                Intent i=new Intent(MyActivity2.this,MyActivity3.class);
                startActivity(i);


            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp=MediaPlayer.create(MyActivity2.this,R.raw.click);
                mp.start();
                Intent i=new Intent(MyActivity2.this,MainActivity.class);
                startActivity(i);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp=MediaPlayer.create(MyActivity2.this,R.raw.click);
                mp.start();
                Intent intent3=new Intent(MyActivity2.this,MyActivity5.class);
                startActivity(intent3);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp=MediaPlayer.create(MyActivity2.this,R.raw.click);
                mp.start();
                Intent intent4=new Intent(MyActivity2.this,MyActivity6.class);
                startActivity(intent4);
            }
        });
//        exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                mp = MediaPlayer.create(MyActivity2.this, R.raw.click);
//                mp.start();
//               // getActivity().finish();
//                System.exit(0);
//
//
//            }
//        });



    }



    @Override
    protected void onDestroy() {
        mp.pause();
        super.onDestroy();

    }
}