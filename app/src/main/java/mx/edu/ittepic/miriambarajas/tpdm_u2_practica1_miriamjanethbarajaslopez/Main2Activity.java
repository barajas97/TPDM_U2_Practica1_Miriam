package mx.edu.ittepic.miriambarajas.tpdm_u2_practica1_miriamjanethbarajaslopez;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    EditText des, ubi, fecha, pres;
    Button guarda, regresa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        des = findViewById(R.id.descripción);
        ubi = findViewById(R.id.ubicacion);
        fecha = findViewById(R.id.fecha);
        pres = findViewById(R.id.presupuesto);
        guarda = findViewById(R.id.guardar);
        regresa = findViewById(R.id.regresar);

        guarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    insertar();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void insertar() throws ParseException {
        String mensaje = "";
        Proyectos p = new Proyectos(this);

        boolean respuesta = p.insertar(new Proyectos(0,des.getText().toString(), ubi.getText().toString(),new SimpleDateFormat("AAAA/MM/DD").parse(fecha.getText().toString()), Float.parseFloat(pres.getText().toString())));
        if(respuesta){
            mensaje = "Se pudo insertar el proyecto "+des.getText().toString();
        }else {
            mensaje = "Error, no se pudo insertar el proyecto"+ p.error;
        }

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atención")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar",null)
                .show();
    }
}
