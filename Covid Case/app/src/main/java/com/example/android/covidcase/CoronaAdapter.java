package com.example.android.covidcase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CoronaAdapter extends ArrayAdapter<Corona> {
    public CoronaAdapter(@NonNull Context context, ArrayList<Corona> corona) {
        super(context, 0,corona);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.corona_list,parent,false);
        }
        Corona currentRegion=getItem(position);
        TextView tvRegion=(TextView)listItemView.findViewById(R.id.tvRegion);
        tvRegion.setText(currentRegion.getCountry());
        TextView totalList=(TextView)listItemView.findViewById(R.id.tvTotal);
        totalList.setText(String.valueOf(currentRegion.getTotal()));
        TextView totalListToday=(TextView)listItemView.findViewById(R.id.tvTotalToday);
        totalListToday.setText("+"+String.valueOf(currentRegion.getTotalToday()));
        TextView deathList=(TextView)listItemView.findViewById(R.id.tvDeath);
        deathList.setText(String.valueOf(currentRegion.getDeath()));
        TextView deathListToday=(TextView)listItemView.findViewById(R.id.tvDeathToday);
        deathListToday.setText("+"+String.valueOf(currentRegion.getDeathToday()));
        TextView recoveredList=(TextView)listItemView.findViewById(R.id.tvRecovered);
        recoveredList.setText(String.valueOf(currentRegion.getRecovered()));
        TextView recoveredListToday=(TextView)listItemView.findViewById(R.id.tvRecoveredToday);
        recoveredListToday.setText("+"+String.valueOf(currentRegion.getRecoveredToday()));
        TextView activeList=(TextView)listItemView.findViewById(R.id.tvActive);
        activeList.setText(String.valueOf(currentRegion.getActive()));
        return listItemView;
    }
}
