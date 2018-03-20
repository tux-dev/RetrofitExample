package com.example.android.retrofitexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
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
        Button startButtonObservable = findViewById(R.id.start_button_observable);
        startButton.setOnClickListener(this);
        startButtonObservable.setOnClickListener(this);
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
            }

            @Override
            public void onFailure(Call<BreedsModel> call, Throwable t) {
                System.out.println("=====> response Failed");
            }
        });
    }

    private void startGetObservable() {
        client.getBreedsObservable()
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<BreedsModel>() {
                    @Override
                    public boolean test(BreedsModel breedsModel) throws Exception {
                        return breedsModel.getStatus().equals("success");
                    }
                })
                .map(new Function<BreedsModel, List<String>>() {
                    @Override
                    public List<String> apply(BreedsModel breedsModel) throws Exception {
                        List<String> newList = new ArrayList<>();
                        List<String> msg = breedsModel.getMessage();
                        if (msg != null) {
                            for (String message : msg) {
                                if (message.startsWith("a")) {
                                    newList.add(message);
                                }
                            }
                        }
                        return newList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getSubscriber());
    }

    private Observer<List<String>> getSubscriber() {
        return new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> strings) {
                System.out.println("===> from Observer current string");
                if (strings != null) {
                    for (String message : strings) {
                        System.out.println("====> from Obs list message is " + message);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                startCall();
                break;
            case R.id.start_button_observable:
                startGetObservable();
                break;
        }
    }
}
