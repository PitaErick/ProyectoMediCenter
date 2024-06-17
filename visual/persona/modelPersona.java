package visual.persona;

import model.Paciente;
import java.awt.Component;
import java.awt.GridLayout;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class modelPersona extends JPanel {

    protected int paddingX = 5;
    protected int paddingY = 5;
    protected JTextField txtNombre;
    protected JTextField txtApellido;
    protected JFormattedTextField txtCedula;

    public modelPersona() {
        setLayout(new GridLayout(3, 2, paddingX, paddingY));
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        try {
            txtCedula = new JFormattedTextField(new MaskFormatter("##########"));
            txtCedula.setColumns(10);
        } catch (ParseException ex) {
        }
        txtNombre.setName("nombre");
        txtApellido.setName("apellido");
        txtCedula.setName("cedula");
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Apellido:"));
        add(txtApellido);
        add(new JLabel("Cedula:"));
        add(txtCedula);

    }

    public modelPersona(Paciente p) {
        this();
        this.txtNombre.setText(p.getNombre());
        this.txtApellido.setText(p.getApellidos());
        this.txtCedula.setText(p.getCedula());
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public JTextField getTxtCedula() {
        return txtCedula;
    }

    public Component[] getPanelDatos() {
        return this.getComponents();
    }

}
