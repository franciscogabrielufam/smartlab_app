package com.example.newpc.laboratory.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.newpc.laboratory.aplicacao.Laboratory;
import com.example.newpc.laboratory.dominio.Dados;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francisco Gabriel on 13/10/2017.
 */

public class FragmentC extends Fragment {
    ImageView temp_d;
    TextView temp_da;
    TextView status_d;
    ImageView status_da;
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

        temp_da = view.findViewById(R.id.temp_da);
        temp_d =  view.findViewById(R.id.temp_d);
        status_d = view.findViewById(R.id.status_d);
        status_da = view.findViewById(R.id.status_da);

        //bt = view.findViewById(R.id.bt);
        atualizar();
/*        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findAllCaf();
            }
        });*/
        return view;
    }
    public void atualizar() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findAllDat();
            }
        }, 2000);
    }

    private void atualizarDados(Dados dados) {

        /*nessa parte, é criada a estrutura de selecao para que a imagem mude de acordo com o valor da temperatura*/
        Double temp = dados.getTemperatura(); // a variavel temp recebe o valor da temperatura
        if(temp<=10){
            temp_d.setImageResource(R.drawable.t10);
        }else if(temp>10 && temp<15) {
            temp_d.setImageResource(R.drawable.t10m);
        }else if(temp==15){
            temp_d.setImageResource(R.drawable.t15);
        }else if(temp>15 && temp<20){
            temp_d.setImageResource(R.drawable.t15m);
        }else if(temp==20){
            temp_d.setImageResource(R.drawable.t20);
        }else if(temp>20 && temp<25) {
            temp_d.setImageResource(R.drawable.t20m);
        }else if(temp==25){
            temp_d.setImageResource(R.drawable.t25);
        }else if(temp>25  && temp<30){
            temp_d.setImageResource(R.drawable.t25m);
        }else if(temp==30) {
            temp_d.setImageResource(R.drawable.t30);
        }else if (temp>30 && temp<35){
            temp_d.setImageResource(R.drawable.t30m);
        }else if(temp==35){
            temp_d.setImageResource(R.drawable.t35);
        }else if (temp>35 && temp<40){
            temp_d.setImageResource(R.drawable.t35m);
        }else if (temp==40){
            temp_d.setImageResource(R.drawable.t40);
        }else if(temp>40 && temp<45){
            temp_d.setImageResource(R.drawable.t40m);
        }else if(temp==45){
            temp_d.setImageResource(R.drawable.t45);
        }else if(temp>45 && temp<50){
            temp_d.setImageResource(R.drawable.t45m);
        }else if (temp==50){
            temp_d.setImageResource(R.drawable.t50);
        }else if(temp>50 && temp<55){
            temp_d.setImageResource(R.drawable.t50m);
        }else if(temp==55){
            temp_d.setImageResource(R.drawable.t55);
        }else if (temp>55 && temp<60){
            temp_d.setImageResource(R.drawable.t55m);
        }else if(temp==60){
            temp_d.setImageResource(R.drawable.t60);
        }else if (temp>60 && temp<65){
            temp_d.setImageResource(R.drawable.t60m);
        }else if (temp==65){
            temp_d.setImageResource(R.drawable.t65);
        }else if (temp>65 && temp<70){
            temp_d.setImageResource(R.drawable.t65m);
        }else if (temp==70){
            temp_d.setImageResource(R.drawable.t70);
        }else if(temp>70 && temp <75){
            temp_d.setImageResource(R.drawable.t70m);
        }else {
            temp_d.setImageResource(R.drawable.tmais);
        }//

        //  temp_c.setImageResource(R.drawable.t30);
        temp_da.setText(dados.getTemperatura()+"º C");


        Double status = dados.getLuminosidade();
        if(status > 450){
            status_da.setImageResource(R.drawable.preparando);
            status_d.setText("Preparando");
        }else{
            if(status <=450 && status>=400){
                status_da.setImageResource(R.drawable.quase_pronto);
                status_d.setText("Quase pronto");
            }
            else if(status<400){
                status_da.setImageResource(R.drawable.pronto);
                status_d.setText("Pronto!");
            }
        }
    }

    /*----------datashow --------------------*/
    public void findAllDat(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, getString(R.string.url_base) + "?acao=3",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray vetor = json.getJSONArray("vetor_d");
                            JSONObject aux = vetor.getJSONObject(0);

                            Dados dados = new Dados();
                            dados.setId(aux.getInt("id_ds"));
                            dados.setTemperatura(aux.getDouble("temperatura_ds"));
                            dados.setLuminosidade(aux.getDouble("status_ds"));

                            atualizarDados(dados);

                            atualizar();
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
        Laboratory.getInstancia().adicionarRequestQueue(stringRequest);
    }


}
