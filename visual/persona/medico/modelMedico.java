/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visual.persona.medico;

import model.Especialidad;
import model.Medico;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import visual.persona.modelPersona;

/**
 *
 * @author Y2K
 */
public class modelMedico extends modelPersona {

    JComboBox<Especialidad> cmbEspecialidad;

    public modelMedico() {
        super();
        setLayout(new GridLayout(4, 2, paddingX, paddingY));
        cmbEspecialidad = new JComboBox<>(Especialidad.values());
        add(new JLabel("Especialidad:"));
        add(cmbEspecialidad);
    }

    public modelMedico(Medico m) {
        this();
        this.txtNombre.setText(m.getNombre());
        this.txtApellido.setText(m.getApellidos());
        this.txtCedula.setText(m.getCedula());
        this.cmbEspecialidad.setSelectedItem(m.getEspecialidad());
    }
    public JComboBox<Especialidad> getCmbEspecialidad() {
        return cmbEspecialidad;
    }

    public Component[] getPanelDatos(){
        return this.getComponents();
    }
    
}
