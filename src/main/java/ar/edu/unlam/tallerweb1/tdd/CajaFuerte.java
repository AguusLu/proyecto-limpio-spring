package ar.edu.unlam.tallerweb1.tdd;

public class CajaFuerte {

    private Boolean abierta = true;

    public boolean estaAbierta(){
        return abierta;
    }

    public void cerrar() {
        abierta = false;
    }

    public void cerrar(int codigoDeApertura) {
        abierta = false;
    }

    public boolean estaCerrada() {
        return !estaAbierta();
    }

    public void abrir(int codigoDeApertura) {
        abierta = true;
    }
}
