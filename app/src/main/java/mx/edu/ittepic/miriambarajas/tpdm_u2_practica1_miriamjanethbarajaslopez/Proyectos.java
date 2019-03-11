package mx.edu.ittepic.miriambarajas.tpdm_u2_practica1_miriamjanethbarajaslopez;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Proyectos {

    private BaseDatos base;
    private String desc, ubi;
    private Date fecha;
    private int id;
    private float pres;
    protected String error="";

    public Proyectos(View.OnClickListener activity){
        base = new BaseDatos(activity,"Civil",null,1 );
    }

    public Proyectos(int id, String descripcion, String ubicacion, Date fecha, float presupuesto){
        this.id = id;
        this.desc = descripcion;
        this.ubi = ubicacion;
        this.fecha = fecha;
        this.pres = presupuesto;
    }

    public boolean insertar(Proyectos p){

        try{
            SQLiteDatabase insercion = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", p.getDesc());
            datos.put("UBICACION", p.getUbi());
            datos.put("FECHA", getFecha()+"" );
            datos.put("PRESUPUESTO", p.getPres());

            long resultado = insercion.insert("PROYECTOS", "IDPROYECTOS", datos);
            insercion.close();
            if(resultado == -1) return false;

        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean actualizar(Proyectos p){
        try{
            SQLiteDatabase actualizar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", p.getDesc());
            datos.put("UBICACION", p.getUbi());
            datos.put("FECHA", getFecha()+"" );
            datos.put("PRESUPUESTO", p.getPres());
            String idP = p.getId()+"";
            String idProyecto[] = {idP};

            long resultado = actualizar.update("PROYECTOS", datos, "WHERE IDPROYECTO = ?",idProyecto);
            actualizar.close();
            if(resultado == -1) return false;

        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean eliminar(Proyectos p){
        int resultado;
        try{
            SQLiteDatabase eliminar = base.getWritableDatabase();
            String idP = p.getId()+"";
            String idProyectos[] = {idP};

            resultado = eliminar.delete("PROYECTOS", "WHERE IDPROYECTO = ?",idProyectos);
            eliminar.close();

        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return resultado>0;
    }

    public Proyectos[] consultar(String columna, String clave){
        Proyectos[] resultado= null;
        try{
            SQLiteDatabase transacciónConsulta = base.getReadableDatabase();
            String SQL = "SELECT * FROM PROYECTOS WHERE IDPROYECTO="+clave;

            if(columna.startsWith("DESCRIPCION")){
                SQL = "SELECT * FROM PROYECTOS WHERE DESCRIPCION = '"+clave+"'";
            }
            Cursor c = transacciónConsulta.rawQuery(SQL, null);
            if(c.moveToFirst()){
                resultado = new Proyectos[c.getCount()];
                int pos = 0;
                do{
                    resultado[pos]=new Proyectos(c.getInt(0), c.getString(1),
                            c.getString(2),new SimpleDateFormat("AAAA/MM/DD").parse(c.getString(3)), c.getFloat(4));
                    pos++;
                }while (c.moveToNext());
            }
            transacciónConsulta.close();
        }catch (SQLiteException e){
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultado;
    }
    public Proyectos[] consultarT(){
        Proyectos[] resultado= null;
        try{
            SQLiteDatabase transacciónConsultaT = base.getReadableDatabase();
            String SQL = "SELECT * FROM PROYECTOS";
            Cursor c = transacciónConsultaT.rawQuery(SQL, null);
            if(c.moveToFirst()){
                resultado = new Proyectos[c.getCount()];
                int pos = 0;
                do{
                    resultado[pos]=new Proyectos(c.getInt(0), c.getString(1),
                            c.getString(2),new SimpleDateFormat("AAAA/MM/DD").parse(c.getString(3)), c.getFloat(4));
                    pos++;
                }while (c.moveToNext());
            }
            transacciónConsultaT.close();
        }catch (SQLiteException e){
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultado;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUbi() {
        return ubi;
    }

    public void setUbi(String ubi) {
        this.ubi = ubi;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public float getPres() {
        return pres;
    }
}
