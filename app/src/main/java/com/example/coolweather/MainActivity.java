package com.example.coolweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.coolweather.test.InitCity;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getData();
    }

    private void getData() {
        InitCity[] initCities = getInitCityFile("init_city.json");
        getChinaData(initCities);
    }


    public void getChinaData(InitCity[] initCities) {
        if (initCities == null) {
            Log.d(TAG, "getProvince: The initCities is null!");
            return;
        }

        StringBuilder province = new StringBuilder();
        StringBuilder city = new StringBuilder();
        StringBuilder county = new StringBuilder();

        province.append("[\n");
        city.append("[\n");
        county.append("[\n");

        int p = 1;
        int c = 1;
        int cc = 1;
        int co = 1;
        int coco = 1;

        String pi = "null";
        String ci = "null";
        String coi = "null";
        HashMap<String, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();

        String ps = "null";
        String cs = "null";
        String cos = "null";

        for (InitCity ic : initCities) {
            if (!(ic.getProvinceZh().equals(ps))) {
                pi = String.valueOf(10100 + p);
                ps = ic.getProvinceZh();
                province.append("{\"id\":"+p+",\"ProvinceId\":\""+pi+"\",\"provinceName\":\""+ps+"\"},");
                p++;
                c = 1;
                co = 1;
            }
            if (!map.containsKey(ic.getLeaderZh())) {
                ci = String.valueOf(Integer.valueOf(pi) * 100 + c);
                map.put(ic.getLeaderZh(),ci);
                cs = ic.getLeaderZh();
                city.append("{\"id\":"+cc+",\"CityId\":\""+ci+"\",\"cityName\":\""+cs+"\",\"provinceId\":\""+pi+"\"},");
                c++;
                cc++;
                co = 1;

            }
            coi = String.valueOf(Integer.valueOf(ci) * 100 + co);
            county.append("{\"id\":"+coco+",\"countyId\":\""+coi+"\",\"countyName\":\""+ic.getCityZh()+"\",\"cityId\":\""+map.get(ic.getLeaderZh())+"\",\"weatherId\":\""+ic.getId()+"\"},");
            co++;
            coco++;
        }

        province.append("\n]");
        city.append("\n]");
        county.append("\n]");

        save(province.toString(),"province.json");
        save(city.toString(),"city.json");
        save(county.toString(),"county.json");
    }


    public InitCity[] getInitCityFile(String fileName) {

        InputStreamReader isr = null;
        BufferedReader br = null;
//        StringBuilder builder = new StringBuilder();

        try {
            isr = new InputStreamReader(getAssets().open(fileName));
            br = new BufferedReader(isr);
            Gson gson = new Gson();
            InitCity[] initCities = gson.fromJson(br, InitCity[].class);
            return initCities;

//            String line ="";
//            while ((line=br.readLine())!=null){
//                builder.append(line);
//            }
//            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public void save(String info,String name){
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            fos=openFileOutput(name, Context.MODE_PRIVATE);
            bw=new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(info);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}