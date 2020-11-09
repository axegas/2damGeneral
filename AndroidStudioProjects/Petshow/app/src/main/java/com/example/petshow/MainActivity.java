package com.example.petshow;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_NOMBRE = "com.example.petshow.Visor";
    public final static int OPINION_REQUEST_CODE = 1;
    private Button btnVerPerrito;
    private TextView tvValoracion;
    private TextView linkAutores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvValoracion = (TextView) findViewById(R.id.tvValoracion);
        linkAutores = (TextView) findViewById(R.id.linkAutores);

        //Obteniendo una instancia del boton show_pet_button
        btnVerPerrito = (Button)findViewById(R.id.btnVerPerrito);

        btnVerPerrito.setOnClickListener(b -> ir(b));
        linkAutores.setOnClickListener(t -> ir(t));

    }

    private void ir(View v){

        switch (v.getId()) {
            case R.id.btnVerPerrito:{
                //Iniciando la actividad Visor
                Intent intent = new Intent(this, Visor.class);
                //Adhesion de nuestra cadena
                intent.putExtra(EXTRA_NOMBRE, "pet1.jpg");
                //Inicio de la actividad esperando un resultado
                startActivityForResult(intent,OPINION_REQUEST_CODE);
                break;
            }
            case R.id.linkAutores:{
                Uri webpage = Uri.parse("http://hermosaprogramacion.blogspot.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

                // Verificar si hay aplicaciones disponibles
                PackageManager packageManager = getPackageManager();
                List activities = packageManager.queryIntentActivities(webIntent, 0);
                boolean isIntentSafe = activities.size() > 0;

                // Si hay, entonces ejecutamos la actividad
                if (isIntentSafe) {
                    startActivity(webIntent);
                }

                break;
            }
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPINION_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String result=data.getStringExtra("opinion");
                tvValoracion.setText("Tu opinion fué "+result);

            }
        }
    }
}