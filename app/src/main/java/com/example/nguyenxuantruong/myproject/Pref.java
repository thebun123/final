package com.example.nguyenxuantruong.myproject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.nguyenxuantruong.myproject.Data.GetData;

/**
 * Created by nguyenxuantruong on 5/15/17.
 */

public class Pref extends Activity{

    RadioGroup MySetting;
    RadioButton usd,vnd,eru;
    String type;
    SharedPreferences preferences;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pref);

        MySetting = (RadioGroup) findViewById(R.id.rgSetting);
        usd = (RadioButton) findViewById(R.id.rbUSD);
        vnd = (RadioButton) findViewById(R.id.rbVND);
        eru = (RadioButton) findViewById(R.id.rbERU);



        preferences = getSharedPreferences(GetData.fileName,0);

        usd.setText("USD " + preferences.getFloat("USD", (float) 22.0) + "USD");
        eru.setText("ERU " + preferences.getFloat("ERU", (float) 22.0) + "ERU ");
        type = preferences.getString("typeMoney","VND");
        final SharedPreferences.Editor editor = preferences.edit();
        Log.e("type",type);
        switch(type){

            case "VND" :
                vnd.setChecked(true);
                break;

            case "USD" :
                usd.setChecked(true);
                break;

            case "ERU" :
                eru.setChecked(true);
                break;
        }

        MySetting.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId){
                    case R.id.rbUSD :
                        editor.putString("typeMoney","USD");
                        Log.e("click","clickUSD");

                        break;

                    case R.id.rbVND :
                        editor.putString("typeMoney","VND");
                        Log.e("click","clickVND");
                        break;

                    case R.id.rbERU :
                        editor.putString("typeMoney","ERU");
                        Log.e("click","clickERU");
                        break;
                }
                editor.apply();
                finish();
            }
        });


    }
}
