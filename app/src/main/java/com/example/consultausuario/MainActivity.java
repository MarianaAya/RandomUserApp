package com.example.consultausuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btConsultar;
    private Spinner spNacionalidade;
    private String itemsel="";
    private String genero="";
    private RadioGroup rgGenero;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btConsultar=findViewById(R.id.btConsultar);
        spNacionalidade=findViewById(R.id.spNacionalidade);
        rgGenero=findViewById(R.id.rgGenero);




        List<String> atemps = new ArrayList();
        atemps.addAll(Arrays.asList("AU","BR","CA","CH","DE","DK","ES","FI",
                                    "FR","GB","IE","IR","NO","NL","NZ","TR","US"));
        ArrayAdapter<String> adapter2 = new
                ArrayAdapter(this,android.R.layout.simple_spinner_item,atemps);
        spNacionalidade.setAdapter(adapter2);

        spNacionalidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                itemsel=adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        rgGenero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = (RadioButton) group.findViewById(checkedId);
                String resposta = button.getText().toString();
                if(resposta.equals("Feminino"))
                    genero="female";
                else
                    genero="male";
            }
        });
        btConsultar.setOnClickListener(e->{trocarPagina();});

    }
    private void trocarPagina(){

        if(genero.isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "Gênero não foi selecionado",Toast.LENGTH_SHORT).show();

        }
        else {
            Intent intent = new Intent(this, ListaActvity.class);
            intent.putExtra("genero", genero);
            intent.putExtra("nacionalidade", itemsel);
            startActivity(intent);
        }

    }



}