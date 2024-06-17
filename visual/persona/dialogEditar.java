/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visual.persona;

import visual.persona.paciente.frmEditarPaciente;
import visual.persona.paciente.frmIngresoPaciente;
import visual.persona.medico.frmIngresoMedico;
import visual.persona.medico.frmEditarMedico;
import gestora.Gestora;
import model.Medico;
import model.Paciente;
import validate.Validate;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class dialogEditar {

    private Validate v;

    public dialogEditar(JDesktopPane desktopPane, String rol) {
        v = new Validate();
        JInternalFrame internalFrame = new JInternalFrame("Datos requeridos", true, true, true, true);
        internalFrame.setPreferredSize(new Dimension(300, 100));

        JPanel contentPanel = new JPanel(new GridLayout(3, 2));

        JLabel label1 = new JLabel("ID a editar: ");
        JTextField txtID = new JTextField();

        JButton button = new JButton("Editar");

        contentPanel.add(label1);
        contentPanel.add(txtID);
        contentPanel.add(new JLabel());
        contentPanel.add(button);
        button.addActionListener((ActionEvent e) -> {
            if (v.validar(txtID.getText().trim())) {
                if (rol == "medico") {
                    Medico medico = Gestora.admGestora().existeIdM(txtID.getText().trim());
                    if (medico != null) {
                        internalFrame.dispose();
                        new frmEditarMedico(desktopPane, medico);
                    } else {
                        internalFrame.dispose();
                        JOptionPane.showMessageDialog(null, "Medico no registrado, Ingreselo");
                        new frmIngresoMedico(desktopPane);
                    }
                } else if (rol == "paciente") {
                    Paciente paciente = Gestora.admGestora().existeIdP(txtID.getText().trim());
                    if (paciente != null) {
                        internalFrame.dispose();
                        new frmEditarPaciente(desktopPane, paciente);
                    } else {
                        internalFrame.dispose();
                        JOptionPane.showMessageDialog(null, "Paciente no registrado, Ingreselo");
                        new frmIngresoPaciente(desktopPane);
                    }

                }
            }
        });

        internalFrame.add(contentPanel);
        internalFrame.pack();
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

}
