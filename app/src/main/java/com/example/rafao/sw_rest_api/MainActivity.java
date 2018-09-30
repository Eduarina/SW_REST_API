package com.example.rafao.sw_rest_api;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button b1,b2,b3,b4,b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.Boton1);
        b1.setOnClickListener(this);
        b2 = (Button) findViewById(R.id.Boton2);
        b2.setOnClickListener(this);
        b3 = (Button) findViewById(R.id.Boton3);
        b3.setOnClickListener(this);
        b4 = (Button) findViewById(R.id.Boton4);
        b4.setOnClickListener(this);
        b5 = (Button) findViewById(R.id.Boton5);
        b5.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intento = new Intent(this,SecondActivity.class);
        int boton = -1;
        switch (v.getId()){
            case R.id.Boton1:
                boton = 1;
                break;
            case R.id.Boton2:
                boton = 2;
                break;
            case R.id.Boton3:
                boton = 3;
                break;
            case R.id.Boton4:
                boton = 4;
                break;
            case R.id.Boton5:
                boton = 5;
                break;
        }
        intento.putExtra("boton",boton);
        startActivity(intento);
    }
}




