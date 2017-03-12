package com.applicationforlife.jamesnikolaidis.contacts.EMULATOR_PACKAGE;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by James Nikolaidis on 3/10/2017.
 */

public class Create_JSON_File_In_Localost extends AsyncTask<String,String,String> {


    // C_L_A_S_S    V A R A I B L E S

            private static Context appContext;
            private static Activity appActivity;
            private File   newJsonFile;
            private static  String PHP_PATH_TO_CREATE_JSON_FILE = "http://10.0.2.2/ContactExample/savefile.php?value=";
            private static String JSON_FILE_PATH = "http://10.0.2.2/ContactExample/Contacts.json";
            private HttpPost httpPost;
            private URL url_To_Localhost;
            private  byte[] StringByte;
            private BufferedReader ins;


    // C_L_A_S_S    C O N S T R U C T O R
            public Create_JSON_File_In_Localost(Context applicationContext, Activity applicationActivity){
                    this.appContext = applicationContext;
                    this.appActivity = applicationActivity;
            }




    @Override
    protected String doInBackground(String... params) {


        // ______ Create httpClient Object to attach the php file that creates the json file
        DefaultHttpClient httpClient = new DefaultHttpClient();

        // _____ Create new Byte type array to store the JSON String data as bytes , in order to transport it via php file
         StringByte = new byte[params.length];

         //______ Save the String byte data into the Byte Table
         StringByte = params[0].getBytes();


        //______  Start the process of read each byte from the table and send it to the php file for store
        for(int i=0; i!=StringByte.length;i++){

            //______ Create new GET method varaible to send the data
            httpPost = new HttpPost(PHP_PATH_TO_CREATE_JSON_FILE+(StringByte[i]));//output is the variable you used in your program

            try {

                //______  Execute the below GET Method request
                httpClient.execute(httpPost);






            } catch (IOException e) {
                Log.e("EROR", "First Error");
                e.printStackTrace();
            }
        }






        /*-------Read the Data from the JSON File  (OPTIONAL) ************/

        String inputLine;
        try {
            url_To_Localhost = new URL(JSON_FILE_PATH);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
             ins = new BufferedReader(
                    new InputStreamReader(url_To_Localhost.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while ((inputLine = ins.readLine()) != null) {
              //  Log.i("JSONDATA",inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    /******* _____ End of optional part of reading from json file _____*/




        return null;
    }



}
