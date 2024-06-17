package visual.persona;

import model.Direccion;
import model.Medico;
import model.Paciente;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

public class modelDirecciones extends JPanel {

    protected int paddingX = 5;
    protected int paddingY = 5;
    private GridLayout grid;
    private JPanel jp;
    private JTextField txtCiudad;
    private JTextField txtCalle;
    private JTextField txtNro;
    private JPanel pn;
    private JButton btnAggOrDel;
    private int initRows;
    private final String agg = "+ Añadir";
    private final String del = "- Eliminar";

    public modelDirecciones() {
        this.initRows = 3;
        iniciarComponentes();
        agregarComponentes();
        finalComponentes();
    }

    public modelDirecciones(Paciente paciente) {
        ArrayList<Direccion> direcciones = paciente.getDireccion();
        this.initRows = (direcciones.size() * 3);
        iniciarComponentes();
        for (Direccion dir : direcciones) {
            agregarComponentes();
            txtCiudad.setText(dir.getCiudad());
            txtCalle.setText(dir.getCalle());
            txtNro.setText("" + dir.getNumero());
        }
        finalComponentes();
        if (initRows != 3) {
            btnAggOrDel.setText(del);
        }
    }

    public modelDirecciones(Medico medico) {
        ArrayList<Direccion> direcciones = medico.getDireccion();
        this.initRows = (direcciones.size() * 3);
        iniciarComponentes();
        for (Direccion dir : direcciones) {
            agregarComponentes();
            txtCiudad.setText(dir.getCiudad());
            txtCalle.setText(dir.getCalle());
            txtNro.setText("" + dir.getNumero());
        }
        finalComponentes();
        if (initRows != 3) {
            btnAggOrDel.setText(del);
        }
    }

    public Component[] getPanelDatos() {
        return jp.getComponents();
    }

    private void agregarComponentes() {
        txtCiudad = new JTextField();
        txtCalle = new JTextField();
        txtNro = new JFormattedTextField(createIntegerFormatter());
        txtCiudad.setName("ciudad");
        txtCalle.setName("calle");
        txtNro.setName("nro");
        jp.add(new JLabel("Ciudad:"));
        jp.add(txtCiudad);
        jp.add(new JLabel("Calle:"));
        jp.add(txtCalle);
        jp.add(new JLabel("Nro:"));
        jp.add(txtNro);
        System.out.println("Exito");
    }

    private void finalComponentes() {
        pn.add(new JLabel());
        pn.add(btnAggOrDel);
        add(jp);
        add(pn);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void iniciarComponentes() {
        pn = new JPanel();
        jp = new JPanel();
        btnAggOrDel = new JButton(agg);
        grid = new GridLayout(initRows, 2, paddingX, paddingY);
        jp.setLayout(grid);
        btnAggOrDel.addActionListener((ActionEvent e) -> {
            eventoClick();
        });
    }

    private void eventoClick() {
        if (btnAggOrDel.getText().trim().equals(agg)) {
            grid.setRows((jp.getComponentCount() / 2) + 3);
            agregarComponentes();
            btnAggOrDel.setText(del);
        } else if (btnAggOrDel.getText().trim().equals(del)) {
            int rowsToRemove = Math.min(3, (jp.getComponentCount() / 2)); // No eliminar más filas de las que existen
            int startIndex = jp.getComponentCount() - 2 * rowsToRemove;
            for (int i = jp.getComponentCount() - 1; i >= startIndex; i--) {
                Component component = jp.getComponent(i);
                jp.remove(component);
            }
            grid.setRows(jp.getComponentCount() / 2);
            btnAggOrDel.setText(agg);
        }
        this.revalidate();
        this.repaint();

    }

    private static NumberFormatter createIntegerFormatter() {
        NumberFormat numberFormat = NumberFormat.getIntegerInstance();
        numberFormat.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(numberFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        return formatter;
    }
}
