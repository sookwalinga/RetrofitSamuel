package com.samo.retrofitsamuel.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofitinstance {

    //    Defining the base url from which the data is fetched.
    public static String BASE_URL = "https://jsonplaceholder.typicode.com/";

    //        Defining the Retrofit instance.
    private static Retrofit retrofit;

    //    Function to return the Retrofit isntance. Applying the singleton pattern.
    public static Retrofit getRetroClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
//                    GSON Converter to convert Java Objects into their JSON Representation.
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
