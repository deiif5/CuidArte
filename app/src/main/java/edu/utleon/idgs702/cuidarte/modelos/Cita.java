package edu.utleon.idgs702.cuidarte.modelos;

public class Cita {
    private int id;
    private int idUsuario;
    private String fecha;
    private String hora;
    private String lugar;
    private String doctor;
    private String especialista;

    public Cita() {
    }

    // Constructor completo (con ID)
    public Cita(int id, int idUsuario, String fecha, String hora, String lugar, String doctor, String especialista) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.doctor = doctor;
        this.especialista = especialista;
    }

    // Constructor sin ID (para insertar nuevos registros)
    public Cita(int idUsuario, String fecha, String hora, String lugar, String doctor, String especialista) {
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.doctor = doctor;
        this.especialista = especialista;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getLugar() {
        return lugar;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getEspecialista() {
        return especialista;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setEspecialista(String especialista) {
        this.especialista = especialista;
    }

    // toString
    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", lugar='" + lugar + '\'' +
                ", doctor='" + doctor + '\'' +
                ", especialista='" + especialista + '\'' +
                '}';
    }
}
