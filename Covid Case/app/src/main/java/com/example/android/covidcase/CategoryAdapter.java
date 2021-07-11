package com.example.android.covidcase;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class CategoryAdapter extends FragmentPagerAdapter {
    private Context context;
    Corona corona;
    ArrayList<Corona> countryCorona;
    ArrayList<CoronaState> stateCorona;
    ArrayList<CoronaDistrict> coronaDistrict;
    public CategoryAdapter(FragmentManager fm, Context context, ArrayList<Corona> corona,ArrayList<Corona> countryCorona,ArrayList<CoronaState> stateCorona,
                           ArrayList<CoronaDistrict> coronaDistrict){
        super(fm);
        this.corona=corona.get(0);
        this.countryCorona=countryCorona;
        this.stateCorona=stateCorona;
        this.coronaDistrict=coronaDistrict;
        this.context=context;    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment(corona);
            case 1:
                return new CountryFragment(countryCorona);
            case 2:
                return new StateFragment(stateCorona);
            case 3:
                return new DistrictFragment(coronaDistrict);
            default:
                return new AboutFragment();
        }
    }


    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "HOME";
        }
        else if(position==1){
            return "COUNTRY";
        }else if(position==2){
            return "STATE";
        }else if(position==3){
            return "DISTRICT";
        }else{
            return "ABOUT";
        }
    }
}
