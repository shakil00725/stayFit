package com.androidhive.googleplacesandmaps;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Imtaz on 1/20/2015.
 */
public class regulardiet extends Activity {
    TextView dietview;
    TextView exercise;
    Button Bok,ok;
    MediaPlayer mp;
    float input,w8,m1,m2,vat=0,dal=0,mas=0,mangsho=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dietshow);
        dietview= (TextView) findViewById(R.id.textView_diet);
        exercise= (TextView) findViewById(R.id.textViewExercs);
        Bok=(Button)findViewById(R.id.button5);
        ok=(Button)findViewById(R.id.button7);
        input=getIntent().getFloatExtra("NET_CAL", -1);
        m1=input-900;
        while(m1>125)
        {
            if(m1>130)
            {
                vat= (float) (vat+0.5);
                m2=130;
            }
            m1=m1-m2;
            if(m1>130 && dal<=100)
            {
                dal=dal+20;
                m2=130;
            }
            m1=m1-m2;
            if(m1>130)
            {
                vat= (float) (vat+0.5);
                m2=130;
            }
            m1=m1-m2;
            if(m1>125)
            {
                mas=mas+50;
                m2=125;

            }
            m1=m1-m2;
            if(m1>130 && mangsho<=500)
            {
                mangsho=mangsho+50;
                m2=130;
            }
            m1=m1-m2;
        }

        {
            if (m1 > 0) {
                dietview.setText("fruits:\nbanana(200g),watermelon(200g),orange(100g),papaya(250g),guava(150g)\nvegetables:\nspinach(200g),lettuce(100g),carrot(240g),cabbage(200g),tomato(180g),cucumber(450g)\nRice: " + vat + " cup\nPulses: " + dal + " g\nFish: " + mas + " g\nMeat: " + mangsho + " g(Chickhen)\nyou can have " + m1 + " claorie as yor wish by the help of caloriechart\nyou can change any item from the diet plan by maintaining daily calorie");
                //printf("you can have "+m1+" claorie as yor wish by the help of caloriechart\n");
            } else {
                dietview.setText("fruits:\nbanana(200g),watermelon(200g),orange(100g),papaya(250g),guava(150g)\nvegetables:\nspinach(200g),lettuce(100g),carrot(240g),cabbage(200g),tomato(180g),cucumber(450g)\nRice: " + vat + " cup\nPulses: " + dal + " g\nFish: " + mas + " g\nMeat: " + mangsho + " g(Chickhen)\nyou can change any item from the diet plan by maintaining daily calorie");
            }
        }

        if("Ovrwght".equals(getIntent().getStringExtra("SITUATION"))){
            exercise.setText("you have to do regular exercise for half an hour everyday.\n");
        }

        Bok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp = MediaPlayer.create(regulardiet.this, R.raw.click);
                mp.start();
                Intent i = new Intent(regulardiet.this, MyActivity6.class);
                startActivity(i);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent4=new Intent(regulardiet.this,MyActivity2.class);
                startActivity(intent4);
            }
        });


    }
}


















