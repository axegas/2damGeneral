package com.example.calcluladorafragmentos;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAbrir;
    private int bo = 0; //con esta variable controlo si el fragment ya esta abierto o no

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main_dinamico); //fragmento embebido de forma programática (dinámica)
        setContentView(R.layout.activity_main_estatico); //fragmento embebido de forma declarativa (estática)



        CalculadoraFragmento fragment = new CalculadoraFragmento();

        btnAbrir = (Button) findViewById(R.id.btnAbrir);

        btnAbrir.setOnClickListener(b -> {
            if(bo==0){//si el fragment está cerrado, lo abro
                bo = getSupportFragmentManager().beginTransaction().add(R.id.contenedor,fragment).commit();
            }else{//si el fragment está abierto, lo cierro
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                bo = 0;
            }
        });

    }
}