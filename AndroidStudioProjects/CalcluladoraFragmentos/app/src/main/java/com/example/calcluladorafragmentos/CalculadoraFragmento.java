package com.example.calcluladorafragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CalculadoraFragmento extends Fragment {

    private View v;
    //TextViews
    private TextView tvResultado; //para mostrar el resultado
    private TextView tvOperacion; //para mostrar, si es el caso, la operación seleccionada

    //botones
    //números
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;
    private Button btnPunto;

    //operaciones
    private Button btnC;
    private Button btnBorrar;
    private Button btnMultiplicacion;
    private Button btnDivision;
    private Button btnResta;
    private Button btnSuma;
    private Button btnIgual;

    private double numero;//número guardado para calcular
    private int IDoperacion;//ID de la operación en curso

    public CalculadoraFragmento() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_calculadora_fragmento, container, false);

        initViews();
        initButtons();

        return v;
    }

    //busco los elementos a partir de la ID
    private void initViews() {
        tvResultado = (TextView) v.findViewById(R.id.tvResultado);
        tvOperacion = (TextView) v.findViewById(R.id.tvOperacion);

        btn1 = (Button) v.findViewById(R.id.btn1);
        btn2 = (Button) v.findViewById(R.id.btn2);
        btn3 = (Button) v.findViewById(R.id.btn3);
        btn4 = (Button) v.findViewById(R.id.btn4);
        btn5 = (Button) v.findViewById(R.id.btn5);
        btn6 = (Button) v.findViewById(R.id.btn6);
        btn7 = (Button) v.findViewById(R.id.btn7);
        btn8 = (Button) v.findViewById(R.id.btn8);
        btn9 = (Button) v.findViewById(R.id.btn9);
        btn0 = (Button) v.findViewById(R.id.btn0);
        btnPunto = (Button) v.findViewById(R.id.btnPunto);

        btnMultiplicacion = (Button) v.findViewById(R.id.btnMultiplicacion);
        btnDivision = (Button) v.findViewById(R.id.btnDivision);
        btnResta = (Button) v.findViewById(R.id.btnResta);
        btnSuma = (Button) v.findViewById(R.id.btnSuma);
        btnIgual = (Button) v.findViewById(R.id.btnIgual);

        btnBorrar = (Button) v.findViewById(R.id.btnBorrar);
        btnC = (Button) v.findViewById(R.id.btnC);
    }

    //en este método inicializo los listeners de los botones
    private void initButtons() {
        btn1.setOnClickListener(b -> clickNumero((Button) b));
        btn2.setOnClickListener(b -> clickNumero((Button) b));
        btn3.setOnClickListener(b -> clickNumero((Button) b));
        btn4.setOnClickListener(b -> clickNumero((Button) b));
        btn5.setOnClickListener(b -> clickNumero((Button) b));
        btn6.setOnClickListener(b -> clickNumero((Button) b));
        btn7.setOnClickListener(b -> clickNumero((Button) b));
        btn8.setOnClickListener(b -> clickNumero((Button) b));
        btn9.setOnClickListener(b -> clickNumero((Button) b));
        btn0.setOnClickListener(b -> clickNumero((Button) b));
        btnPunto.setOnClickListener(b -> clickNumero((Button) b));

        btnMultiplicacion.setOnClickListener(b -> clickOperacion((Button) b));
        btnDivision.setOnClickListener(b -> clickOperacion((Button) b));
        btnResta.setOnClickListener(b -> clickOperacion((Button) b));
        btnSuma.setOnClickListener(b -> clickOperacion((Button) b));
        btnIgual.setOnClickListener(b -> clickOperacion((Button) b));

        btnBorrar.setOnClickListener(b -> clickBorrar());
        btnC.setOnClickListener(b -> clickC());
    }

    //para borrar la última cifra del número en pantalla
    private void clickC() {
        if(tvResultado.getText().equals("")){
            return;
        }
        String str = tvResultado.getText().toString();
        str = str.substring(0,str.length()-1);
        if(str.charAt(str.length()-1)=='.'){
            str = str.substring(0,str.length()-1);
        }
        tvResultado.setText(str);
    }

    //borrar toda la pantalla y los datos acumulados
    private void clickBorrar() {
        numero = 0;
        IDoperacion = 0;
        tvResultado.setText("");
        tvOperacion.setText("");
    }

    //capturar la operación a realizar y el número en pantalla. las operaciones se acumulan
    private void clickOperacion(Button b){
        if(tvResultado.getText().equals("")){
            return;
        }
        numero = calcula(IDoperacion);
        IDoperacion = b.getId();
        if(IDoperacion==R.id.btnIgual){
            tvResultado.setText(Double.toString(numero));
            tvOperacion.setText("");
        }else{
            tvOperacion.setText(numero + " " + b.getText());
            tvResultado.setText("");
        }
    }

    //mostrar en pantalla el número pulsado
    private void clickNumero(Button b) {
        String tv = tvResultado.getText().toString();
        if(b.getId()==R.id.btnPunto && (tv.contains(".") || tv.equals(""))) // validar si, en el caso de que haya pulsado el punto, sea correcto introducirlo
            return;
        tv += b.getText();
        tvResultado.setText(tv);
    }

    //funcion auxiliar para calcular
    private double calcula(int n){
        double aux = Double.parseDouble(tvResultado.getText().toString());
        double resultado;
        switch (n){
            case R.id.btnMultiplicacion:
                resultado = numero * aux;
                break;
            case R.id.btnResta:
                resultado = numero - aux;
                break;
            case R.id.btnSuma:
                resultado = numero + aux;
                break;
            case R.id.btnDivision:
                resultado = numero / aux;
                break;
            default:
                resultado = aux;
                break;
        }
        return resultado;
    }
}