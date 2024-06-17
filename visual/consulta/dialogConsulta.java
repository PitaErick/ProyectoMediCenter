/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visual.consulta;

import gestora.Gestora;
import model.Medico;
import model.Paciente;
import validate.Validate;
import visual.persona.paciente.frmIngresoPaciente;
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

public class dialogConsulta {

    private Validate v;

    public dialogConsulta(JDesktopPane desktopPane) {
        v= new Validate();
        JInternalFrame internalFrame = new JInternalFrame("Datos requeridos", true, true, true, true);
        internalFrame.setPreferredSize(new Dimension(300, 100));

        JPanel contentPanel = new JPanel(new GridLayout(3, 2));

        JLabel label1 = new JLabel("Carnet Medico:");
        JTextField carnet = new JTextField();

        JLabel label2 = new JLabel("IdHistorial Paciente:");
        JTextField idHistorial = new JTextField();

        JButton button = new JButton("Registrar");

        contentPanel.add(label1);
        contentPanel.add(carnet);
        contentPanel.add(label2);
        contentPanel.add(idHistorial);
        contentPanel.add(new JLabel());
        contentPanel.add(button);
        button.addActionListener((ActionEvent e) -> {
            if(v.validar(carnet.getText().trim(),idHistorial.getText().trim())){
                Medico medico= Gestora.admGestora().existeIdM(carnet.getText().trim());
                if(medico!=null){
                    Paciente paciente = Gestora.admGestora().existeIdP(idHistorial.getText().trim());
                       if(paciente!=null){
                           internalFrame.dispose();
                           new frmConsulta(desktopPane,medico,paciente);
                       }else{
                           internalFrame.dispose();
                           JOptionPane.showMessageDialog(null,"Paciente no registrado, Ingreselo");
                           new frmIngresoPaciente(desktopPane);
                       }
                }else{
                    JOptionPane.showMessageDialog(null,"Medico no encontrado");
                }
            }
        });

        internalFrame.add(contentPanel);
        internalFrame.pack();
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);
    }

}
