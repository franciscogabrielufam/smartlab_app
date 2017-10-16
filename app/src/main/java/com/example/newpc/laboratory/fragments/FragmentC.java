package com.example.newpc.laboratory.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.newpc.laboratory.Cafeteira;
import com.example.newpc.laboratory.Datashow;
import com.example.newpc.laboratory.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francisco Gabriel on 13/10/2017.
 */

public class FragmentC extends Fragment {
    TextView temp_d;
    TextView status_d;
    Button bt;
    List<Datashow> listar_d = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, container, false);

        temp_d = (TextView) view.findViewById(R.id.temp_d);
        status_d = (TextView)view.findViewById(R.id.status_d);
        bt = (Button)view.findViewById(R.id.bt);
        findAllDat();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findAllDat();
            }
        });
        return view;
    }


    /*----------datashow --------------------*/
    public void findAllDat(){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getString(R.string.url_base) + "?acao=3",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray vetor = json.getJSONArray("vetor_d");
                            for(int i=0;i<vetor.length();i++){
                                JSONObject aux = vetor.getJSONObject(i);
                                Datashow obj = new Datashow();
                                obj.setId_d(aux.getString("id_ds"));
                                obj.setTemperatura_d(aux.getString("temperatura_ds"));
                                obj.setStatus_d(aux.getString("status_ds"));

                                listar_d.add(obj);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("pesqui_d", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("pesqui_d", error.toString());
                    }
                });
        requestQueue.add(stringRequest);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                for(int i=0; i<listar_d.size();i++) {
                    Log.i("pesqui_d",listar_d.get(i).getTemperatura_d());
                    Log.i("pesqui_d",listar_d.get(i).getStatus_d());
                    Datashow datashow = listar_d.get(i);

                    temp_d.setText(listar_d.get(i).getTemperatura_d()+"ºC");

                    int status = Integer.parseInt(listar_d.get(i).getStatus_d());
                    if(status > 450){
                        status_d.setText("Preparando");
                    }else{
                        if(status <=450 && status>=400){
                            status_d.setText("Quase pronto");
                        }
                        else if(status<400){
                            status_d.setText("Pronto!");
                        }
                    }
                }
            }
        });
    }

}
