package visual.persona;

import model.Medico;
import model.Operadora;
import model.Paciente;
import model.Telefono;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

/**
 * Esta clase representa un panel para gestionar información de teléfonos.
 */
public class modelTelefonos extends JPanel {

    protected int paddingX = 5;
    protected int paddingY = 5;
    private JFormattedTextField txtTel = null;
    private JComboBox<Operadora> cmbOperadora;
    private GridLayout grid;
    private JPanel jp;
    private JPanel pn;
    private JButton btnAgg;
    private JButton btnDel;
    private int initRows;

    /**
     * Constructor predeterminado para crear un panel de teléfonos.
     */
    public modelTelefonos() {
        this.initRows = 2;
        iniciarComponentes();
        agregarComponentes();
        finalComponentes();

    }

    /**
     * Constructor para crear un panel de teléfonos con información de un
     * paciente.
     *
     * @param paciente El paciente del cual se obtendrán los teléfonos.
     */
    public modelTelefonos(Paciente paciente) {
        ArrayList<Telefono> telefonos = paciente.getTelefono();
        this.initRows = (telefonos.size() * 2);
        iniciarComponentes();
        for (Telefono tel : telefonos) {
            agregarComponentes();
            cmbOperadora.setSelectedItem(tel.getOperadora());
            txtTel.setText(tel.getNumero());
        }
        finalComponentes();

    }
    
    /**
     * Constructor para crear un panel de teléfonos con información de un
     * paciente.
     *
     * @param medico El medico del cual se obtendrán los teléfonos.
     */
    public modelTelefonos(Medico medico) {
        ArrayList<Telefono> telefonos = medico.getTelefono();
        this.initRows = (telefonos.size() * 2);
        iniciarComponentes();
        for (Telefono tel : telefonos) {
            agregarComponentes();
            cmbOperadora.setSelectedItem(tel.getOperadora());
            txtTel.setText(tel.getNumero());
        }
        finalComponentes();

    }

    /**
     * Obtiene los componentes del panel de datos.
     *
     * @return Un array de componentes que forman el panel de datos.
     */
    public Component[] getPanelDatos() {
        return jp.getComponents();
    }

    /**
     * Agrega componentes al panel de teléfonos.
     */
    private void agregarComponentes() {
        try {
            txtTel = new JFormattedTextField(new MaskFormatter("##########"));
            txtTel.setColumns(10);
        } catch (ParseException ex) {
        }
        cmbOperadora = new JComboBox<>(Operadora.values());
        txtTel.setName("telefono");
        jp.add(new JLabel("Operadora:"));
        jp.add(cmbOperadora);
        jp.add(new JLabel("Telefono:"));
        jp.add(txtTel);
    }

    /**
     * Acción realizada cuando se hace clic en el botón de agregar.
     */
    private void btnAggAccionCLick() {
        grid.setRows((jp.getComponentCount() / 2) + 2);
        agregarComponentes();
        this.revalidate();
        this.repaint();
        System.out.println("exito");
    }

    /**
     * Inicializa los componentes y atributos de la clase.
     */
    private void iniciarComponentes() {
        pn = new JPanel();
        jp = new JPanel();
        grid = new GridLayout(initRows, 2, paddingX, paddingY);
        jp.setLayout(grid);
        btnAgg = new JButton("+ Añadir");
        btnDel = new JButton("- Eliminar");
        btnAgg.addActionListener((ActionEvent e) -> {
            btnAggAccionCLick();
            checkBtnDel();
        });
        btnDel.addActionListener((ActionEvent e) -> {
            btnDelAccionCLick();
            checkBtnDel();
        });

    }

    /**
     * Finaliza la configuración de componentes y la disposición del panel.
     */
    private void finalComponentes() {
        pn.add(new JLabel());
        pn.add(btnDel);
        pn.add(btnAgg);
        add(jp);
        add(pn);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        checkBtnDel();
    }

    /**
     * Acción realizada cuando se hace clic en el botón de eliminar.
     */
    private void btnDelAccionCLick() {
        int totalRows = jp.getComponentCount() / 2; // Número de filas
        int rowsToRemove = Math.min(2, totalRows); // No eliminar más filas de las que existen
        int startIndex = jp.getComponentCount() - 2 * rowsToRemove;
        for (int i = jp.getComponentCount() - 1; i >= startIndex; i--) {
            Component component = jp.getComponent(i);
            jp.remove(component);
        }
        grid.setRows(jp.getComponentCount() / 2);
        this.revalidate();
        this.repaint();
    }

    private void checkBtnDel() {
        if ((jp.getComponentCount() / 2) == 2) {
            btnDel.setVisible(false);
        }else{
            btnDel.setVisible(true);
        }
    }

}
