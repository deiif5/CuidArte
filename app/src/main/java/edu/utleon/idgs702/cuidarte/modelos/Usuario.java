package edu.utleon.idgs702.cuidarte.modelos;

public class Usuario {
    private int id;
    private String nombre;
    private String apellidos;
    private String usuario;
    private int edad;
    private int pin;

    public Usuario() {
    }

    // Constructor completo (con ID)
    public Usuario(int id, String nombre, String apellidos, String usuario, int edad, int pin) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.edad = edad;
        this.pin = pin;
    }

    // Constructor sin ID (para insertar nuevos registros)
    public Usuario(String nombre, String apellidos, String usuario, int edad, int pin) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.edad = edad;
        this.pin = pin;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getEdad() {
        return edad;
    }

    public int getPin() {
        return pin;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    // toString
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", usuario='" + usuario + '\'' +
                ", edad=" + edad +
                ", pin=" + pin +
                '}';
    }
}
