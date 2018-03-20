package com.example.android.retrofitexample;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mher on 3/5/18.
 */

public interface MyCallService {

    @GET("/api/breeds/list")
    Call<BreedsModel> getBreedsList();

    @GET("/api/breeds/list")
    Observable<BreedsModel> getBreedsObservable();
}
