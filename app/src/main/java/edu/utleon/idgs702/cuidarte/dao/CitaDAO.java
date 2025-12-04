package edu.utleon.idgs702.cuidarte.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import edu.utleon.idgs702.cuidarte.database.DataBaseHelper;
import edu.utleon.idgs702.cuidarte.modelos.Cita;


public class CitaDAO {
    private DataBaseHelper dbHelper;

    public void CitaDAO(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    // Crear nueva cita
    public long crearCita(Cita cita) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idUsuario", cita.getIdUsuario());
        values.put("fecha", cita.getFecha());
        values.put("hora", cita.getHora());
        values.put("lugar", cita.getLugar());
        values.put("doctor", cita.getDoctor());
        values.put("especialista", cita.getEspecialista());

        long id = db.insert("cita", null, values);
        db.close();
        return id;
    }

    // Obtener todas las citas de un usuario específico
    public List<Cita> obtenerCitasPorUsuario(int idUsuario) {
        List<Cita> listaCitas = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "cita",
                null,
                "idUsuario=?",
                new String[]{String.valueOf(idUsuario)},
                null, null,
                "fecha DESC, hora DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                Cita cita = new Cita();
                cita.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                cita.setIdUsuario(cursor.getInt(cursor.getColumnIndexOrThrow("idUsuario")));
                cita.setFecha(cursor.getString(cursor.getColumnIndexOrThrow("fecha")));
                cita.setHora(cursor.getString(cursor.getColumnIndexOrThrow("hora")));
                cita.setLugar(cursor.getString(cursor.getColumnIndexOrThrow("lugar")));
                cita.setDoctor(cursor.getString(cursor.getColumnIndexOrThrow("doctor")));
                cita.setEspecialista(cursor.getString(cursor.getColumnIndexOrThrow("especialista")));
                listaCitas.add(cita);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listaCitas;
    }
}
