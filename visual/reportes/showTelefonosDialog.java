/*
 * Haga clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia.
 * Haga clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar esta plantilla.
 */
package visual.reportes;

import model.Telefono;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Esta clase representa un diálogo para mostrar información de teléfonos.
 */
public class showTelefonosDialog extends JDialog {

    private JPanel panel;

    /**
     * Constructor para crear un diálogo de visualización de teléfonos.
     *
     * @param telefono La lista de objetos Telefono a mostrar.
     * @param desktopPane El JInternalFrame sobre el cual se ubicará el diálogo.
     */
    public showTelefonosDialog(ArrayList<Telefono> telefono, JInternalFrame desktopPane) {
        super();

        setTitle("Teléfono/s");

        panel = new JPanel() {
            {
                int paddingX = 5;
                int paddingY = 5;
                GridLayout grid;
                JPanel jp = new JPanel();
                grid = new GridLayout((telefono.size() * 3), 2, paddingX, paddingY);
                jp.setBorder(new EmptyBorder(20, 20, 20, 20));
                jp.setLayout(grid);
                int numDir = 0;
                for (Telefono dir : telefono) {
                    numDir += 1;
                    JTextField textOperadora = new JTextField();
                    JTextField txtNumero = new JTextField();
                    textOperadora.setText("" + dir.getOperadora());
                    txtNumero.setText(dir.getNumero());
                    jp.add(new JLabel("Teléfono " + numDir));
                    jp.add(new JLabel());
                    jp.add(new JLabel("Operadora:"));
                    jp.add(textOperadora);
                    jp.add(new JLabel("Teléfono:"));
                    jp.add(txtNumero);
                }
                add(jp);
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            }
        };

        setLocationRelativeTo(desktopPane);
        setModal(true);
        getContentPane().add(panel);
        pack();
        setVisible(true);
    }
}
