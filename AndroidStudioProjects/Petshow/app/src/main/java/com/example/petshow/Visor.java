package com.example.petshow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Visor extends AppCompatActivity {

   RadioGroup opiniones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor);


        TextView image_name = (TextView) findViewById(R.id.nombre_mascota);
        opiniones = (RadioGroup) findViewById(R.id.radioGroup);

        Button aceptar = (Button) findViewById(R.id.button);
        aceptar.setOnClickListener(b -> volver());

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NOMBRE);
        image_name.setText(name);


    }

    private void volver() {

        //Obtener del radiobutton seleccionado actualmente
        RadioButton currentRadio = (RadioButton) findViewById(opiniones.getCheckedRadioButtonId());

        //Obtener la cadena del radiobutton
        String opinion = currentRadio.getText().toString();

        //Crear un nuevo intent de respuesta
        Intent databack = new Intent();

        //Añadir como Extra el texto del radiobutton
        databack.putExtra("opinion",opinion);

        //Devolver por el canal de forma exitosa el mensaje del intent
        setResult(RESULT_OK,databack);

        //Terminar la actividad
        finish();
    }

}