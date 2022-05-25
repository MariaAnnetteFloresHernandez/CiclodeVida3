package com.example.ciclodevida3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.ciclodevida3.Datos.Datos;
import com.example.ciclodevida3.Modelo.VistaModelo;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    EditText tnombre, tedad;
    GridView gvdatos;
    Button btnagregar;

    VistaModelo vistaModelo;
    List<Datos> listadatos = new ArrayList<>();
    LiveData<List<Datos>> lista;
    ArrayList<String> listagrid = new ArrayList<String>();

    private Observer<List<Datos>> observar = new Observer<List<Datos>>() {
        @Override
        public void onChanged(List<Datos> datosList) {
            listagrid.clear();
            cargarGrid(datosList);
            /*listadatos = datosList;
            listagrid.add("Nombre");
            listagrid.add("Edad");
            ArrayAdapter<String> adapter;
            for (Datos datos : datosList){
                listagrid.add(datos.getNombre());
                listagrid.add(String.valueOf(datos.getEdad()));
            }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listagrid);
            gvdatos.setAdapter(adapter);*/

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        tnombre = findViewById(R.id.txtnombre);
        tedad = findViewById(R.id.txtedad);
        gvdatos = findViewById(R.id.gridDatos);
        btnagregar = findViewById(R.id.btnagregar);

        vistaModelo = new ViewModelProvider(this).get(VistaModelo.class);
        vistaModelo.getListaDatos().observe(this,observar);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Datos datos = new Datos(tnombre.getText().toString(),Integer.valueOf(tedad.getText().toString()));
                vistaModelo.Agregar(datos);
            }
        });
    }

    private void cargarGrid(List<Datos> datosList){
        listadatos = datosList;
        listagrid.add("Nombre");
        listagrid.add("Edad");
        ArrayAdapter<String> adapter;

        for(Datos dato : listadatos){
            listagrid.add(dato.getNombre());
            listagrid.add(String.valueOf(dato.getEdad()));
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listagrid);
        gvdatos.setAdapter(adapter);

    }
}