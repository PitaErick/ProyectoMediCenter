package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consulta {

    private Paciente paciente;
    private Medico medico;
    private int id;
    private LocalDateTime fechayhora;
    private String consultorio;
    private String diagnostico;
    private String receta;

    public Consulta(Paciente paciente, Medico medico, int id, LocalDateTime fechayhora, String consultorio, String diagnostico, String receta) {
        this.paciente = paciente;
        this.medico = medico;
        this.id = id;
        this.fechayhora = fechayhora;
        this.consultorio = consultorio;
        this.diagnostico = diagnostico;
        this.receta = receta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public String getNombreMedico() {
        return medico.getNombre()+" "+medico.getApellidos();
    }

    public String getNombrePaciente() {
        return paciente.getNombre()+" "+paciente.getApellidos();
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechayhora() {
        return fechayhora;
    }
    public String getFormatFechayhora() {
        return fechayhora.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public void setFechayhora(LocalDateTime fechayhora) {
        this.fechayhora = fechayhora;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

}
