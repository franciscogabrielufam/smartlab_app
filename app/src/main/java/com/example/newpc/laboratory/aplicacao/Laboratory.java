package com.example.newpc.laboratory.aplicacao;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Francisco Gabriel on 17/10/2017.
 */

public class Laboratory extends Application {

    private static Laboratory laboratory;
    private RequestQueue requestQueue;

    @Override
    public void onCreate() {

        super.onCreate();

        laboratory = this;
    }

    public static synchronized Laboratory getInstancia() {

        return laboratory;
    }

    public RequestQueue obterRequestQueue() {

        if(requestQueue == null) requestQueue = Volley.newRequestQueue(this);

        return requestQueue;
    }

    public <T> void adicionarRequestQueue(Request<T> request) {

        obterRequestQueue().add(request);
    }
}