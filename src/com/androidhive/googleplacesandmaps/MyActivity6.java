package com.androidhive.googleplacesandmaps;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by User on 26-Jan-15.
 */
public class MyActivity6 extends Activity {
    TextView TB;
    Button ob;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main6);
        TB= (TextView) findViewById(R.id.textView);
        //ob= (Button) findViewById(R.id.button6);

        TB.setText("Dairy products:\nCow's milk(1 cup)-135 calories\nbutter(100g)-717 calories\nCondensed milk(1cup)-456 calories\nCheese(100g)-371 calories\nYogurt(100g)-59 calories\nPowdered milk(100g)-496 calories\n\nFruits:\nApple(100g)-60 calories\nAlmond(100g)-650 calories\nBanana(100g)-139 calories\ncoconut(100g)-450 calories\nCashew nut(100g)-600 calories\nDates(100g)-317calories\nGrapes(100g)-71 calories\nGuava(150g)-75 calories\nMango(100g)-74 calories\nWatermelon(100g)-30 calories\nOrange(100g)-72 calories\nPear(100g)-75 calories\nPapaya(100g)-32 calories\nPineapple(100g)-46 calories\nPlum(75g)-39 calories\nLitchi(100g)-66 calories\n\nVegetables:\nBeetroot-43 calories\nBitter gaourd(100g)-20 calories\nCarrot(60g)-29 calories\nCabbage(300g)-80 calories\nCapsicum(100g)-24 calories\nCauliflower(500g)-30 calories\nCoriander leaves(100g)-20 calories\nColocasia(75g)-72 calories\nCucumber(150g)-20 calories\nEggplant(100g)-25 calories\nFenugreek leaves(100g)-15 calories\nFreanch beans(100g)-26 calories\nLadies finger(100g)-35 calories\nLettuce(100g)-20 calories\nOnion(100g)-50 calories\nPotato(60g)-26 calories\nRadish(150g)-120 calories\nSpinach(100g)-26 calories\nSweet potato(100g)-120 calories\nTomato(60g)-10 calories\n\nOils:\nSoybean(100g)-763 calories\nMustard(100g)-884 calories\nOlive oil(100g)-884 calories\nSugar(100g)-387 calories\nHoney(100g)-304 calories\n\nRice(1cup)-130 calories\n\nPulses(20g)-130 calories\n\nWheat(100g)-339calories\n\nMeat:\nChicken(100g)-219calories\nBeef(100g)-250 calories\nMutton(100g)-294 calories\nLamb(100g) calories\n\nFishes:\nHilsha fish(100g)-273 calories\nCatfish(100g)-228 calories\nEel(100g)-236 calories\nBarramundi(100g)-92 calories\nCarpfish(100g)-127 calories\nSnapper(100g)-100 calories\nPromfert(100g)-87 calories\nTuna(100g)-184 calories\nCoral(100g)-92 calories\nShrimp(100g)-119 calories\nCrab(100g)-83 calories\n\nbevarages:\nInstant coffee(100g)-353 calories\nBlack tea(100g)-1 calories\n");
//        ob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                Intent intent4=new Intent(MyActivity6.this,MyActivity2.class);
//                startActivity(intent4);
//            }
//        });


    }
}