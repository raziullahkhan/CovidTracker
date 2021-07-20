package com.example.android.covidcase;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.NetworkRequest;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.webkit.HttpAuthHandler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.Serializable;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;
import java.util.Objects;

public class Splash extends Activity{
    private final int SPLASH_DISPLAY_LENGTH = 500;
    private static  final String COVID="https://corona.lmao.ninja/v2/all?yesterday=true";
    private static final String COUNTRYURL="https://corona.lmao.ninja/v2/countries?yesterday=true&sort=cases";
    private static final String COVIDSTATE="https://akashraj.tech/corona/api_india";
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.splash);

        RegionAsyncTask country=new RegionAsyncTask();
        country.execute(COVID,COUNTRYURL,COVIDSTATE);
    }
    private class RegionAsyncTask extends AsyncTask<String,Void,ArrayList<ArrayList>>{

        @Override
        protected ArrayList<ArrayList> doInBackground(String... urls) {
            if(urls.length<1||urls[0]==null||urls[1]==null||urls[2]==null){
                alertDialog();
                return null;
            }

            Corona corona=Utils.fetchCoronaData(urls[0]);
            ArrayList<Corona> coronas=new ArrayList<>();
            coronas.add(corona);
            ArrayList<Corona> coronaCountry=Utils.fetchCountryCorona(urls[1]);
            ArrayList<CoronaState> coronaState=Utils.fetchStateCorona(urls[2]);
            ArrayList<CoronaDistrict>coronaDistrict= Utils.fetchDistrictCorona(urls[2]);
            ArrayList<ArrayList> result=new ArrayList<ArrayList>();
            result.add(coronas);result.add(coronaCountry);result.add(coronaState);result.add(coronaDistrict);
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<ArrayList> coronas) {
            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/
            if(coronas==null||coronas.get(0)==null||coronas.get(1)==null||
            coronas.get(2)==null||coronas.get(3)==null){
                alertDialog();
                return;
            }
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    startActivity(new Intent(Splash.this, MainActivity.class).putExtra("global",coronas.get(0))
                            .putExtra("country",coronas.get(1))
                            .putExtra("state",coronas.get(2)).putExtra("district",coronas.get(3)));
                    finish();
                }
            }, SPLASH_DISPLAY_LENGTH);

        }
    }
    private void alertDialog(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Can not fetch data!");
        dialog.setTitle("Network Error");
        dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(1);
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
}
