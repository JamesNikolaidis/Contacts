package com.applicationforlife.jamesnikolaidis.contacts;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by James Nikolaidis on 3/10/2017.
 */

public class Check_IF_Device_is_Emulator {



    //Checks if the RUNNING DEVICE IS EMULATOR OR REAL DEVISE
    public static boolean Check_The_Devise(Activity applicationActivity){

        TelephonyManager telephonyManager = (TelephonyManager) applicationActivity.getSystemService(Context.TELEPHONY_SERVICE);

            String networkType = telephonyManager.getNetworkOperatorName();

                if("Android".equals(networkType)){
                 //then is emulator

                    return  false;
                }else{

                    //is real device
                    return true;
                }



    }


}
