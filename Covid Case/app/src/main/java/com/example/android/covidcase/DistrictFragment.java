package com.example.android.covidcase;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class DistrictFragment extends Fragment {
    private CoronaDistrictAdapter adapter;
    private ArrayList<CoronaDistrict> coronaDistrict;
    public DistrictFragment(ArrayList<CoronaDistrict> coronaDistrict){
        this.coronaDistrict=coronaDistrict;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.district,container,false);
        Spinner spinner=(Spinner)rootView.findViewById(R.id.spinner);
        String state="";
        ArrayList <String> spinnerArray=new ArrayList<String>();
        for(int i=0;i<coronaDistrict.size();i++){
            if(state.equalsIgnoreCase(coronaDistrict.get(i).getState())){
                continue;
            }if(!coronaDistrict.get(i).getState().equalsIgnoreCase("State Unassigned")) {
                spinnerArray.add(coronaDistrict.get(i).getState());
            }
            state=coronaDistrict.get(i).getState();
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,
                spinnerArray);
        spinner.setAdapter(arrayAdapter);
        ListView coronaListView=(ListView)rootView.findViewById(R.id.list);
        adapter=new CoronaDistrictAdapter(getActivity(),new ArrayList<CoronaDistrict>());
        coronaListView.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<CoronaDistrict> district=new ArrayList<CoronaDistrict>();
                String s=parent.getItemAtPosition(position).toString();
                for(int i=0;i<coronaDistrict.size();i++){
                    if(coronaDistrict.get(i).getState().equalsIgnoreCase(s)){
                        if(!coronaDistrict.get(i).getDistrict().equalsIgnoreCase("Foreign Evacuees")&&
                                !coronaDistrict.get(i).getDistrict().equalsIgnoreCase("Mumbai Suburban")&&
                        !coronaDistrict.get(i).getDistrict().equalsIgnoreCase("Other State")&&
                        !coronaDistrict.get(i).getDistrict().equalsIgnoreCase("Airport Quarantine")&&
                        !coronaDistrict.get(i).getDistrict().equalsIgnoreCase("Unknown")&&
                        !coronaDistrict.get(i).getDistrict().equalsIgnoreCase("Railway Quarantine")&&
                        !coronaDistrict.get(i).getDistrict().equalsIgnoreCase("BSF Camp")&&
                        !coronaDistrict.get(i).getDistrict().equalsIgnoreCase("Evacuees")&&
                        !coronaDistrict.get(i).getDistrict().equalsIgnoreCase("Italians")&&
                        !coronaDistrict.get(i).getDistrict().equalsIgnoreCase("Other Region")&&
                        !coronaDistrict.get(i).getDistrict().equalsIgnoreCase("State Pool")&&
                        !coronaDistrict.get(i).getDistrict().equalsIgnoreCase("Others")&&
                        !coronaDistrict.get(i).getDistrict().equalsIgnoreCase("CAPF Personnel")) {
                            district.add(coronaDistrict.get(i));
                        }
                    }
                }
                adapter.clear();
                if(district!=null||!district.isEmpty()){
                    adapter.addAll(district);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return rootView;

    }

}