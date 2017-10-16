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
import com.example.newpc.laboratory.Arcondicionado;
import com.example.newpc.laboratory.Cafeteira;
import com.example.newpc.laboratory.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francisco Gabriel on 13/10/2017.
 */

public class FragmentD extends Fragment {
    TextView temp_a;
    TextView status_a;
    Button bt;
    List<Arcondicionado> listar = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d, container, false);

        temp_a = (TextView) view.findViewById(R.id.temp_a);
        status_a = (TextView)view.findViewById(R.id.status_a);
        bt = (Button)view.findViewById(R.id.bt);
        findAllArc();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findAllArc();
            }
        });
        return view;
    }


    /*----------Arcondicionado --------------------*/
    public void findAllArc(){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getString(R.string.url_base) + "?acao=2",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray vetor = json.getJSONArray("vetor_a");
                            for(int i=0;i<vetor.length();i++){
                                JSONObject aux = vetor.getJSONObject(i);
                                Arcondicionado obj = new Arcondicionado();
                                obj.setId_a(aux.getString("id_ac"));
                                obj.setTemperatura_a(aux.getString("temperatura_ac"));
                                obj.setStatus_a(aux.getString("status_ar"));

                                listar.add(obj);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("pesqui", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("pesqui", error.toString());
                    }
                });
        requestQueue.add(stringRequest);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                for(int i=0; i<listar.size();i++) {
                    Log.i("pesqui",listar.get(i).getTemperatura_a());
                    Log.i("pesqui",listar.get(i).getStatus_a());
                    Arcondicionado arcondicionado = listar.get(i);

                    temp_a.setText(listar.get(i).getTemperatura_a()+"ºC");


                    int status = Integer.parseInt(listar.get(i).getStatus_a());
                    if(status > 450){
                        status_a.setText("Preparando");
                    }else{
                        if(status <=450 && status>=400){
                            status_a.setText("Quase pronto");
                        }
                        else if(status<400){
                            status_a.setText("Pronto!");
                        }
                    }

                }
            }
        });
    }


}
