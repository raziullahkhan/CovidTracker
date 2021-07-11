package com.example.android.covidcase;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class StateFragment extends Fragment {
    //private static final String COVIDURL="https://api.covid19india.org/v2/state_district_wise.json";
    private CoronaStateAdapter adapter;
    private ArrayList<CoronaState> stateCorona;
    public StateFragment(ArrayList<CoronaState> stateCorona){
        this.stateCorona=stateCorona;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.list,container,false);
        ListView coronaListView=(ListView)rootView.findViewById(R.id.list);
        adapter=new CoronaStateAdapter(getActivity(),new ArrayList<CoronaState>());
        coronaListView.setAdapter(adapter);
        adapter.clear();
        if(stateCorona!=null&&!stateCorona.isEmpty()){
            adapter.addAll(stateCorona);
        }
        return rootView;
    }

}