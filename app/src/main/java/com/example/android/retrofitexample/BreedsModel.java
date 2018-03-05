package com.example.android.retrofitexample;

import java.util.List;

/**
 * Created by mher on 3/5/18.
 */

public class BreedsModel {

    private String status;

    private List<String> message;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public List<String> getMessage() {
        return message;
    }
}
