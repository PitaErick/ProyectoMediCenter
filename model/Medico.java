package model;

import validate.Validate;
import java.util.ArrayList;

public class Medico extends Persona {

    private String carnet;
    private Especialidad especialidad;

    public Medico(String nombre, String apellidos, Telefono numero, String cedula, Direccion direccion, Especialidad especialidad) {
        super(nombre, apellidos, numero, cedula, direccion);
        this.especialidad = especialidad;
        this.carnet = new Validate().getID(this);
    }

    public Medico(String nombre, String apellidos, String cedula, ArrayList<Telefono> telefono, ArrayList<Direccion> direccion, Especialidad especialidad) {
        super(nombre, apellidos, cedula, telefono, direccion);
        this.carnet = new Validate().getID(this);
        this.especialidad = especialidad;
    }

    public String getCarnet() {
        return carnet;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

}
