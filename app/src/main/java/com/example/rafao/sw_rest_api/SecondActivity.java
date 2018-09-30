package com.example.rafao.sw_rest_api;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.rafao.sw_rest_api.Fragments.BaseApiFragment;
import com.example.rafao.sw_rest_api.Fragments.FilmsFragment;
import com.example.rafao.sw_rest_api.Fragments.PeopleFragment;
import com.example.rafao.sw_rest_api.Fragments.SpeciesFragment;
import com.example.rafao.sw_rest_api.Fragments.StarshipsFragment;
import com.example.rafao.sw_rest_api.Fragments.VehiclesFragment;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_second);
        int boton = getIntent().getIntExtra("boton",-1);
        switch (boton){
            case 1:
                getSupportFragmentManager()
                        .beginTransaction ()
                        .replace (R.id.rootContainer, new PeopleFragment())
                        .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit ();
                break;
            case 2:
                getSupportFragmentManager()
                        .beginTransaction ()
                        .replace (R.id.rootContainer, new StarshipsFragment())
                        .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit ();
                break;
            case 3:
                getSupportFragmentManager()
                        .beginTransaction ()
                        .replace (R.id.rootContainer, new FilmsFragment())
                        .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit ();
                break;
            case 4:
                getSupportFragmentManager()
                        .beginTransaction ()
                        .replace (R.id.rootContainer, new VehiclesFragment())
                        .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit ();
                break;
            case 5:
                getSupportFragmentManager()
                        .beginTransaction ()
                        .replace (R.id.rootContainer, new SpeciesFragment())
                        .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit ();
                break;
        }



    }

}
