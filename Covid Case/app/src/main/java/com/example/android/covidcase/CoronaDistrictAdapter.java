package com.example.android.covidcase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CoronaDistrictAdapter extends ArrayAdapter<CoronaDistrict> {
    public CoronaDistrictAdapter(@NonNull Context context, ArrayList<CoronaDistrict> corona) {
        super(context, 0,corona);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem=convertView;
        if(listItem==null){
            listItem= LayoutInflater.from(getContext()).inflate(R.layout.corona_list,parent,false);
        }
        CoronaDistrict corona=getItem(position);
        TextView tvRegion=(TextView)listItem.findViewById(R.id.tvRegion);
        tvRegion.setText(corona.getDistrict());
        TextView totalList=(TextView)listItem.findViewById(R.id.tvTotal);
        totalList.setText(String.valueOf(corona.getTotal()));
        TextView totalListToday=(TextView)listItem.findViewById(R.id.tvTotalToday);
        totalListToday.setText("+"+String.valueOf(corona.getTotalToday()));
        TextView deathList=(TextView)listItem.findViewById(R.id.tvDeath);
        deathList.setText(String.valueOf(corona.getDeath()));
        TextView deathListToday=(TextView)listItem.findViewById(R.id.tvDeathToday);
        deathListToday.setText("+"+String.valueOf(corona.getDeathToday()));
        TextView recoveredList=(TextView)listItem.findViewById(R.id.tvRecovered);
        recoveredList.setText(String.valueOf(corona.getRecovered()));
        TextView recoveredListToday=(TextView)listItem.findViewById(R.id.tvRecoveredToday);
        recoveredListToday.setText("+"+String.valueOf(corona.getRecoveredToday()));
        TextView activeList=(TextView)listItem.findViewById(R.id.tvActive);
        activeList.setText(String.valueOf(corona.getActive()));
        return listItem;
    }
}
