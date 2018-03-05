package com.example.android.retrofitexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        client = new Client();
    }

    private void startCall() {
        if (client == null) {
            System.out.println("===> Client is null");
            return;
        }
        client.getBreeds().enqueue(new Callback<BreedsModel>() {
            @Override
            public void onResponse(Call<BreedsModel> call, Response<BreedsModel> response) {
                System.out.println("=====> response is " + response.toString());
                if (!response.isSuccessful()) {
                    System.out.println("===> response is not successful");
                    return;
                }
                BreedsModel model = response.body();
                if (model == null) {
                    System.out.println("===> model is null");
                    return;
                }
                System.out.println("====> status is " + model.getStatus());
            }

            @Override
            public void onFailure(Call<BreedsModel> call, Throwable t) {
                System.out.println("=====> response Failed");
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                startCall();
                break;
        }
    }
}
