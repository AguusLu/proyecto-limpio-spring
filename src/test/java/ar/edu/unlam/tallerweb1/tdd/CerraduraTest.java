package ar.edu.unlam.tallerweb1.tdd;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

//1. No escribir codigo productivo sin un test que de rojo antes
//2. Hacer el código más simple posible para pasar a verde
//3. Mejorar el código estando en verde (refactor)

public class CerraduraTest {

    public static final int CODIGO_DE_APERTURA = 3434;

    @Test
    public void cuandoSeCreaLaCajaFuerteDebeEstarAbierta(){
        //preparacion Given(dado)

        //ejecucion When(cuando)
        CajaFuerte caja = whenCreoUnaCajaFuerte();

        //comprobacion Then(entonces)
        thenLaCajaFuerteEstaAbierta(caja);
    }

   /* @Test
    public void alCerrarLaCajaFuerteDebeEstarCerrada(){
        CajaFuerte caja = givenCreoUnaCajaFuerte();

        whenCierroLaCajaFuerte(caja);
        caja.cerrar();

        thenLaCajaFuerteEstaCerrada(caja);
    }*/

    @Test
    public void alIngresarElCodigoCorrectoLaCajaSeAbre(){
        CajaFuerte caja = givenCreoUnaCajaFuerte();

        caja.cerrar(CODIGO_DE_APERTURA);

        whenAbroLaCajaFuerteCon(CODIGO_DE_APERTURA, caja);
        
        thenLaCajaFuerteEstaAbierta(caja);
    }

    private void whenAbroLaCajaFuerteCon(int codigoDeApertura, CajaFuerte caja) {
        caja.abrir(CODIGO_DE_APERTURA);
    }

    @Test
    public void alCerrarConCodigoLaCajaFuerteDebeEstarCerrada(){
        CajaFuerte caja = givenCreoUnaCajaFuerte();

        caja.cerrar(CODIGO_DE_APERTURA);

        thenLaCajaFuerteEstaCerrada(caja);
    }

    private CajaFuerte givenCreoUnaCajaFuerte() {
        return getCajaFuerte();
    }

    private CajaFuerte getCajaFuerte() {
        return crearCaja();
    }

    private void whenCierroLaCajaFuerte(CajaFuerte caja) {
        caja.cerrar();
    }

    private void thenLaCajaFuerteEstaCerrada(CajaFuerte caja) {
        assertThat(caja.estaCerrada()).isTrue();
    }

    private void thenLaCajaFuerteEstaAbierta(CajaFuerte caja) {
        assertThat(caja.estaAbierta()).isTrue();
    }

    private CajaFuerte whenCreoUnaCajaFuerte(){
        return getCajaFuerte();
    }

    private CajaFuerte crearCaja(){
        return new CajaFuerte();
    }
}
