package model;

import validate.Validate;
import java.util.ArrayList;

public class Paciente extends Persona {

    private String idHistorial;

    public Paciente(String nombre, String apellidos, Telefono numero, String cedula, Direccion direccion) {
        super(nombre, apellidos, numero, cedula, direccion);
        this.idHistorial = new Validate().getID(this);
    }

    public Paciente(String nombre, String apellidos, String cedula, ArrayList<Telefono> telefono, ArrayList<Direccion> direccion) {
        super(nombre, apellidos, cedula, telefono, direccion);
        this.idHistorial = new Validate().getID(this);
    }

    
    public String getIdHistorial() {
        return idHistorial;
    }

}
