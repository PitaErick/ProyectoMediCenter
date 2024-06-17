package visual.consulta;

import gestora.Gestora;
import model.Medico;
import model.Paciente;
import validate.Validate;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class frmConsulta {

    protected int paddingX = 5;
    protected int paddingY = 5;
    private JTextField txtFecha;
    private JTextField txtHora;
    private Validate v;

    public frmConsulta(JDesktopPane desktopPane, Medico medico, Paciente paciente) {
        v = new Validate();
        JInternalFrame internalFrame = new JInternalFrame("Registrar consulta", true, true, true, true);
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new GridLayout(6, 2, paddingX, paddingY));
        JTextField txtDiagnostico = new JTextField();
        JTextField txtTratamiento = new JTextField();
        JTextField txtConsultorio = new JTextField();
        JLabel lblTitle = new JLabel("Ingreso Consulta del paciente "+ paciente.getNombre());
        JButton btnGuardar = new JButton("Registrar");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        try {
            txtFecha = new JFormattedTextField(new MaskFormatter("##-##-####"));
            txtHora = new JFormattedTextField(new MaskFormatter("##:##:##"));
        } catch (ParseException ex) {
        }

        pnMain.add(new JLabel("Diagnostico:"));
        pnMain.add(txtDiagnostico);
        pnMain.add(new JLabel("Tratamiento:"));
        pnMain.add(txtTratamiento);
        pnMain.add(new JLabel("Consultorio:"));
        pnMain.add(txtConsultorio);
        pnMain.add(new JLabel("Fecha <dd-MM-yyyy>:"));
        pnMain.add(txtFecha);
        pnMain.add(new JLabel("Hora <HH:mm:ss>:"));
        pnMain.add(txtHora);

        panel.add(lblTitle);
        panel.add(pnMain);
        panel.add(btnGuardar);
        //JScrollPane scrollPane = new JScrollPane(panel);

        internalFrame.add(panel);

        //internalFrame.setPreferredSize(new Dimension(350, 500));
        internalFrame.setVisible(true);
        internalFrame.pack();
        //internalFrame.setResizable(false);
        internalFrame.setMaximizable(false);
        desktopPane.add(internalFrame);
        btnGuardar.addActionListener((e) -> {
            if (v.validar(txtDiagnostico.getText().trim(), txtTratamiento.getText().trim(), txtConsultorio.getText().trim(), txtFecha.getText().trim(), txtHora.getText().trim())) {
                Gestora.admGestora().agregarConsulta(medico, paciente, txtDiagnostico.getText().trim(), txtTratamiento.getText().trim(), txtConsultorio.getText().trim(), v.parseDate(txtFecha.getText().trim(), txtHora.getText().trim()));
                internalFrame.dispose();
                JOptionPane.showMessageDialog(null, "Consulta registrada exitosamente.");
            }
        });
    }

}
