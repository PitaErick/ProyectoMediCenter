/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visual.reportes;

import model.Direccion;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class showDireccionDialog extends JDialog {

    private JPanel panel;

    public showDireccionDialog(ArrayList<Direccion> obj, JInternalFrame desktopPane) {
        super();

        setTitle("Direccion/es");
        /**/
        panel = new JPanel() {
            {
                int paddingX = 5;
                int paddingY = 5;
                GridLayout grid;
                JPanel jp = new JPanel();
                grid = new GridLayout((obj.size() * 4), 2, paddingX, paddingY);
                jp.setBorder(new EmptyBorder(20, 20, 20, 20));
                jp.setLayout(grid);
                int numDir = 0;
                for (Direccion dir : obj) {
                    numDir += 1;
                    JTextField txtCiudad1 = new JTextField();
                    JTextField txtCalle1 = new JTextField();
                    JFormattedTextField txtNro1 = new JFormattedTextField();
                    txtNro1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getNumberInstance())));
                    txtCiudad1.setText(dir.getCiudad());
                    txtCalle1.setText(dir.getCalle());
                    txtNro1.setText("" + dir.getNumero());
                    jp.add(new JLabel("Direccion " + numDir));
                    jp.add(new JLabel());
                    jp.add(new JLabel("Ciudad:"));
                    jp.add(txtCiudad1);
                    jp.add(new JLabel("Calle:"));
                    jp.add(txtCalle1);
                    jp.add(new JLabel("Nro:"));
                    jp.add(txtNro1);
                }
                add(jp);
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            }
        };
        /**/

        setLocationRelativeTo(desktopPane);
        setModal(true);
        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

}
