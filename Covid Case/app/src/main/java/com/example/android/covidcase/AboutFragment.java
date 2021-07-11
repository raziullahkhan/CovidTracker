package com.example.android.covidcase;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutFragment extends Fragment {
    TextView tvVersion,tvNovel,tvAkash,tvContact;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_about,container,false);
       tvVersion=(TextView)view.findViewById(R.id.tvVersion);
       tvAkash=(TextView)view.findViewById(R.id.tvAkash);
       tvAkash.setPaintFlags(tvAkash.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
       tvNovel=(TextView)view.findViewById(R.id.tvNovel);
       tvNovel.setPaintFlags(tvNovel.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
       tvContact=(TextView)view.findViewById(R.id.tvContact);
       tvContact.setPaintFlags(tvContact.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
        PackageManager pm=getActivity().getPackageManager();
        String pkgName=getActivity().getPackageName();
        PackageInfo pkgInfo=null;
        try{
            pkgInfo=pm.getPackageInfo(pkgName,0);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        String ver=pkgInfo.versionName;
        tvVersion.setText(tvVersion.getText()+ver);
        tvNovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri coronaUri=Uri.parse("https://github.com/NovelCOVID/API/");
                Intent websiteIntent=new Intent(Intent.ACTION_VIEW,coronaUri);
                startActivity(websiteIntent);
            }
        });
        tvAkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri coronaUri=Uri.parse("https://rapidapi.com/spamakashrajtech/api/corona-virus-world-and-india-data/");
                Intent websiteIntent=new Intent(Intent.ACTION_VIEW,coronaUri);
                startActivity(websiteIntent);
            }
        });
        tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Intent intent=new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto","raziullahkhan2222@gmail.com",null));
                intent.putExtra(Intent.EXTRA_SUBJECT,"CovidTracker");
                getContext().startActivity(Intent.createChooser(intent,null));
            }
        });
        return view;
    }
}