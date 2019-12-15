package com.example.addressproject;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    //以下跟选择地址有关
    private AreaPickerView areaPickerView;
    private List<AddressBean> addressBeans;
    private int[] i;
    private String villageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.text_view);

        Gson gson = new Gson();
        addressBeans = gson.fromJson(getJsonFromAssets(MainActivity.this), new TypeToken<List<AddressBean>>() {
        }.getType());
        areaPickerView = new AreaPickerView(this, R.style.Dialog, addressBeans);
        areaPickerView.setAreaPickerViewCallback(new AreaPickerView.AreaPickerViewCallback() {
            @Override
            public void callback(int... value) {
                i=value;
                if (value.length == 4) {
                    textView.setText(addressBeans.get(value[0]).getLabel() + "-" + addressBeans.get(value[0]).getChildren().get(value[1]).getLabel() + "-" + addressBeans.get(value[0]).getChildren().get(value[1]).getChildren().get(value[2]).getLabel()+"-"+ addressBeans.get(value[0]).getChildren().get(value[1]).getChildren().get(value[2]).getChildren().get(value[3]).getLabel());
                    villageId=addressBeans.get(value[0]).getChildren().get(value[1]).getChildren().get(value[2]).getChildren().get(value[3]).getValue();
                } else{

                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListDialogvillage();
            }
        });
    }

    private void showListDialogvillage() {
        areaPickerView.setSelect(i);
        areaPickerView.show();
    }


    public static String getJsonFromAssets(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("region.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
