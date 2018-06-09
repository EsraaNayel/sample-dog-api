package com.esraanayel.sampledogapi.utils;

import com.esraanayel.sampledogapi.BuildConfig;

/**
 * Created by Esraa on 6/6/2018.
 */

public class Constant {

    public static String getBaseURL(){
        if(BuildConfig.DEBUG ){
            return "https://dog.ceo/api/breeds/" ;  //debug
        }else{
            return "https://dog.ceo/api/breeds";    //release
        }
    }
}
