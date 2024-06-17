package model;

public class Telefono {

    private String numero;
    private Operadora operadora;

    public Telefono(String numero, Operadora operadora) {
        this.numero = numero;
        this.operadora = operadora;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Operadora getOperadora() {
        return operadora;
    }

    public void setOperadora(Operadora operadora) {
        this.operadora = operadora;
    }

    @Override
    public String toString() {
        return "Telefono{" + "numero=" + numero + ", operadora=" + operadora + '}';
    }
    

    
}
