package com.applicationforlife.jamesnikolaidis.contacts.EMULATOR_PACKAGE;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * Created by James Nikolaidis on 3/11/2017.
 */

public class Insert_New_Contact_in_JSON_File extends AsyncTask<String,String,String> {


    private JSONObject PastJson;
    private HttpPost httpPost;
    private byte[] StringByte;
    private JSONObject ChatObject;
    private static String JSON_STRING,FIRST_COLLUM="Name",SECOND_COLLUM="text";
    private JSONObject newJsonObject,New_Chat_Message_Object;
    private Map<String,String> NEW_MESSAGE_NODE;


    @Override
    protected String doInBackground(String... params) {

        JSON_STRING= new String();

        JSON_STRING = new Read_From_URL_And_Return_JSON_String().doInBackground("");


        //_______ Create the First JSON Object from the Original File
        try {
            newJsonObject = new JSONObject(JSON_STRING);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //_______  Create new JSON File to store the new User Contact
        New_Chat_Message_Object = new JSONObject();

        try {
            //_________ Save on the first collum the name of the sender
            New_Chat_Message_Object.put(FIRST_COLLUM,params[0]);

        } catch (JSONException e) {
            //____ JSON Error here
            e.printStackTrace();
        }


        try {
            //_________ Save on the first collum the name of the sender
            New_Chat_Message_Object.put(SECOND_COLLUM,params[1]);

        } catch (JSONException e) {
            //____ JSON Error here
            e.printStackTrace();
        }


        try {
            //_______ Create's the Chat JSON Object
            ChatObject = new JSONObject(JSON_STRING).getJSONObject("Chat");

        } catch (JSONException e) {
            //____ JSON Error here
            e.printStackTrace();
        }


        try {
            //________ Create new JSON OPT
            ChatObject.putOpt(String.valueOf(Math.random()), New_Chat_Message_Object);

        } catch (JSONException e) {
            //____ JSON Error here
            e.printStackTrace();
        }



        try {
            //________ Store the new Contact in the JSON File
            newJsonObject.put("Chat",ChatObject);
        } catch (JSONException e) {
            //____ JSON Error here
            e.printStackTrace();
        }


        StringByte = new byte[newJsonObject.length()];

        //______ Save the String byte data into the Byte Table
        StringByte = newJsonObject.toString().getBytes();



        DefaultHttpClient httpClient = new DefaultHttpClient();

        for(int i=0; i!=StringByte.length;i++){

            //______ Create new GET method varaible to send the data
            if(i==0){
            httpPost = new HttpPost("http://10.0.2.2/ContactExample/test.php?value="+(StringByte[i])+"&key=0");//output is the variable you used in your program
            }else{
                httpPost = new HttpPost("http://10.0.2.2/ContactExample/test.php?value="+(StringByte[i])+"&key=1");//output is the variable you used in your program

            }
            try {

                //______  Execute the below GET Method request
                httpClient.execute(httpPost);



            } catch (IOException e) {
                Log.e("EROR", "First Error");
                e.printStackTrace();
            }
        }




        return null;
    }


}
