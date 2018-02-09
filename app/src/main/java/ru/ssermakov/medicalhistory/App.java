package ru.ssermakov.medicalhistory;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by btb_wild on 09.02.2018.
 */

public class App extends Application {
    private static DbService dbService;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.11:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dbService = retrofit.create(DbService.class);
    }

    public static DbService getDbService() {
        return dbService;
    }
}
