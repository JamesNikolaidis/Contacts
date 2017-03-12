package com.applicationforlife.jamesnikolaidis.contacts.EMULATOR_PACKAGE;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by James Nikolaidis on 3/11/2017.
 */

public class Refersh_The_List_View_Localhost_MODE extends AsyncTask{

    private JSONObject Original_JSON_Object,KEYNODE;
    private static ArrayList<String> JSON_KEYS,NAMES_LIST , TELEPHONE_LIST;
    private ListView listView;
    private ListAdapter adapter;



    public Refersh_The_List_View_Localhost_MODE(){
            JSON_KEYS = new ArrayList<>();
    }


    @Override
    public Object doInBackground(Object ...params) {


        //_______ GET the Original JSON File String
        String JSON_String = new Read_From_URL_And_Return_JSON_String().doInBackground("");

        //_____________ Initialize the ArrayList
        NAMES_LIST= new ArrayList<>();
        TELEPHONE_LIST = new ArrayList<>();

        //________ Fetch the Chat JSON Objetc via JSON String
        try {
             Original_JSON_Object = new JSONObject(JSON_String).getJSONObject("Chat");
        } catch (JSONException e) {
            e.printStackTrace();
        }



        // _______ initialize the iterator to store the node id of each JSON Opt
       Iterator<String> it = Original_JSON_Object.keys();


        //_________ Fetch the Data from the JSON File
       while(it.hasNext()){

            String nextId = it.next();
           //______ Save the node key for future uses
            JSON_KEYS.add(nextId);

           try {

               // we fetching the node with the Node id which we have already got
               KEYNODE = Original_JSON_Object.getJSONObject(nextId);

           } catch (JSONException e) {

               e.printStackTrace();
           }

           try {

               //_________ Fetch the Data from the JSON File
               NAMES_LIST.add(KEYNODE.getString("Name"));

               TELEPHONE_LIST.add( KEYNODE.getString("text"));



           } catch (JSONException e) {
               e.printStackTrace();
           }

       }


        //________ Get the List View Object from the Params as object
        listView = (ListView) params[0];

        //_______ Create a new Custom adapter Object
         adapter = new Custom_Adapter((Context)params[1],NAMES_LIST,TELEPHONE_LIST);

        Activity activity = (Activity)params[2];


        //______ This is a must if you want to make changes on a UI if you are not inside it
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //________ Complete the initialize of the Listview by setting his adapter
                listView.setAdapter(adapter);
            }
        });


        return null;
    }
}
