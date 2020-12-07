package com.example.fragmentosejemplo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class BlankFragment extends Fragment {

    Button boton;
    EditText edit;
    TextView text;


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_blank, container, false);





        boton = (Button) view.findViewById(R.id.main_button);
        edit = (EditText) view.findViewById(R.id.main_editText);
        text = (TextView) view.findViewById(R.id.main_textview);

        return inflater.inflate(R.layout.fragment_blank, container, false);
    }
}