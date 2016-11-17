package com.androidhive.googleplacesandmaps;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Imtaz on 1/16/2015.
 */
public class result extends Activity {
    TextView finaltext;
    Float net_cal;
    Button diet_btn,ok;
    MediaPlayer mp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showw);
        diet_btn= (Button) findViewById(R.id.button_plan);
        ok= (Button) findViewById(R.id.button8);

        finaltext= (TextView) findViewById(R.id.textViewshow);

        if("Normal".equals(getIntent().getStringExtra("Obstha"))) {
            net_cal=getIntent().getFloatExtra("Total_calorie", -1);
            finaltext.setText("\nYour BMI is " + getIntent().getFloatExtra("Bemai", -1) + ".\n\nU r " + getIntent().getStringExtra("Obstha") + ".\n\nU need to have " + getIntent().getFloatExtra("Total_calorie", -1) + " cal daily.\n\nUr everyday water requirement is  " + getIntent().getIntExtra("cup_water", -1) + " glass.");
        }
        else if("Ovrwght".equals(getIntent().getStringExtra("Obstha"))){
            net_cal= getIntent().getFloatExtra("Total_calorie", -1)-(((getIntent().getFloatExtra("extra_wght",-1))*3500)/30);
            if(net_cal <0)
                net_cal=-net_cal;
            finaltext.setText("\nYour BMI is " + getIntent().getFloatExtra("Bemai", -1) + ".\n\nU r " + getIntent().getStringExtra("Obstha")+" by "+getIntent().getFloatExtra("extra_wght",-1) +" kg.\n\nU need to have " + getIntent().getFloatExtra("Total_calorie", -1) + " cal daily.\n\nUr everyday water requirement is  " + getIntent().getIntExtra("cup_water", -1) + " glass."+net_cal);
        }
        else if("Underwght".equals(getIntent().getStringExtra("Obstha"))){
            net_cal= getIntent().getFloatExtra("Total_calorie", -1)+(((getIntent().getFloatExtra("extra_wght",-1))*3500)/30);

            finaltext.setText("\nYour BMI is " + getIntent().getFloatExtra("Bemai", -1) + ".\n\nU r " + getIntent().getStringExtra("Obstha")+" by "+getIntent().getFloatExtra("extra_wght",-1) +" kg.\n\nU need to have " + getIntent().getFloatExtra("Total_calorie", -1) + " cal daily.\n\nUr everyday water requirement is  " + getIntent().getIntExtra("cup_water", -1) + " glass."+net_cal);
        }
        diet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp= MediaPlayer.create(result.this, R.raw.click);
                mp.start();
                String situation=getIntent().getStringExtra("Obstha");
                Intent diet_plan=new Intent(result.this,regulardiet.class);
                diet_plan.putExtra("NET_CAL",net_cal);
                diet_plan.putExtra("SITUATION",situation);
                startActivity(diet_plan);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent4=new Intent(result.this,MyActivity2.class);
                startActivity(intent4);
            }
        });

    }
}