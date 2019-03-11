package mx.edu.ittepic.miriambarajas.tpdm_u2_practica1_miriamjanethbarajaslopez;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView proyectos;
    Proyectos vector[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proyectos = findViewById(R.id.listaproyectos);

        proyectos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarAlerta(position);
            }
        });

    }

    private void mostrarAlerta(final int position) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atención")
                .setMessage("Deseas editar/modificar el proyecto "+vector[position].getDesc()+"")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eliminarActualizar(position);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void eliminarActualizar(int position) {
        Intent eliminarActualizar = new Intent(this,Main2Activity.class);

        eliminarActualizar.putExtra("id",vector[position].getId());
        eliminarActualizar.putExtra("descripcion", vector[position].getDesc());
        eliminarActualizar.putExtra("ubicacion", vector[position].getUbi());
        eliminarActualizar.putExtra("fecha",vector[position].getFecha());
        eliminarActualizar.putExtra("presupuesto",vector[position].getPres());

        startActivity(eliminarActualizar);
    }

    protected void onStart() {
        Proyectos p = new Proyectos(this);
        vector = p.consultarT();
        String[] descripcionP = null;


        if(vector == null){
            descripcionP = new String[1];
            descripcionP[0] = "No hay proyectos capturados";
        }
        else{
            descripcionP = new String[vector.length];
            for(int i=0; i<vector.length; i++){
                Proyectos temp = vector[i];
                descripcionP[i] = temp.getDesc()+"\n"+temp.getUbi()+"\n"+temp.getFecha()+"\n"+temp.getPres();
            }
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,descripcionP);
        proyectos.setAdapter(adaptador);
        super.onStart();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insertar:
                Intent nuevoProyecto = new Intent(this, Main2Activity.class);
                startActivity(nuevoProyecto);
                break;
            case R.id.actualizar:
                Intent actualizarProyecto = new Intent(this, Main3Activity.class);
                startActivity(actualizarProyecto);
                break;
            case R.id.eliminar:
                Intent eliminarProyecto = new Intent(this, Main3Activity.class);
                startActivity(eliminarProyecto);
                break;
            case R.id.consultar:
                Intent consultarProyecto = new Intent(this, Main3Activity.class);
                startActivity(consultarProyecto);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

