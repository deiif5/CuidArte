package edu.utleon.idgs702.cuidarte.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import edu.utleon.idgs702.cuidarte.DataBaseHelper;
import edu.utleon.idgs702.cuidarte.modelos.Usuario;

public interface UsuarioDAO {
    private DataBaseHelper dbHelper;
    public UsuarioDAO(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    // Registrar nuevo usuario
    public long registrarUsuario(Usuario usuario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USUARIO_NOMBRE, usuario.getNombre());
        values.put(DatabaseHelper.COLUMN_USUARIO_APELLIDOS, usuario.getApellidos());
        values.put(DatabaseHelper.COLUMN_USUARIO_USUARIO, usuario.getUsuario());
        values.put(DatabaseHelper.COLUMN_USUARIO_EDAD, usuario.getEdad());
        values.put(DatabaseHelper.COLUMN_USUARIO_PIN, usuario.getPin());
        
        long id = db.insert(DatabaseHelper.TABLE_USUARIOS, null, values);
        db.close();
        return id;
    }
    
    // Iniciar sesión (verificar usuario y pin)
    public Usuario iniciarSesion(String usuario, int pin) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Usuario usuarioEncontrado = null;
        
        Cursor cursor = db.query(
            DatabaseHelper.TABLE_USUARIOS,
            null,
            DatabaseHelper.COLUMN_USUARIO_USUARIO + "=? AND " + DatabaseHelper.COLUMN_USUARIO_PIN + "=?",
            new String[]{usuario, String.valueOf(pin)},
            null, null, null
        );
        
        if (cursor.moveToFirst()) {
            usuarioEncontrado = new Usuario();
            usuarioEncontrado.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USUARIO_ID)));
            usuarioEncontrado.setNombre(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USUARIO_NOMBRE)));
            usuarioEncontrado.setApellidos(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USUARIO_APELLIDOS)));
            usuarioEncontrado.setUsuario(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USUARIO_USUARIO)));
            usuarioEncontrado.setEdad(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USUARIO_EDAD)));
            usuarioEncontrado.setPin(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USUARIO_PIN)));
        }
        
        cursor.close();
        db.close();
        return usuarioEncontrado;
    }
    
    // Método adicional: verificar si un nombre de usuario ya existe
    public boolean usuarioExiste(String usuario) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        
        Cursor cursor = db.query(
            DatabaseHelper.TABLE_USUARIOS,
            new String[]{DatabaseHelper.COLUMN_USUARIO_ID},
            DatabaseHelper.COLUMN_USUARIO_USUARIO + "=?",
            new String[]{usuario},
            null, null, null
        );
        
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return existe;
    }
}
