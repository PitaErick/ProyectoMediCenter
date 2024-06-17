package validate;

import model.Direccion;
import model.Medico;
import model.Operadora;
import model.Paciente;
import model.Persona;
import model.Telefono;
import visual.persona.modelDirecciones;
import visual.persona.modelTelefonos;
import java.awt.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Validate {

    public String getID(Persona persona) {
        String cel = "", nom = "", ape = "";
        int num = new Random().nextInt(999) + 100;
        // Comprobamos si la instancia es de la clase Paciente
        if (persona instanceof Paciente) {
            cel = persona.getCedula().substring(0, 2) +persona.getCedula().substring(8);
        }
        // Comprobamos si la instancia es de la clase Medico
        if (Medico.class.isInstance(persona)) {
            cel = persona.getCedula().substring(8);
        }
        // Tomamos los primeros dos caracteres del nombre
        nom = persona.getNombre().substring(0, 2);
        // Tomamos los dos últimos caracteres de los apellidos
        ape = persona.getApellidos().substring(persona.getApellidos().length() - 2);
        // Concatenamos las partes para formar el identificador y lo retornamos
        return cel + nom + ape + num;
    }

    public int inputInt(String msg) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(msg);
            return sc.nextInt();
        } catch (Exception e) {
            return -1;
        }
    }

    public String inputText(String msg) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(msg);
            return sc.nextLine();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validarIngresos(String nombre, String apellido, String cedula, String ciudad, String calle, int numero, String telefono, int indexOperadora, int indexEspecial) {
        if (nombre != null && apellido != null && cedula != null && ciudad != null && calle != null && indexOperadora != -1 && telefono != null && numero != -1 && indexEspecial != -1) {
            if (cedula.length() == 10 && telefono.length() == 10) {
                return true;
            }
        }
        return true;
    }

    public String inputCedOrTel(String msg) {
        Scanner sc = new Scanner(System.in);
        String num;
        try {
            System.out.print(msg);
            num = sc.nextLine();
            Integer.parseInt(num);
            return num;
        } catch (Exception e) {
            return null;
        }
    }

    public LocalDateTime parseDate(String fecha, String hora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime fechaHoraConvertida = LocalDateTime.parse(fecha + " " + hora, formatter);
        return fechaHoraConvertida;
    }

    public ArrayList<Telefono> getListTelefono(modelTelefonos vt) {
        ArrayList<Telefono> arrApoyo = new ArrayList<>();
        for (int i = 1; i <= vt.getPanelDatos().length - 1; i += 4) {
            JComboBox<Operadora> operadora = (JComboBox) vt.getPanelDatos()[i];
            JTextField numero = (JTextField) vt.getPanelDatos()[i + 2];
            arrApoyo.add(new Telefono(numero.getText().trim(), (Operadora) operadora.getSelectedItem()));
        }
        return arrApoyo;
    }

    public ArrayList<Direccion> getListDirecciones(modelDirecciones vd) {
        ArrayList<Direccion> arrApoyo = new ArrayList<>();
        for (int i = 1; i <= vd.getPanelDatos().length - 1; i += 6) {
            JTextField ciudad = (JTextField) vd.getPanelDatos()[i];
            JTextField calle = (JTextField) vd.getPanelDatos()[i + 2];
            JTextField numero = (JTextField) vd.getPanelDatos()[i + 4];
            arrApoyo.add(new Direccion(ciudad.getText().trim(), calle.getText().trim(), aInt(numero)));
        }
        return arrApoyo;
    }

    private int aInt(JTextField numero) {
        try {
            return Integer.parseInt(numero.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean validarEntrada(Component[] vp, Component[] vd, Component[] vt) {
        Component[] result = new Component[vp.length + vd.length + vt.length];
        System.arraycopy(vp, 0, result, 0, vp.length);
        System.arraycopy(vd, 0, result, vp.length, vd.length);
        System.arraycopy(vt, 0, result, vp.length + vd.length, vt.length);
        ArrayList<Boolean> flags = new ArrayList<>();
        String cadenaErrores = "";
        for (Component component : result) {
            if (component instanceof JTextField) {
                if (((JTextField) component).getText().trim().isBlank()) {
                    cadenaErrores += "Debe completar el campo " + component.getName() + "\n";
                }
                flags.add(((JTextField) component).getText().trim().isBlank());
            }
        }
        if (!cadenaErrores.trim().isBlank()) {
            JOptionPane.showMessageDialog(null, cadenaErrores);
        }
        return flags.contains(true);
    }

    public void resetDatos(Component[] vp, Component[] vd, Component[] vt) {
        Component[] result = new Component[vp.length + vd.length + vt.length];
        System.arraycopy(vp, 0, result, 0, vp.length);
        System.arraycopy(vd, 0, result, vp.length, vd.length);
        System.arraycopy(vt, 0, result, vp.length + vd.length, vt.length);
        ArrayList<Boolean> flags = new ArrayList<>();
        String cadenaErrores = "";
        for (Component component : result) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
        if (!cadenaErrores.trim().isBlank()) {
            JOptionPane.showMessageDialog(null, cadenaErrores);
        }
    }

    public boolean validar(String carnet, String idHistorial) {
        if (!carnet.isBlank() && !idHistorial.isBlank()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Complete los campos");
            return false;
        }

    }

    public boolean validar(String trim, String trim0, String trim1, String fecha, String hora) {
        if (!trim.isBlank() && !trim0.isBlank() && !trim1.isBlank() && !fecha.isBlank() && !hora.isBlank()) {
            try {
                parseDate(fecha, hora);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ingrese un formato de fecha correcto."
                        + "\n  Fecha en formato<dd-MM-yyyy>"
                        + "\n  Hora en formato<HH:mm:ss>");
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean validar(String trim) {
        if (!trim.isBlank()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Complete los campos");
            return false;
        }
    }

    public int validarInt(String trim) {
        try {
            return Integer.parseInt(trim);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
