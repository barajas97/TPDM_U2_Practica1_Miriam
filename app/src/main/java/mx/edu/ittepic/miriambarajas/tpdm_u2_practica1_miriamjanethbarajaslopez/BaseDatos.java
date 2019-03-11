package mx.edu.ittepic.miriambarajas.tpdm_u2_practica1_miriamjanethbarajaslopez;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PROYECTOS(IDPROYECTO INT PRIMARY KEY AUTOINCREMENT NOT NULL, DESCRIPCION VARCHAR(200) NOT NULL," +
                "UBICACION VARCHAR(200), FECHA DATE, PRESUPUESTO FLOAT)");
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
