package com.example.android.retrofitexample;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mher on 3/5/18.
 */

public class Client {

    private static final String BASE_URL = "https://dog.ceo/dog-api/";

    private OkHttpClient client;
    private Retrofit retrofit;
    private MyCallService myCallService;

    public Client() {
        initClient();
        initRetrofit();
        initCallService();
    }

    private void initClient() {
        client = new OkHttpClient.Builder()
                .addInterceptor(new MyInterceptor())
                .build();
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void initCallService() {
        myCallService = retrofit.create(MyCallService.class);
    }

    public Call<BreedsModel> getBreeds() {
        return myCallService.getBreedsList();
    }
}
