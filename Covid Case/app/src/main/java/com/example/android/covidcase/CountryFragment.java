package com.example.android.covidcase;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;


public class CountryFragment extends Fragment {

    private CoronaAdapter adapter;
    ArrayList<Corona> countryCorona;
    //TextView tvTotal,tvDeath,tvRecovered,tvActive,tvTotalToday,tvDeathToday,tvRecoveredToday;
    public CountryFragment(ArrayList<Corona> countryCorona){
        this.countryCorona=countryCorona;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.list,container,false);
        ListView coronaListView=(ListView)rootView.findViewById(R.id.list);
        adapter=new CoronaAdapter(getActivity(),new ArrayList<Corona>());
        coronaListView.setAdapter(adapter);
        adapter.clear();
        if(countryCorona!=null&&!countryCorona.isEmpty()){
            adapter.addAll(countryCorona);
        }
        return rootView;
    }

}