package com.example.calcluladorafragmentos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnAbrir;
    int bo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalculadoraFragmento fragment = new CalculadoraFragmento();

        btnAbrir = (Button) findViewById(R.id.btnAbrir);

        btnAbrir.setOnClickListener(b -> {
            if(bo==0){
                bo = getSupportFragmentManager().beginTransaction().add(R.id.contenedor,fragment).commit();
            }else{
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                bo = 0;
            }
        });

    }
}