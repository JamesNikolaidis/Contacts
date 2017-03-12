package com.applicationforlife.jamesnikolaidis.contacts.REAL_DEVICE_PACKAGE;

import android.app.Activity;
import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.applicationforlife.jamesnikolaidis.contacts.EMULATOR_PACKAGE.Custom_Adapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by James Nikolaidis on 3/11/2017.
 */

public class Refersh_The_List_View_REAL_DEVICE_MODE {


    private static  JSONObject Original_JSON_Object,KEYNODE;
    private static ArrayList<String> JSON_KEYS,NAMES_LIST , TELEPHONE_LIST;
    private  static ListView listView;
    private static ListAdapter adapter;



    public Refersh_The_List_View_REAL_DEVICE_MODE(){
        JSON_KEYS = new ArrayList<>();
    }


    public static  void Refresh_The_list_View_In_Real_Device(Object ...params) throws IOException,JSONException {

        String JSON_String = Read_Data_From_JSON_File.Read_JSON_Data_And_Return_JSON_String();

        Original_JSON_Object = new JSONObject(JSON_String).getJSONObject("Chat");

        listView = (ListView)params[0];

        NAMES_LIST = new ArrayList<>();

        TELEPHONE_LIST = new ArrayList<>();

        Iterator<String> iterator = Original_JSON_Object.keys();

        while(iterator.hasNext()){

            String key = iterator.next();

            JSON_KEYS.add(key);

            KEYNODE = Original_JSON_Object.getJSONObject(key);


            NAMES_LIST.add(KEYNODE.getString("Name"));

            TELEPHONE_LIST.add(KEYNODE.getString("text"));

        }

        adapter = new Custom_Adapter((Context)params[1],NAMES_LIST,TELEPHONE_LIST);

        final Activity applicationActivity = (Activity)params[2];

        applicationActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(adapter);

            }
        });




    }



}
