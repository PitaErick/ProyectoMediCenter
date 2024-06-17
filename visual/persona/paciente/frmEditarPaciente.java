/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visual.persona.paciente;

import gestora.Gestora;
import model.Direccion;
import model.Paciente;
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
import visual.persona.modelPersona;
import visual.persona.modelTelefonos;

public class frmEditarPaciente {

    static Gestora admG = Gestora.admGestora();
    private Validate v;

    public frmEditarPaciente(JDesktopPane desktopPane, Paciente paciente) {
        v = new Validate();
        JButton btnGuardar = new JButton("Guardar");
        JLabel lblTitle = new JLabel("EDITAR PACIENTE "+paciente.getIdHistorial());
        JInternalFrame internalFrame = new JInternalFrame("Editar Paciente"+paciente.getIdHistorial(), true, true, true, true);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        // Establecer alineación centrada
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        //internalFrame.setLayout(new FlowLayout());
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        modelPersona vp = new modelPersona(paciente);
        modelTelefonos vt = new modelTelefonos(paciente);
        modelDirecciones vd = new modelDirecciones(paciente);
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
                guardarDatos(paciente,vp, vd, vt);
                internalFrame.dispose();
                JOptionPane.showMessageDialog(internalFrame, "Se actualizo exitosamente el paciente con IdHistorial:" + paciente.getIdHistorial());

            }
        });
    }

    private void guardarDatos(Paciente paciente, modelPersona vp, modelDirecciones vd, modelTelefonos vt) {
        String nombre = vp.getTxtNombre().getText().trim(), apellido = vp.getTxtApellido().getText().trim(), cedula = vp.getTxtCedula().getText().trim();
        ArrayList<Telefono> telefonos = v.getListTelefono(vt);
        ArrayList<Direccion> direcciones = v.getListDirecciones(vd);
        admG.editarPaciente(paciente,nombre, apellido, cedula, direcciones, telefonos);
        admG.reportePacientes();
    }

}
