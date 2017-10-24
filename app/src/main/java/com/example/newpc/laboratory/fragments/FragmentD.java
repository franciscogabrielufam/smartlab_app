package com.example.newpc.laboratory.fragments;

import android.graphics.Color;
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
import com.example.newpc.laboratory.Arcondicionado;
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

public class FragmentD extends Fragment {
    ImageView temp_a;
    TextView temp_ar;
    TextView status_a;
    ImageView status_ar;
    ImageView clima;
    Button bt;
    List<Datashow> listar = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d, container, false);

        temp_ar = view.findViewById(R.id.temp_ar);
        temp_a =  view.findViewById(R.id.temp_a);
        status_a = view.findViewById(R.id.status_a);
        status_ar = view.findViewById(R.id.status_ar);
        clima = view.findViewById(R.id.clima);

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
                findAllArc();
            }
        }, 2000);
    }

    private void atualizarDados(Dados dados) {

        /*nessa parte, é criada a estrutura de selecao para que a imagem mude de acordo com o valor da temperatura*/
        Double temp = dados.getTemperatura(); // a variavel temp recebe o valor da temperatura
        if(temp<=5) {
            temp_a.setImageResource(R.drawable.tmenos);
        }else if(temp<=10 && temp >5){
            temp_a.setImageResource(R.drawable.t10);
        }else if(temp>10 && temp<15) {
            temp_a.setImageResource(R.drawable.t10m);
        }else if(temp==15){
            temp_a.setImageResource(R.drawable.t15);
        }else if(temp>15 && temp<20){
            temp_a.setImageResource(R.drawable.t15m);
        }else if(temp==20){
            temp_a.setImageResource(R.drawable.t20);
        }else if(temp>20 && temp<25) {
            temp_a.setImageResource(R.drawable.t20m);
        }else if(temp==25){
            temp_a.setImageResource(R.drawable.t25);
        }else if(temp>25  && temp<30){
            temp_a.setImageResource(R.drawable.t25m);
        }else if(temp==30) {
            temp_a.setImageResource(R.drawable.t30);
        }else if (temp>30 && temp<35){
            temp_a.setImageResource(R.drawable.t30m);
        }else if(temp==35){
            temp_a.setImageResource(R.drawable.t35);
        }else if (temp>35 && temp<40){
            temp_a.setImageResource(R.drawable.t35m);
        }else if (temp==40){
            temp_a.setImageResource(R.drawable.t40);
        }else if(temp>40 && temp<45){
            temp_a.setImageResource(R.drawable.t40m);
        }else if(temp==45){
            temp_a.setImageResource(R.drawable.t45);
        }else if(temp>45 && temp<50){
            temp_a.setImageResource(R.drawable.t45m);
        }else if (temp==50){
            temp_a.setImageResource(R.drawable.t50);
        }else if(temp>50 && temp<55){
            temp_a.setImageResource(R.drawable.t50m);
        }else if(temp==55){
            temp_a.setImageResource(R.drawable.t55);
        }else if (temp>55 && temp<60){
            temp_a.setImageResource(R.drawable.t55m);
        }else if(temp==60){
            temp_a.setImageResource(R.drawable.t60);
        }else if (temp>60 && temp<65){
            temp_a.setImageResource(R.drawable.t60m);
        }else if (temp==65){
            temp_a.setImageResource(R.drawable.t65);
        }else if (temp>65 && temp<70){
            temp_a.setImageResource(R.drawable.t65m);
        }else if (temp==70){
            temp_a.setImageResource(R.drawable.t70);
        }else if(temp>70 && temp <75){
            temp_a.setImageResource(R.drawable.t70m);
        }else if(temp>=75 && temp <80){
            temp_a.setImageResource(R.drawable.tmais);
        }else {
            temp_a.setImageResource(R.drawable.tmais2);
        }//

        //  temp_c.setImageResource(R.drawable.t30);
        temp_ar.setText(dados.getTemperatura()+"º C");
        temp_ar.setTextColor(Color.rgb(107,128,155));


        //Double status = dados.getUmidade();
        if(temp >= 30){
            status_ar.setImageResource(R.drawable.desligado_a);
            status_a.setText("desligado");
          status_a.setTextColor(Color.RED);
        }else{
                status_ar.setImageResource(R.drawable.ligado_a);
                status_a.setText("ligado");
            status_a.setTextColor(Color.rgb(0,230,118));
        }

        //temperatura ambiente
        if(temp>28){
            clima.setImageResource(R.drawable.calor);
        } else if(temp>=25 && temp <=28){
            clima.setImageResource(R.drawable.tempo_bom);
        }else {
            clima.setImageResource(R.drawable.frio);
        }
    }

    /*----------Arcondicionado --------------------*/
    public void findAllArc(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, getString(R.string.url_base) + "?acao=2",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray vetor = json.getJSONArray("vetor_a");
                            JSONObject aux = vetor.getJSONObject(0);

                            Dados dados = new Dados();
                            dados.setId(aux.getInt("id_ac"));
                            dados.setTemperatura(aux.getDouble("temperatura_ac"));
                            dados.setUmidade(aux.getDouble("status_ar"));

                            atualizarDados(dados);
                            atualizar();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("pesqui_a", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("pesqui_a", error.toString());
                    }
                });
        Laboratory.getInstancia().adicionarRequestQueue(stringRequest);
    }


}
