package com.example.consultausuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaActvity extends AppCompatActivity {
    private TextView tvEmail;
    private TextView tvNome;
    private ImageView ivFoto;
    private TextView tvNacionalidade;
    private TextView tvGenero;
    private Button btVoltar;

    private Bitmap bmp;
    private String genero="";
    private String nacionalidade="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        tvEmail=findViewById(R.id.tvEmail);
        tvNome=findViewById(R.id.tvNome);
        tvNacionalidade=findViewById(R.id.tvNacionalidade);
        ivFoto=findViewById(R.id.ivFoto);
        tvGenero=findViewById(R.id.tvGenero);
        btVoltar=findViewById(R.id.btVoltar);

        Intent intent=getIntent();

        genero=intent.getStringExtra("genero");
        nacionalidade=intent.getStringExtra("nacionalidade");
        chamarWS();

        btVoltar.setOnClickListener(e->{finish();});
    }

    private void chamarWS(){



        Call<Root> call = new RetrofitConfig().getUserService().buscarGender(genero,nacionalidade);

        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root=response.body();
                Log.i("obs",""+response.body());
                tvNome.setText("Nome: "+root.getResults().get(0).getName());
                tvEmail.setText("Email: "+root.getResults().get(0).getEmail());
                tvNacionalidade.setText("Nacionalidade: "+root.getResults().get(0).getNat());
                tvGenero.setText("Gênero: "+root.getResults().get(0).getGender());
                carregarImagem(root.getResults().get(0).getPicture().getLarge());

            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                //Log.e("CEPService   ", "Erro ao buscar o cep:" + t.getMessage());

            }
        });





    }
    protected void carregarImagem(final String Url)
    {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    URL url = new URL(Url);
                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ivFoto.setImageBitmap(bmp);
    }
}