package gestora;

import model.Consulta;
import model.Direccion;
import model.Especialidad;
import model.Medico;
import model.Operadora;
import model.Paciente;
import model.Persona;
import model.Telefono;
import validate.Validate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Gestora {

    private static Gestora gestora;
    private static Validate v;
    private static ArrayList<Paciente> listPacientes;
    private static ArrayList<Medico> listMedicos;
    private static ArrayList<Consulta> listConsulta;
    private static int idConsulta;

    private Gestora() {
        v = new Validate();
        listPacientes = new ArrayList<>();
        listMedicos = new ArrayList<>();
        listConsulta = new ArrayList<>();
        idConsulta = 0;
    }

    public static Gestora admGestora() {
        if (gestora == null) {
            gestora = new Gestora();
        }
        return gestora;
    }

    public void nuevoMedico(String nombre, String apellido, String cedula, Especialidad especialidad, ArrayList<Direccion> direccion, ArrayList<Telefono> telefono) {
        listMedicos.add(new Medico(nombre, apellido, cedula, telefono, direccion, especialidad));
    }

    public void ingresoPersona(String rol) {
        boolean flag = false;
        String nombre, apellido, cedula;
        String ciudad, calle, telefono;
        int numero, indexOperadora, indexEspecial = 0;
        do {
            System.out.println("Ingreso de " + rol);
            nombre = v.inputText("Ingrese su nombre: ");
            apellido = v.inputText("Ingrese su apellido: ");
            cedula = v.inputCedOrTel("Ingrese su cedula: ");
            if (rol == "Medico") {
                System.out.println("---Especialidad---");
                for (Especialidad esp : Especialidad.values()) {
                    System.out.println(esp.ordinal() + ".-" + esp);
                }
                indexEspecial = v.inputInt("Ingrese la especialidad: ");
            }
            ciudad = v.inputText("Ingrese la ciudad: ");
            calle = v.inputText("Ingrese la calle: ");
            numero = v.inputInt("Ingrese el numero: ");
            telefono = v.inputCedOrTel("Ingrese el telefono: ");
            System.out.println("---Operadoras---");
            for (Operadora op : Operadora.values()) {
                System.out.println(op.ordinal() + ".-" + op);
            }
            indexOperadora = v.inputInt("Ingrese la operadora: ");
            if (v.validarIngresos(nombre, apellido, cedula, ciudad, calle, numero, telefono, indexOperadora, indexEspecial)) {
                if (indexOperadora >= 0 && indexOperadora <= Operadora.values().length) {
                    if (rol == "Paciente") {
                        listPacientes.add(new Paciente(nombre, apellido, new Telefono(telefono, Operadora.values()[indexOperadora]), cedula, new Direccion(ciudad, calle, numero)));
                        flag = false;
                    } else if (rol == "Medico") {
                        if (indexEspecial >= 0 && indexEspecial <= Operadora.values().length) {
                            listMedicos.add(new Medico(nombre, apellido, new Telefono(telefono, Operadora.values()[indexOperadora]), cedula, new Direccion(ciudad, calle, numero), Especialidad.values()[indexEspecial]));
                            flag = false;
                        } else {
                            System.out.println("-----Datos erroneos-----");
                        }
                    }
                } else {
                    System.out.println("-----Datos erroneos-----");
                }
            } else {
                System.out.println("-----Datos erroneos-----");
                flag = true;
            }
        } while (flag);
    }

    public void editarMedico(String idMedico) {
        Medico medico = existeIdM(idMedico);
        boolean flag = false;
        String nombre, apellido, cedula;
        String ciudad, calle, telefono;
        int numero, indexOperadora, indexEspecial = 0;
        if (medico != null) {
            do {
                nombre = v.inputText("Ingrese su nombre: ");
                apellido = v.inputText("Ingrese su apellido: ");
                cedula = v.inputCedOrTel("Ingrese su cedula: ");
                System.out.println("---Especialidad---");
                for (Especialidad esp : Especialidad.values()) {
                    System.out.println(esp.ordinal() + ".-" + esp);
                }
                indexEspecial = v.inputInt("Ingrese la especialidad: ");
                ciudad = v.inputText("Ingrese la ciudad: ");
                calle = v.inputText("Ingrese la calle: ");
                numero = v.inputInt("Ingrese el numero: ");
                telefono = v.inputCedOrTel("Ingrese el telefono: ");
                System.out.println("---Operadoras---");
                for (Operadora op : Operadora.values()) {
                    System.out.println(op.ordinal() + ".-" + op);
                }
                indexOperadora = v.inputInt("Ingrese la operadora: ");
                if (v.validarIngresos(nombre, apellido, cedula, ciudad, calle, numero, telefono, indexOperadora, indexEspecial)) {
                    if (indexOperadora >= 0 && indexOperadora <= Operadora.values().length) {
                        if (indexEspecial >= 0 && indexEspecial <= Operadora.values().length) {
                            Medico m = new Medico(nombre, apellido, new Telefono(telefono, Operadora.values()[indexOperadora]), cedula, new Direccion(ciudad, calle, numero), Especialidad.values()[indexEspecial]);
                            listMedicos.set(listMedicos.indexOf(medico), m);
                            flag = false;
                        } else {
                            System.out.println("-----Datos erroneos-----");
                        }
                    } else {
                        System.out.println("-----Datos erroneos-----");
                    }
                } else {
                    System.out.println("-----Datos erroneos-----");
                    flag = true;
                }
            } while (flag);
        } else {
            System.out.println("----Medico no existente----");
        }
    }

    public void editarPaciente(String idPaciente) {
        Paciente paciente = existeIdP(idPaciente);
        boolean flag = false;
        String nombre, apellido, cedula;
        String ciudad, calle, telefono;
        int numero, indexOperadora, indexEspecial = 0;
        if (paciente != null) {
            do {
                nombre = v.inputText("Ingrese su nombre: ");
                apellido = v.inputText("Ingrese su apellido: ");
                cedula = v.inputCedOrTel("Ingrese su cedula: ");
                System.out.println("---Especialidad---");
                ciudad = v.inputText("Ingrese la ciudad: ");
                calle = v.inputText("Ingrese la calle: ");
                numero = v.inputInt("Ingrese el numero: ");
                telefono = v.inputCedOrTel("Ingrese el telefono: ");
                System.out.println("---Operadoras---");
                for (Operadora op : Operadora.values()) {
                    System.out.println(op.ordinal() + ".-" + op);
                }
                indexOperadora = v.inputInt("Ingrese la operadora: ");
                if (v.validarIngresos(nombre, apellido, cedula, ciudad, calle, numero, telefono, indexOperadora, indexEspecial)) {
                    if (indexOperadora >= 0 && indexOperadora <= Operadora.values().length) {
                        Paciente p = new Paciente(nombre, apellido, new Telefono(telefono, Operadora.values()[indexOperadora]), cedula, new Direccion(ciudad, calle, numero));
                        listPacientes.set(listPacientes.indexOf(paciente), p);
                        flag = false;
                    } else {
                        System.out.println("-----Datos erroneos-----");
                    }
                } else {
                    System.out.println("-----Datos erroneos-----");
                    flag = true;
                }
            } while (flag);
        } else {
            System.out.println("----Medico no existente----");
        }
    }

    public void registrarConsulta(String idMedico) {
        idConsulta += 1;
        String diagnostico, receta, consultorio, fecha, hora;
        LocalDateTime fechayhora;
        Medico medico = existeIdM(idMedico);
        if (medico != null) {
            String idHistorial = v.inputText("Ingrese el ID Historial: ");
            Paciente paciente = existeIdP(idHistorial);
            if (paciente != null) {
                diagnostico = v.inputText("Ingrese el diagnostico: ");
                receta = v.inputText("Ingrese el tratamiento: ");
                consultorio = v.inputText("Ingrese el consultorio: ");
                fecha = v.inputText("Ingrese la fecha en formato<dd-MM-yyyy>: ");
                hora = v.inputText("Ingrese la hora en formato<HH:mm:ss>: ");
                fechayhora = v.parseDate(fecha, hora);
                listConsulta.add(new Consulta(paciente, medico, idConsulta, fechayhora, consultorio, diagnostico, receta));
            } else {
                ingresoPersona("Paciente");
            }
        } else {
            System.out.println("----Medico no existente----");
        }

    }

    public void reportePacientes() {
        for (Paciente med : listPacientes) {
            System.out.println("----Paciente " + med.getIdHistorial() + "----");
            System.out.println("Id: " + med.getIdHistorial());
            System.out.println("Nombre: " + med.getNombre());
            System.out.println("Apellido: " + med.getApellidos());
            System.out.println("Cedula: " + med.getCedula());
            System.out.println("--Direccion/es--");
            for (Direccion dir : med.getDireccion()) {
                System.out.println("Direcc:" + dir.getCiudad() + ", " + dir.getCalle() + " Nro." + dir.getNumero());
            }
            System.out.println("--Telefono/s--");
            for (Telefono tel : med.getTelefono()) {
                System.out.println(tel.getOperadora() + ": " + tel.getNumero());
            }
        }
    }

    public void reporteMedicos() {
        for (Medico med : listMedicos) {
            System.out.println("----Medico " + med.getCarnet() + "----");
            System.out.println("Carnet: " + med.getCarnet());
            System.out.println("Especialidad: " + med.getEspecialidad());
            System.out.println("Nombre: " + med.getNombre());
            System.out.println("Apellido: " + med.getApellidos());
            System.out.println("Cedula: " + med.getCedula());
            System.out.println("--Direccion/es--");
            for (Direccion dir : med.getDireccion()) {
                System.out.println("Direcc:" + dir.getCiudad() + ", " + dir.getCalle() + " Nro." + dir.getNumero());
            }
            System.out.println("--Telefono/s--");
            for (Telefono tel : med.getTelefono()) {
                System.out.println(tel.getOperadora() + ": " + tel.getNumero());
            }
        }
    }

    public void reporteConsultas() {
        for (Consulta c : listConsulta) {
            System.out.println("----Consulta Nro." + c.getId() + "----");
            System.out.println("Fecha: " + c.getFechayhora());
            System.out.println("Paciente: " + c.getPaciente().getNombre() + " " + c.getPaciente().getApellidos());
            System.out.println("Medico: " + c.getMedico().getNombre() + " " + c.getMedico().getApellidos());
        }
    }

    public Paciente existeIdP(String idHistorial) {
        for (Paciente p : listPacientes) {
            if (p.getIdHistorial().toLowerCase().equals(idHistorial.toLowerCase())) {
                return p;
            }
        }
        return null;
    }

    public Medico existeIdM(String idMedico) {
        for (Medico m : listMedicos) {
            if (m.getCarnet().toLowerCase().equals(idMedico.toLowerCase())) {
                return m;
            }
        }
        return null;
    }

    public void nuevoPaciente(String nombre, String apellido, String cedula, ArrayList<Direccion> direcciones, ArrayList<Telefono> telefonos) {
        listPacientes.add(new Paciente(nombre, apellido, cedula, telefonos, direcciones));
    }

    public ArrayList<Paciente> getListPaciente() {
        return this.listPacientes;
    }

    public ArrayList<Medico> getListMedicos() {
        return this.listMedicos;
    }

    public String getUltimoCarnet() {
        Medico m = listMedicos.get(listMedicos.size() - 1);
        return m.getCarnet();
    }

    public String getUltimoID() {
        Paciente m = listPacientes.get(listPacientes.size() - 1);
        return m.getIdHistorial();
    }

    public void agregarConsulta(Medico medico, Paciente paciente, String diag, String trat, String consult, LocalDateTime fechayhora) {
        this.idConsulta += 1;
        listConsulta.add(new Consulta(paciente, medico, idConsulta, fechayhora, consult, diag, trat));
    }

    public ArrayList<Consulta> getListConsultas() {
        return this.listConsulta;
    }

    public void editarPaciente(Paciente paciente,String nombre, String apellido, String cedula, ArrayList<Direccion> direcciones, ArrayList<Telefono> telefonos) {
        Paciente p = new Paciente(nombre, apellido, cedula, telefonos, direcciones);
        listPacientes.set(listPacientes.indexOf(paciente), p);
    }

    public void editarMedico(Medico medico, String nombre, String apellido, String cedula, ArrayList<Direccion> direcciones, ArrayList<Telefono> telefonos,Especialidad especialidad) {
        Medico m = new Medico(nombre, apellido, cedula, telefonos, direcciones, especialidad);
        listMedicos.set(listMedicos.indexOf(medico), m);
    }

}
