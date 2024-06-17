package visual.reportes;

import gestora.Gestora;
import model.Direccion;
import model.Medico;
import model.Telefono;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class reporteMedico {

    private JTable table;
    private DefaultTableModel tableModel;
    private int currentPage = 1;
    private int itemsPerPage;
    private ArrayList<Medico> records = Gestora.admGestora().getListMedicos();

    public reporteMedico(JDesktopPane desktopPane, int itemsPerPage) {
        this.itemsPerPage=itemsPerPage;
        JInternalFrame internalFrame = new JInternalFrame("Reporte Pacientes");
        internalFrame.setSize(700, 250);
        internalFrame.setClosable(true);
        internalFrame.setResizable(false);
        internalFrame.setLayout(new BorderLayout());

        // Crear el panel que contendrá la tabla
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Crear la tabla y el modelo de tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nro");
        tableModel.addColumn("Carnet");
        tableModel.addColumn("Especialidad");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Apellido");
        tableModel.addColumn("Cedula");
        tableModel.addColumn("Direccion/es");
        tableModel.addColumn("Telefono/s");
        table = new JTable(tableModel);

        // Crear JScrollPane para la tabla y agregarlo al panel
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        internalFrame.add(tablePanel, BorderLayout.CENTER);

        // Crear botones de paginación
        JPanel paginationPanel = new JPanel();
        JButton firstPageButton = new JButton("<<");
        JButton prevPageButton = new JButton("<");
        JButton nextPageButton = new JButton(">");
        JButton lastPageButton = new JButton(">>");

        paginationPanel.add(firstPageButton);
        paginationPanel.add(prevPageButton);
        paginationPanel.add(nextPageButton);
        paginationPanel.add(lastPageButton);

        internalFrame.add(paginationPanel, BorderLayout.SOUTH);

        // Acciones de los botones de paginación
        firstPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPage = 1;
                updateTableData();
            }
        });

        prevPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    updateTableData();
                }
            }
        });

        nextPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int maxPage = (int) Math.ceil((double) records.size() / itemsPerPage);
                if (currentPage < maxPage) {
                    currentPage++;
                    updateTableData();
                }
            }
        });

        lastPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int maxPage = (int) Math.ceil((double) records.size() / itemsPerPage);
                currentPage = maxPage;
                updateTableData();
            }

        });

        // Agregar el InternalFrame al escritorio
        desktopPane.add(internalFrame);

        internalFrame.setVisible(true);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());

                // Verificar si el clic fue en una de las dos últimas columnas
                int columnCount = table.getColumnCount();
                if (col == (columnCount - 2)) {
                    new showDireccionDialog((ArrayList<Direccion>) table.getValueAt(row, col), internalFrame);
                }
                if (col == (columnCount - 1)) {
                    new showTelefonosDialog((ArrayList<Telefono>) table.getValueAt(row, col), internalFrame);
                }
            }
        });
        table.getColumnModel().getColumn(table.getColumnCount() - 2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return super.getTableCellRendererComponent(table, "ver", isSelected, hasFocus, row, column);
            }
        });
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return super.getTableCellRendererComponent(table, "ver", isSelected, hasFocus, row, column);
            }
        });
        updateTableData();
    }

    private void updateTableData() {
        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, records.size());

        // Limpiar la tabla
        tableModel.setRowCount(0);

        // Llenar la tabla con los registros de la página actual
        for (int i = startIndex; i < endIndex; i++) {
            Medico p = records.get(i);
            tableModel.addRow(new Object[]{
                (i + 1),//nro
                p.getCarnet(),//
                p.getEspecialidad(),
                p.getNombre(),
                p.getApellidos(),
                p.getCedula(),
                p.getDireccion(),
                p.getTelefono()
            });
        }
    }

}
