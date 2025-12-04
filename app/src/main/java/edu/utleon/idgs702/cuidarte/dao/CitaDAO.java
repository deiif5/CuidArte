package edu.utleon.idgs702.cuidarte.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import edu.utleon.idgs702.cuidarte.DataBaseHelper;
import edu.utleon.idgs702.cuidarte.modelos.Cita;

public interface CitaDAO {
    private DatabaseHelper dbHelper;
    
    public CitaDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }
    
    // Crear nueva cita
    public long crearCita(Cita cita) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_CITA_ID_USUARIO, cita.getIdUsuario());
        values.put(DatabaseHelper.COLUMN_CITA_FECHA, cita.getFecha());
        values.put(DatabaseHelper.COLUMN_CITA_HORA, cita.getHora());
        values.put(DatabaseHelper.COLUMN_CITA_LUGAR, cita.getLugar());
        values.put(DatabaseHelper.COLUMN_CITA_DOCTOR, cita.getDoctor());
        values.put(DatabaseHelper.COLUMN_CITA_ESPECIALISTA, cita.getEspecialista());
        
        long id = db.insert(DatabaseHelper.TABLE_CITAS, null, values);
        db.close();
        return id;
    }
    
    // Obtener todas las citas de un usuario específico
    public List<Cita> obtenerCitasPorUsuario(int idUsuario) {
        List<Cita> listaCitas = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        
        Cursor cursor = db.query(
            DatabaseHelper.TABLE_CITAS,
            null,
            DatabaseHelper.COLUMN_CITA_ID_USUARIO + "=?",
            new String[]{String.valueOf(idUsuario)},
            null, null,
            DatabaseHelper.COLUMN_CITA_FECHA + " DESC, " + DatabaseHelper.COLUMN_CITA_HORA + " DESC"
        );
        
        if (cursor.moveToFirst()) {
            do {
                Cita cita = new Cita();
                cita.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CITA_ID)));
                cita.setIdUsuario(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CITA_ID_USUARIO)));
                cita.setFecha(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CITA_FECHA)));
                cita.setHora(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CITA_HORA)));
                cita.setLugar(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CITA_LUGAR)));
                cita.setDoctor(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CITA_DOCTOR)));
                cita.setEspecialista(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CITA_ESPECIALISTA)));
                listaCitas.add(cita);
            } while (cursor.moveToNext());
        }
        
        cursor.close();
        db.close();
        return listaCitas;
    }
}
