package model;

import java.util.ArrayList;

public abstract class Persona {

    protected String nombre;
    protected String apellidos;
    protected String cedula;
    protected ArrayList<Telefono> telefono;
    protected ArrayList<Direccion> direccion;

    
    public Persona(String nombre, String apellidos, Telefono numero, String cedula, Direccion direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.telefono = new ArrayList<>();
        if (numero != null) {
            this.telefono.add(numero);
        }
        this.direccion = new ArrayList<>();
        if (direccion != null) {
            this.direccion.add(direccion);
        }
    }

    public Persona(String nombre, String apellidos, String cedula, ArrayList<Telefono> telefono, ArrayList<Direccion> direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    

    public void setTelefono(Telefono telefono) {
        if (this.telefono.size() > 1) {
            this.telefono.add(telefono);
        }
    }

    public void setDireccion(Direccion direccion) {
        if (this.direccion.size() >= 1 && this.direccion.size() < 2) {
            this.direccion.add(direccion);
        } else {
            System.out.println("Superado el limite de direcciones");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public ArrayList<Telefono> getTelefono() {
        return telefono;
    }

    public ArrayList<Direccion> getDireccion() {
        return direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

}
