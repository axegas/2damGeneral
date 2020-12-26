package com.example.crmleads;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class LeadsActivity extends AppCompatActivity {

    private int bo = 0; //con esta variable controlo si el fragment ya esta abierto o no

    private Button btnAbrir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leads);

        btnAbrir = (Button) findViewById(R.id.btnAbrir);


        LeadsFragment leadsFragment = new LeadsFragment();

        btnAbrir.setOnClickListener(b -> {
            if(bo==0){//si el fragment está cerrado, lo abro
                bo = getSupportFragmentManager().beginTransaction().add(R.id.container,leadsFragment).commit();
            }else{//si el fragment está abierto, lo cierro
                getSupportFragmentManager().beginTransaction().remove(leadsFragment).commit();
                bo = 0;
            }
        });


    }
}