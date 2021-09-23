package ar.edu.unlam.tallerweb1.tdd;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginImpl;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//1. No escribir codigo productivo sin un test que de rojo antes
//2. Hacer el código más simple posible para pasar a verde
//3. Mejorar el código estando en verde (refactor)

public class ServicioLoginTest {

    public static final String EMAIL = "agus.luu@gmail.com";
    private RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    private ServicioLogin servicioLogin = new ServicioLoginImpl(repositorioUsuario);

    @Test(expected = Exception.class)
    public void siMeRegistroConUsuarioExistenteDaError() throws Exception{
        givenUsuarioYaExiste(EMAIL);
        whenRegistro(EMAIL);
        thenElUsuarioNoSeGuarda();
    }

    private void thenElUsuarioNoSeGuarda() {
        verify(repositorioUsuario, never()).guardar(any());
    }

    @Test
    public void deberiaRegistrarUsuarioSiNoExiste() throws Exception{
        givenUsuarioNoExiste(EMAIL);
        Usuario creado = whenRegistro(EMAIL);
        thenElRegistroEsExistoso(creado);
    }

    private void givenUsuarioNoExiste(String email) {
        when(repositorioUsuario.buscar(email)).thenReturn(null);
    }

    private Usuario whenRegistro(String email) throws Exception{
        return servicioLogin.registrar(email, "67447");
    }
    private void thenElRegistroEsExistoso(Usuario creado) {
        assertThat(creado).isNotNull();
        assertThat(creado.getEmail()).isEqualTo(EMAIL);
        verify(repositorioUsuario, times(1)).guardar(any());
    }

    private void givenUsuarioYaExiste(String email) {
        when(repositorioUsuario.buscar(email)).thenReturn(new Usuario());
    }


}
