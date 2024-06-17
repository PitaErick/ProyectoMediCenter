/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visual.persona.medico;

import gestora.Gestora;
import model.Direccion;
import model.Especialidad;
import model.Telefono;
import validate.Validate;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import visual.persona.modelDirecciones;
import visual.persona.modelTelefonos;

/**
 *
 * @author Y2K
 */
public class frmIngresoMedico {

    static Gestora admG = Gestora.admGestora();
    private Validate v;

    public frmIngresoMedico(JDesktopPane desktopPane) {
        v = new Validate();
        JButton btnGuardar = new JButton("Guardar");
        JLabel lblTitle = new JLabel("INGRESO DE MEDICO");
        JInternalFrame internalFrame = new JInternalFrame("Registrar medicos ", true, true, true, true);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Establecer alineación centrada
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        //internalFrame.setLayout(new FlowLayout()
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitle);
        modelMedico vm = new modelMedico();
        modelTelefonos vt = new modelTelefonos();
        modelDirecciones vd = new modelDirecciones();
        vm.setAlignmentX(Component.CENTER_ALIGNMENT);
        vt.setAlignmentX(Component.CENTER_ALIGNMENT);
        vd.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(vm);
        panel.add(vd);
        panel.add(vt);

        panel.add(btnGuardar);
        JScrollPane scrollPane = new JScrollPane(panel);

        internalFrame.getContentPane().add(scrollPane);

        internalFrame.setPreferredSize(new Dimension(350, 500));
        internalFrame.setVisible(true);
        internalFrame.pack();
        //internalFrame.setResizable(false);
        internalFrame.setMaximizable(false);
        desktopPane.add(internalFrame);
        btnGuardar.addActionListener((e) -> {
            if (!v.validarEntrada(vm.getPanelDatos(), vd.getPanelDatos(), vt.getPanelDatos())) {
                guardarDatos(vm, vd, vt);
                internalFrame.dispose();
                JOptionPane.showMessageDialog(internalFrame, "Se registro exitosamente el medico con carnet:"+admG.getUltimoCarnet());
                
            }
        });
    }

    private void guardarDatos(modelMedico vm, modelDirecciones vd, modelTelefonos vt) {
        //Extraer datos de Medico
        String nombre = vm.getTxtNombre().getText().trim(), apellido = vm.getTxtApellido().getText().trim(), cedula = vm.getTxtCedula().getText().trim();
        Especialidad especialidad = (Especialidad) vm.getCmbEspecialidad().getSelectedItem();
        ArrayList<Telefono> telefonos = v.getListTelefono(vt);
        ArrayList<Direccion> direcciones = v.getListDirecciones(vd);
        admG.nuevoMedico(nombre, apellido, cedula, especialidad, direcciones, telefonos);
        admG.reporteMedicos();
    }

}
