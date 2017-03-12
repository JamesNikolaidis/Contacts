package com.applicationforlife.jamesnikolaidis.contacts.EMULATOR_PACKAGE;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by James Nikolaidis on 3/11/2017.
 */

public class Read_From_URL_And_Return_JSON_String extends AsyncTask<String,String,String> {


        private static URL JSON_FILE_URL;
        private static String JSON_FILE_URL_STRING="http://10.0.2.2/ContactExample/Contacts.json";
        private static BufferedReader JSON_Reader ;
        private static InputStream JSON_INPUT_STREAM;
        private static String JSON_STRING="",GET_STRING;
    JSONObject ggg;




    @Override
    public  String doInBackground(String... params) {

        JSON_STRING =new String("");
        try {
            JSON_FILE_URL = new URL(JSON_FILE_URL_STRING);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            JSON_INPUT_STREAM =  JSON_FILE_URL.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


        JSON_Reader = new BufferedReader(new InputStreamReader(JSON_INPUT_STREAM));

        try {

            while( (GET_STRING=JSON_Reader.readLine())!=null){
                JSON_STRING = JSON_STRING+ GET_STRING;

            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.e("JSONDATA","Before"+JSON_STRING);

        return JSON_STRING;
    }
}
