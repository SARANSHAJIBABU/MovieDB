package com.saran.mdb;

import retrofit2.Retrofit;

/**
 * Created by Saran on 2/11/16.
 */

public class ApiCLient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .build();
        }
        return retrofit;
    }
}
