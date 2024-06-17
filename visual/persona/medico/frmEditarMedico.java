/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visual.persona.medico;

import gestora.Gestora;
import model.Direccion;
import model.Especialidad;
import model.Medico;
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

public class frmEditarMedico {

    static Gestora admG = Gestora.admGestora();
    private Validate v;

    public frmEditarMedico(JDesktopPane desktopPane, Medico medico) {
        v = new Validate();
        JButton btnGuardar = new JButton("Guardar");
        JLabel lblTitle = new JLabel("EDITAR MEDICO "+medico.getCarnet());
        JInternalFrame internalFrame = new JInternalFrame("Editar medico"+medico.getCarnet(), true, true, true, true);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        // Establecer alineación centrada
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        //internalFrame.setLayout(new FlowLayout());
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        modelMedico vp = new modelMedico(medico);
        modelTelefonos vt = new modelTelefonos(medico);
        modelDirecciones vd = new modelDirecciones(medico);
        vp.setAlignmentX(Component.CENTER_ALIGNMENT);
        vt.setAlignmentX(Component.CENTER_ALIGNMENT);
        vd.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitle);
        panel.add(vp);
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
            if (!v.validarEntrada(vp.getPanelDatos(), vd.getPanelDatos(), vt.getPanelDatos())) {
                guardarDatos(medico,vp, vd, vt);
                internalFrame.dispose();
                JOptionPane.showMessageDialog(internalFrame, "Se actualizo exitosamente el medico con carnet:" + medico.getCarnet());
            }
        });
    }

    private void guardarDatos(Medico medico, modelMedico vp, modelDirecciones vd, modelTelefonos vt) {
        String nombre = vp.getTxtNombre().getText().trim(), apellido = vp.getTxtApellido().getText().trim(), cedula = vp.getTxtCedula().getText().trim();
        ArrayList<Telefono> telefonos = v.getListTelefono(vt);
        ArrayList<Direccion> direcciones = v.getListDirecciones(vd);
        admG.editarMedico(medico,nombre, apellido, cedula, direcciones, telefonos, (Especialidad) vp.getCmbEspecialidad().getSelectedItem());
        admG.reporteMedicos();
    }

}
