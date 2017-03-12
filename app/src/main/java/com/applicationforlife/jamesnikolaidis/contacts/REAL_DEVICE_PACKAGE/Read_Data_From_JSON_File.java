package com.applicationforlife.jamesnikolaidis.contacts.REAL_DEVICE_PACKAGE;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by James Nikolaidis on 3/11/2017.
 */

public class Read_Data_From_JSON_File {

        private static String FilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/ContactApp/"+"Contacts.json";
        private static File JSON_File;
        private static BufferedReader bufferedReader;
        private FileReader fileReader;
        private static Context appContext;
        private static Activity appActivity;
        private static String JSON_STRING,Returned_JSON_String;


        public Read_Data_From_JSON_File(Context applicationContext , Activity applicationActivity ){
            this.appContext = applicationContext;
            this.appActivity = applicationActivity;
        }


        public static String Read_JSON_Data_And_Return_JSON_String() throws IOException {

            JSON_File = new File(FilePath);

            bufferedReader = new BufferedReader(new FileReader(JSON_File));

            Returned_JSON_String="";

            while ((JSON_STRING = bufferedReader.readLine()) != null) {

                Returned_JSON_String = Returned_JSON_String+JSON_STRING;

            }
            Log.i("BABY", Returned_JSON_String);

            return Returned_JSON_String;
        }




}
