package com.applicationforlife.jamesnikolaidis.contacts.EMULATOR_PACKAGE;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.applicationforlife.jamesnikolaidis.contacts.R;

import java.util.ArrayList;

/**
 * Created by James Nikolaidis on 3/12/2017.
 */

public class Custom_Adapter extends ArrayAdapter<String> {

    private static ArrayList<String> CONTACT_NAME , CONTACT_NUMBER;
    private static Context appContext;

    public Custom_Adapter(@NonNull Context context, ArrayList<String> names, ArrayList<String> tel) {
        super(context, R.layout.default_adapter,tel);

       this.CONTACT_NAME = names;
       this.CONTACT_NUMBER = tel;
        this.appContext = context;
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(appContext);
        View defaultAdapterLayout = layoutInflater.inflate(R.layout.default_adapter,parent,false);
        TextView mNameTextView , mTelTextView;

        mNameTextView = (TextView)defaultAdapterLayout.findViewById(R.id.NameTextView);
        mTelTextView = (TextView) defaultAdapterLayout.findViewById(R.id.TelTextView);


        mNameTextView.setText(CONTACT_NAME.get(position));
        mTelTextView.setText(CONTACT_NUMBER.get(position));


        return  defaultAdapterLayout;
    }
}
