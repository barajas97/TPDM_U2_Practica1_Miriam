package mx.edu.ittepic.miriambarajas.tpdm_u2_practica1_miriamjanethbarajaslopez;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    Button consultar,cerrar;
    EditText dato;
    TextView de, ub, fe, pr;
    RadioButton rd;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        consultar = findViewById(R.id.consultarbtn);
        cerrar = findViewById(R.id.cerrarbtn);
        dato = findViewById(R.id.buscar);
        de = findViewById(R.id.descripciontxt);
        ub = findViewById(R.id.ubicaciontxt);
        fe = findViewById(R.id.fechatxt);
        pr = findViewById(R.id.presupuestotxt);
        rd = findViewById(R.id.descripcionrd);

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Bundle parametros = getIntent().getExtras();
        de.setText("Descripción: "+parametros.getString("descripcion"));
        ub.setText("Ubicacion: "+parametros.getString("ubicacion"));
        fe.setText("Fecha: "+parametros.getString("fecha"));
        pr.setText("Presupuesto: "+parametros.getString("presupuesto"));
        id = parametros.getInt("id");


        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Proyectos p = new Proyectos(this);
                Proyectos[] respuesta = p.consultar("ID", dato.getText().toString());

                if (rd.isChecked()){
                    respuesta = p.consultar("DESCRIPCION", dato.getText().toString());
                }
            }
        });
    }
}
