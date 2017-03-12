package com.applicationforlife.jamesnikolaidis.contacts.REAL_DEVICE_PACKAGE;

import android.os.Environment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by James Nikolaidis on 3/11/2017.
 */

public class Insert_New_Contact_in_JSON {

    private static JSONObject ORIGINAL_JSON_OBJECT,CHAT_OBJECT,NEW_CONTACT_OBJECT;
    private static String JSON_STRING,FIRST_COLLUM="Name",SECOND_COLLUM="text";
    private static BufferedWriter bufferedWriter;
    private static File JSON_FILE;
    private static String FilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/ContactApp/"+"Contacts.json";

    public static void Insert_new_Contact_to_JSON_file(String ...params) throws IOException , JSONException {

            JSON_STRING = new String();

            //___________ GET THE ORIGINAL JSON STRING 
            JSON_STRING = Read_Data_From_JSON_File.Read_JSON_Data_And_Return_JSON_String();

             //_______ Create the First JSON Object from the Original File
            ORIGINAL_JSON_OBJECT = new JSONObject(JSON_STRING);


            //_______ Create's the Chat JSON Object
            CHAT_OBJECT = ORIGINAL_JSON_OBJECT.getJSONObject("Chat");

            //_________ Create the New Contact JSON Object
            NEW_CONTACT_OBJECT = new JSONObject();

             //_________ Add to the New Contact JSON Object data
            NEW_CONTACT_OBJECT.put(FIRST_COLLUM,params[0]);
            NEW_CONTACT_OBJECT.put(SECOND_COLLUM,params[1]);

            //_____________ inserted into the Chat OPT
            CHAT_OBJECT.putOpt(String.valueOf(Math.random()),NEW_CONTACT_OBJECT);


            //_____________ Refresh the Original JSON File
            ORIGINAL_JSON_OBJECT.put("Chat",CHAT_OBJECT);


            //____________ Open the JSON file
            JSON_FILE = new File(FilePath);

            //___________ Create the proper writer
            bufferedWriter = new BufferedWriter( new FileWriter(JSON_FILE));


            //____________ Write the JSON String into the file
            bufferedWriter.write(ORIGINAL_JSON_OBJECT.toString());

            //_________ Close the file
            bufferedWriter.close();

        

    }



}
