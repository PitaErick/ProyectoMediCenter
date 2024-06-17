/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visual.reportes;

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

public class dialogReporte {

    private Validate v;

    public dialogReporte(JDesktopPane desktopPane, String tipo) {
        v = new Validate();
        JInternalFrame internalFrame = new JInternalFrame("Numero de datos por pagina", true, true, true, true);
        //internalFrame.setPreferredSize(new Dimension(400, 90));
        JPanel contentPanel = new JPanel(new GridLayout(2, 2));
        JLabel label1 = new JLabel("Numero de datos por pagina: ");
        JTextField txtNum = new JTextField();
        JButton button = new JButton("Mostrar");
        contentPanel.add(label1);
        contentPanel.add(txtNum);
        contentPanel.add(new JLabel());
        contentPanel.add(button);
        button.addActionListener((ActionEvent e) -> {
            int cantDatos = v.validarInt(txtNum.getText().trim());
            if (cantDatos > 0) {
                if (tipo == "medicos") {
                    internalFrame.dispose();
                    new reporteMedico(desktopPane, cantDatos);

                } else if (tipo == "pacientes") {
                    internalFrame.dispose();
                    new reportePacientes(desktopPane, cantDatos);

                } else if (tipo == "consultas") {
                    internalFrame.dispose();
                    new reporteConsultas(desktopPane, cantDatos);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese solo digitos mayor a 0");
            }
        });

        internalFrame.add(contentPanel);
        internalFrame.pack();
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

}
