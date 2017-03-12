package com.applicationforlife.jamesnikolaidis.contacts.REAL_DEVICE_PACKAGE;


import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by James Nikolaidis on 3/11/2017.
 */

public class Create_JSON_File_In_SDCARD {


        // C_L_A_S_S    V A R A I B L E S
        private static File SDCARD_PATH_STRING;
        private static File JSON_FILE;;
        private static FileOutputStream Write_To_JSON_File;
        private static boolean Created_Dir=false, Correct_Write = false;



        public static boolean Create_JSON_File_In_SDCARD(String First_JSON) throws IOException {

                // _____________  Find the SDCARD Directory to save the JSON File
                SDCARD_PATH_STRING = Environment.getExternalStorageDirectory();

                //______________ Create the Full Dir to Save the JSON file
                File JSON_Dir_File = new File(SDCARD_PATH_STRING.getAbsolutePath()+"/ContactApp/");

                //__________  Create the below Dir ith mkdir();
                Created_Dir = JSON_Dir_File.mkdir();

                //____________ Create the JSON File
                JSON_FILE = new File(JSON_Dir_File,"Contacts.json");

                //_____________ Create the FileOutput Stream for writing the data in the file
                Write_To_JSON_File = new FileOutputStream(JSON_FILE);


                // _____________  Check if the store has been successful
                try {
                        //______________  Write the JSON Data in the File
                        Write_To_JSON_File.write(First_JSON.getBytes());
                        Correct_Write = true;

                } catch (IOException e) {
                        Correct_Write = false;
                        e.printStackTrace();}

                //_________ Close the FileOutputStream Connection
                Write_To_JSON_File.close();



                //_______  Check if everything is ok
                if(Correct_Write==true && Created_Dir==true){
                        return  true;
                }else{
                        return false;
                }




        }







}
