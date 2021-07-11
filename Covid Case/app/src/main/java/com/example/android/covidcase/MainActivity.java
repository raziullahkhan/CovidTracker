package com.example.android.covidcase;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.ParcelableSpan;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        ArrayList<Corona> corona=(ArrayList<Corona>)intent.getSerializableExtra("global");
        ArrayList<Corona> coronaCountry=(ArrayList<Corona>)(intent.getSerializableExtra("country"));
        ArrayList<CoronaState> coronaState=(ArrayList<CoronaState>)intent.getSerializableExtra("state") ;
        ArrayList<CoronaDistrict> coronaDistrict=(ArrayList<CoronaDistrict>)intent.getSerializableExtra("district");
        ViewPager viewPager=(ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(5);
        CategoryAdapter adapter=new CategoryAdapter(getSupportFragmentManager(),this,corona,coronaCountry,coronaState,coronaDistrict);
        viewPager.setAdapter(adapter);
        TabLayout tab=(TabLayout)findViewById(R.id.tabs);
        tab.setupWithViewPager(viewPager);

    }

}