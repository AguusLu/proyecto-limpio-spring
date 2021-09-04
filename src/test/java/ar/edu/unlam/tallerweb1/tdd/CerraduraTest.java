package ar.edu.unlam.tallerweb1.tdd;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

//1. No escribir codigo productivo sin un test que de rojo antes
//2. Hacer el código más simple posible para pasar a verde
//3. Mejorar el código estando en verde (refactor)

public class CerraduraTest {

    @Test
    public void cuandoSeCreaLaCajaFuerteDebeEstarAbierta(){
        //preparacion Given(dado)

        //ejecucion When(cuando)
        CajaFuerte caja = whenCreoUnaCajaFuerte();

        //comprobacion Then(entonces)
        thenLaCajaFuerteEstaAbierta();
    }

    private void thenLaCajaFuerteEstaAbierta() {
        assertThat(caja.estaAbierta()).isTrue();
    }

    private CajaFuerte whenCreoUnaCajaFuerte(){
        return new CajaFuerte();
    }
}
