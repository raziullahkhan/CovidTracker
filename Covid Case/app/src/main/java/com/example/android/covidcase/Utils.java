package com.example.android.covidcase;

import android.net.UrlQuerySanitizer;
import android.text.TextUtils;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

public final class Utils {
    private static ArrayList<CoronaState> extractStateJson(String coronaJSON){
        if(TextUtils.isEmpty(coronaJSON)){
            return null;
        }
        ArrayList<CoronaState> corona=new ArrayList<>();
        try {
            JSONObject jsonBase = new JSONObject(coronaJSON);
            JSONObject jsonState = jsonBase.getJSONObject("state_wise");
            Iterator<String> keys = jsonState.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if (jsonState.get(key) instanceof JSONObject) {
                    String state = key;
                    if(!state.equalsIgnoreCase("State Unassigned")) {
                        JSONObject json = jsonState.getJSONObject(key);
                        long active = json.getLong("active");
                        long total = json.getLong("confirmed");
                        long death = json.getLong("deaths");
                        long totalToday = json.getLong("deltaconfirmed");
                        long deathToday = json.getLong("deltadeaths");
                        long recoveredToday = json.getLong("deltarecovered");
                        long recovered = json.getLong("recovered");
                        corona.add(new CoronaState(state, total, totalToday, death, deathToday, recovered, recoveredToday, active));
                    }
                }

            }
        }catch (JSONException e){
            Log.e("Utils","Problem with parsing JSON",e);
        }
        return corona;
    }
    private static ArrayList<CoronaDistrict> extractDistrictJson(String coronaJSON){
        if(TextUtils.isEmpty(coronaJSON)){
            return null;
        }
        ArrayList<CoronaDistrict> corona=new ArrayList<>();
        try{
            JSONObject jsonBase = new JSONObject(coronaJSON);
            JSONObject jsonState = jsonBase.getJSONObject("state_wise");
            Iterator<String> keys = jsonState.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if (jsonState.get(key) instanceof JSONObject) {
                    String state = key;
                    JSONObject json = jsonState.getJSONObject(key);
                    JSONObject jsonDistrict=json.getJSONObject("district");
                    Iterator<String> d=jsonDistrict.keys();
                    while(d.hasNext()){
                        String k=d.next();
                        if(jsonDistrict.get(k) instanceof JSONObject){
                            String district=k;
                            JSONObject pd=jsonDistrict.getJSONObject(k);
                            long active = pd.getLong("active");
                            long total = pd.getLong("confirmed");
                            long death = pd.getLong("deceased");
                            long recovered = pd.getLong("recovered");
                            JSONObject delta=pd.getJSONObject("delta");
                            long totalToday = delta.getLong("confirmed");
                            long deathToday = delta.getLong("deceased");
                            long recoveredToday = delta.getLong("recovered");
                            CoronaDistrict coronaDistrict=new CoronaDistrict(state,district,total,totalToday,death,deathToday,recovered,recoveredToday,active);
                            corona.add(coronaDistrict);
                        }
                    }
                    }

            }
        }catch (JSONException e){
            Log.e("Utils","Problem with parsing JSON",e);
        }
        return corona;
    }
    private static ArrayList<Corona> extractCountryJson(String coronaJSON){
        if(TextUtils.isEmpty(coronaJSON)){
            return null;
        }
        ArrayList<Corona> corona=new ArrayList<>();
        try{
            JSONArray baseJson=new JSONArray(coronaJSON);
            for(int i=0;i<baseJson.length();i++){
                JSONObject jsonObject=baseJson.getJSONObject(i);
                String country=jsonObject.getString("country");
                long total=jsonObject.getLong("cases");
                long totalToday=jsonObject.getLong("todayCases");
                long death=jsonObject.getLong("deaths");
                long deathToday=jsonObject.getLong("todayDeaths");
                long recovered=jsonObject.getLong("recovered");
                long recoveredToday=jsonObject.getLong("todayRecovered");
                long active=jsonObject.getLong("active");
                Corona coronas=new Corona(total,death,recovered,active,totalToday,deathToday,recoveredToday,country);
                corona.add(coronas);
            }
        }catch (JSONException e){
            Log.e("Utils","Problem with parsing JSON",e);
        }
        return corona;
    }
    private static Corona extractFromJson(String coronaJSON){
        if(TextUtils.isEmpty(coronaJSON)){
            return null;
        }
        Corona corona=null;
        try{
            JSONObject baseJsonResponse=new JSONObject(coronaJSON);
            long total=baseJsonResponse.getLong("cases");
            long totalToday=baseJsonResponse.getLong("todayCases");
            long death=baseJsonResponse.getLong("deaths");
            long deathToday=baseJsonResponse.getLong("todayDeaths");
            long recovered=baseJsonResponse.getLong("recovered");
            long recoveredToday=baseJsonResponse.getLong("todayRecovered");
            long active=baseJsonResponse.getLong("active");

            corona=new Corona(total,death,recovered,active,totalToday,deathToday,recoveredToday);
        }
        catch (JSONException e){
            Log.e("Utils","Problem with parsing JSON",e);
        }
        return corona;
    }
    private static URL createUrl(String stringUrl){
        URL url=null;
        try{
            url=new URL(stringUrl);
        }
        catch(MalformedURLException e){
            Log.e("Utils","Problem building the Url",e);
        }
        return url;
    }
    private static String makeHttpRequest(URL url)throws IOException {
        String jsonResponse="";
        if(url==null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection=null;
        InputStream inputStream=null;
        try{
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if(urlConnection.getResponseCode()==200){
                inputStream=urlConnection.getInputStream();
                jsonResponse=readFromStream(inputStream);
            }
            else{
                Log.e("Utils","Error Response Code: "+urlConnection.getResponseCode());
            }
        }
        catch (IOException e){
            Log.e("Utils","Error retreiving the corona JSON results",e);
        }
        finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output=new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader=new BufferedReader(inputStreamReader);
            String line=reader.readLine();
            while(line!=null){
                output.append(line);
                line=reader.readLine();
            }
        }
        return output.toString();
    }
    public static Corona fetchCoronaData(String requestUrl){
        URL url=createUrl(requestUrl);
        String jsonResponse=null;
        try{
            jsonResponse=makeHttpRequest(url);
        }catch (IOException e){
            Log.e("Utils","Problem making HTTP request",e);
        }
        Corona corona=extractFromJson(jsonResponse);
        return corona;
    }
    public static ArrayList<Corona> fetchCountryCorona(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("Utils", "Problem making HTTP request", e);
        }
        ArrayList<Corona> corona = extractCountryJson(jsonResponse);
        return corona;
    }
    public static ArrayList<CoronaDistrict> fetchDistrictCorona(String requestUrl){
        Response response=null;
        String jsonResponse=null;
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(requestUrl)
                .get()
                .addHeader("x-rapidapi-key", "5f8b2eee7emsh4441a6c4b158c23p1f3423jsn5417693e336d")
                .addHeader("x-rapidapi-host", "corona-virus-world-and-india-data.p.rapidapi.com")
                .build();

        try {
            response = client.newCall(request).execute();
            jsonResponse = response.body().string();
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<CoronaDistrict> coronaDistrict = extractDistrictJson(jsonResponse);
        return coronaDistrict;

    }
    public static ArrayList<CoronaState> fetchStateCorona(String requestUrl) {
        Response response=null;
        String jsonResponse=null;
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(requestUrl)
                .get()
                .addHeader("x-rapidapi-key", "5f8b2eee7emsh4441a6c4b158c23p1f3423jsn5417693e336d")
                .addHeader("x-rapidapi-host", "corona-virus-world-and-india-data.p.rapidapi.com")
                .build();

        try {
            response = client.newCall(request).execute();
            jsonResponse = response.body().string();
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<CoronaState> coronaState = extractStateJson(jsonResponse);
        return coronaState;
    }
}
