package com.applicationforlife.jamesnikolaidis.contacts;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.applicationforlife.jamesnikolaidis.contacts.EMULATOR_PACKAGE.Create_JSON_File_In_Localost;
import com.applicationforlife.jamesnikolaidis.contacts.EMULATOR_PACKAGE.Insert_New_Contact_in_JSON_File;
import com.applicationforlife.jamesnikolaidis.contacts.EMULATOR_PACKAGE.Refersh_The_List_View_Localhost_MODE;
import com.applicationforlife.jamesnikolaidis.contacts.REAL_DEVICE_PACKAGE.Create_JSON_File_In_SDCARD;
import com.applicationforlife.jamesnikolaidis.contacts.REAL_DEVICE_PACKAGE.Insert_New_Contact_in_JSON;
import com.applicationforlife.jamesnikolaidis.contacts.REAL_DEVICE_PACKAGE.Refersh_The_List_View_REAL_DEVICE_MODE;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView)findViewById(R.id.Contacts);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                    final AlertDialog alertDialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    alertDialog = builder.create();
                    alertDialog.show();
                    alertDialog.setContentView(R.layout.add_contact_layout);
                    alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                    LinearLayout linearLayout1 =(LinearLayout)alertDialog.findViewById(R.id.Contact_Layout);
                    linearLayout1.setVisibility(View.VISIBLE);
                    Button saveBtn = (Button) alertDialog.findViewById(R.id.button);
                    saveBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditText name =(EditText) alertDialog.findViewById(R.id.Name_Edit_Text);
                            EditText tel =(EditText) alertDialog.findViewById(R.id.Telephone_Edit_Text);


                            if(Check_IF_Device_is_Emulator.Check_The_Devise(MainActivity.this)){

                                try {
                                    Insert_New_Contact_in_JSON.Insert_new_Contact_to_JSON_file(name.getText().toString(),tel.getText().toString());
                                    Refersh_The_List_View_REAL_DEVICE_MODE refersh_the_list_view_real_device_mode = new Refersh_The_List_View_REAL_DEVICE_MODE();

                                    refersh_the_list_view_real_device_mode.Refresh_The_list_View_In_Real_Device(listView,getApplicationContext(),MainActivity.this);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                alertDialog.cancel();
                            Snackbar.make(view, "The Contact has been added.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            }else{
                                Log.e("IT",String.valueOf(listView.getId()));
                                new Insert_New_Contact_in_JSON_File().execute(name.getText().toString(),tel.getText().toString());
                                new Refersh_The_List_View_Localhost_MODE().execute(listView,getApplicationContext(),MainActivity.this);
                                alertDialog.cancel();
                                Snackbar.make(view, "The Contact has been added.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }




                        }
                    });



            }
        });



        JSONObject newJsonObject = new JSONObject();
        try {
            newJsonObject.put("Chat",new JSONObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if(Check_IF_Device_is_Emulator.Check_The_Devise(this)) {
            try {
                Create_JSON_File_In_SDCARD.Create_JSON_File_In_SDCARD(newJsonObject.toString());

            } catch (IOException e) {

                e.printStackTrace();

            }
        }else{
            new Create_JSON_File_In_Localost(getApplicationContext(),this).execute(newJsonObject.toString());


        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
