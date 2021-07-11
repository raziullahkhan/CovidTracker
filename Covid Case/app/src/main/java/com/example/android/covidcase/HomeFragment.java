package com.example.android.covidcase;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    PieChart pieChart;
    PieData pieData;
    List<PieEntry> pieEntryList=new ArrayList<>();
    Corona corona;
    public HomeFragment(Corona corona) {
        // Required empty public constructor
        this.corona=corona;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_home,container,false);
        pieChart=(PieChart)rootView.findViewById(R.id.pie);
        pieChart.setUsePercentValues(true);
        pieEntryList.add(new PieEntry(corona.getTotal(),""));
        pieEntryList.add(new PieEntry(corona.getDeath(),""));
        pieEntryList.add(new PieEntry(corona.getRecovered(),""));
        pieEntryList.add(new PieEntry(corona.getActive(),""));
        PieDataSet pieDataSet=new PieDataSet(pieEntryList,"Cases");
        pieDataSet.setColors(Color.rgb(255,160,0),Color.rgb(211,47,47),
                Color.rgb(76,175,80),Color.rgb(255,87,34));
        pieDataSet.setValueTextSize(0);
        pieData=new PieData(pieDataSet);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setRotationEnabled(false);
        pieChart.setTouchEnabled(false);
        pieChart.getDescription().setText("");
        Legend l=pieChart.getLegend();
        l.setEnabled(false);
        pieChart.setData(pieData);
        TextView tvTotal=(TextView)rootView.findViewById(R.id.tvTotalG);
        TextView tvDeath=(TextView)rootView.findViewById(R.id.tvDeathG);
        TextView tvRecovered=(TextView)rootView.findViewById(R.id.tvRecoveredG);
        TextView tvActive=(TextView)rootView.findViewById(R.id.tvActiveG);
        TextView tvTotalToday=(TextView)rootView.findViewById(R.id.tvTotalT);
        TextView tvDeathToday=(TextView)rootView.findViewById(R.id.tvDeathT);
        TextView tvRecoveredToday=(TextView)rootView.findViewById(R.id.tvRecoveredT);
        tvTotal.setText(String.valueOf(corona.getTotal()));
        tvDeath.setText(String.valueOf(corona.getDeath()));
        tvRecovered.setText(String.valueOf(corona.getRecovered()));
        tvActive.setText(String.valueOf(corona.getActive()));
        tvTotalToday.setText("+"+String.valueOf(corona.getTotalToday()));
        tvDeathToday.setText("+"+String.valueOf(corona.getDeathToday()));
        tvRecoveredToday.setText("+"+String.valueOf(corona.getRecoveredToday()));
        return rootView;
    }
}