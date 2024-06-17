
import javax.swing.SwingUtilities;
import visual.panelBienvenida;

public class Main {

    public static void main(String[] args) {
        ////SwingUtilities.invokeLater(() -> {
            panelBienvenida welcomeFrame = new panelBienvenida();
            welcomeFrame.setVisible(true);
        ////});
    }
    /*
    static Gestora admG = Gestora.admGestora();
    static Validate v = new Validate();
    public static void main(String[] args) {
        System.out.println(Especialidad.values()[7]);
        int opc1 = 0, opc2 = 0;
        String idMedico = "";
        do {
            opc1 = v.inputInt(menu());

            switch (opc1) {
                case 1:
                    opc2 = v.inputInt("\nMedicos\n1.-Ingreso.\n2.-Editar\nIngrese >");
                    if (opc2 == 1) {
                        admG.ingresoPersona("Medico");
                    } else if (opc2 == 2) {
                        admG.editarMedico(v.inputText("Ingrese el carnet del medico a editar: "));
                    }
                    break;
                case 2:
                    opc2 = v.inputInt("\nPaciente\n 1.-Ingreso.\n2.-Editar\nIngrese >");
                    if (opc2 == 1) {
                        admG.ingresoPersona("Paciente");
                    } else if (opc2 == 2) {
                        admG.editarPaciente(v.inputText("Ingrese el id del paciente a editar: "));
                    }
                    break;
                case 3:
                    idMedico = v.inputText("Confirme su carnet de medico: ");
                    admG.registrarConsulta(idMedico);
                    break;
                case 4:
                    opc2 = v.inputInt("Reportes de:\n1.-Pacientes\n2.-Medicos.\n3.-Consultas\nIngrese >");
                    if(opc2==1){
                        admG.reportePacientes();
                    }else if(opc2==2){
                        admG.reporteMedicos();
                    }else if(opc2==3){
                        admG.reporteConsultas();
                    }
                    break;
            }
        } while (opc1 != 0);

    }

    private static String menu() {
        return "Menu:\n"
                + "1.-Ir a Medico\n"
                + "2.-Ir a Paciente\n"
                + "3.-Registro de Consulta\n"
                + "4.-Reportes\n"
                + "0.-Salir\n"
                + "Ingrese >";
    }
     */
}
