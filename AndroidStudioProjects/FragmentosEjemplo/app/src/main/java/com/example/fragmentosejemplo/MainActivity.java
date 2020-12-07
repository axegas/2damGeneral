package com.example.fragmentosejemplo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button) findViewById(R.id.main_button);



        BlankFragment fragment = new BlankFragment();


        boton.setOnClickListener(b -> {
            getSupportFragmentManager().beginTransaction().add(R.id.contenedor,fragment).commit();
        });

    }
}