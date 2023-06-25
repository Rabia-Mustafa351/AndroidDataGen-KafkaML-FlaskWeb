package com.example.datacollectionapp;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;



public class RetrofitClient {



    private static Service REST_CLIENT;
    static {
        setupRestClient();
    }

    private RetrofitClient() {}

    public static Service get() {
        //Log.e("Service",String.valueOf(REST_CLIENT));
        return REST_CLIENT;
    }

    public static void setupRestClient() {
        OkHttpClient client = new OkHttpClient();

        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(30,TimeUnit.SECONDS);

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setClient(new OkClient(client))
                .setLogLevel(RestAdapter.LogLevel.FULL);
        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(Service.class);
    }

    private static Retrofit retrofit = null;
    public static Retrofit getClient(String baseUrl){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }



}
