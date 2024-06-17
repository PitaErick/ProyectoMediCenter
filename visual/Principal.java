package visual;

import gestora.Gestora;
import visual.consulta.dialogConsulta;
import visual.persona.dialogEditar;
import visual.persona.medico.frmIngresoMedico;
import visual.persona.paciente.frmIngresoPaciente;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import visual.reportes.dialogReporte;

public class Principal extends JFrame {

    private JDesktopPane desktopPane;

    public Principal() {
        setTitle("MediCenter - Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        desktopPane = new JDesktopPane() {
            @Override
            protected void paintChildren(Graphics g) {
                Image IMG = new ImageIcon(getClass().getResource("/Visual/imgFondo.png")).getImage().getScaledInstance(50, 50, 0);
                g.drawImage(IMG, 0, 0, getWidth(), getHeight(), this);
                super.paintChildren(g);
            }

        };

        add(desktopPane);
        crearMenu();
        setLocationRelativeTo(null);
    }

    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuMedicos = new JMenu("Medicos");
        JMenuItem registrarMedico = new JMenuItem("Registrar Medico");
        JMenuItem editarMedico = new JMenuItem("Editar Medico");
        registrarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new frmIngresoMedico(desktopPane);
            }
        });
        editarMedico.addActionListener((ActionEvent e) -> {
            new dialogEditar(desktopPane, "medico");
        });

        JMenu menuPacientes = new JMenu("Pacientes");
        JMenuItem registrarPaciente = new JMenuItem("Registrar Paciente");
        JMenuItem editarPaciente = new JMenuItem("Editar Paciente");
        registrarPaciente.addActionListener((ActionEvent e) -> {
            new frmIngresoPaciente(desktopPane);
        });
        editarPaciente.addActionListener((ActionEvent e) -> {
            new dialogEditar(desktopPane, "paciente");
        });

        JMenu menuConsulta = new JMenu("Consulta");
        JMenuItem registrarConsulta = new JMenuItem("Registrar Consulta");
        registrarConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new dialogConsulta(desktopPane);
            }
        });

        JMenu menuReporte = new JMenu("Reportes");
        JMenuItem reportMedico = new JMenuItem("De medicos");
        JMenuItem reportPaciente = new JMenuItem("De pacientes");
        JMenuItem reportConsulta = new JMenuItem("De consultas");

        reportPaciente.addActionListener((ActionEvent e) -> {
            if (Gestora.admGestora().getListPaciente().size() > 0) {
                new dialogReporte(desktopPane, "pacientes");
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese Datos Primero");
            }
        });

        reportMedico.addActionListener((ActionEvent e) -> {
            if (Gestora.admGestora().getListMedicos().size() > 0) {
                new dialogReporte(desktopPane, "medicos");
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese Datos Primero");
            }
        });
        reportConsulta.addActionListener((ActionEvent e) -> {
            if (Gestora.admGestora().getListConsultas().size() > 0) {
                new dialogReporte(desktopPane, "consultas");
                //new reporteConsultas(desktopPane);
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese Datos Primero");
            }
        });

        menuMedicos.add(registrarMedico);
        menuMedicos.add(editarMedico);

        menuPacientes.add(registrarPaciente);
        menuPacientes.add(editarPaciente);

        menuConsulta.add(registrarConsulta);

        menuReporte.add(reportMedico);
        menuReporte.add(reportPaciente);
        menuReporte.add(reportConsulta);

        menuBar.add(menuMedicos);
        menuBar.add(menuPacientes);
        menuBar.add(menuConsulta);
        menuBar.add(menuReporte);
        setJMenuBar(menuBar);
    }

    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Principal mainFrame = new Principal();
            mainFrame.setVisible(true);
        });
    }
     */
}
