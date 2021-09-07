package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;

public class ControladorRegistrarmeTest {

    private ControladorRegistrarme controladorRegistrarme = new ControladorRegistrarme();
    public static final DatosRegistro USUARIO_CON_MAIL_INCORRECTO
            = new DatosRegistro("aguuu@gmail.com", "123", "123");
    public static final DatosRegistro USUARIO_CON_CLAVE_INCORRECTA
             = new DatosRegistro("aguuu@gmail.com", "123", "12345");
    private static final DatosRegistro USUARIO
            = new DatosRegistro("agus.luu@gmail.com", "123", "123");

    @Test
    public void puedoRegistrarmeConUsuarioNuevoYClaveCorrecta(){
        givenQueElUsuarioNoExiste(USUARIO);
        ModelAndView mav = whenRegistroElUsuario(USUARIO);
        thenElRegistroEsExitoso(mav);
    }

    @Test
    public void noPuedoRegistrarmeConMailDeUsuarioIncorrecto() {
        ModelAndView mav = whenRegistroElUsuario(USUARIO_CON_MAIL_INCORRECTO);
        thenElRegistroFallaPorEmail(mav);
    }

    private void thenElRegistroFallaPorEmail(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("registrarme");
        assertThat(mav.getModel().get("msg")).isEqualTo("Registro fallido por mail incorrecto");
        assertThat(mav.getModel().get("email")).isEqualTo(USUARIO.getEmail());
    }

    @Test
    public void noPuedoRegistrarmeConClavesQueNoCoinciden() {
        ModelAndView mav = whenRegistroElUsuarioClaveDistinta(USUARIO_CON_CLAVE_INCORRECTA);
        thenElRegistroFallaPorClave(mav);
    }

    private ModelAndView whenRegistroElUsuarioClaveDistinta(DatosRegistro usuarioConClaveIncorrecta) {
        return controladorRegistrarme.registrarUsuario(usuarioConClaveIncorrecta);
    }

    private void thenElRegistroFallaPorClave(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("registrarme");
        assertThat(mav.getModel().get("msg")).isEqualTo("Registro fallido por claves incorrectas");
        //assertThat(mav.getModel().get("clave")).isEqualTo(USUARIO_CON_CLAVE_INCORRECTA.getClave());
       // assertThat(mav.getModel().get("repite clave")).isEqualTo(USUARIO_CON_CLAVE_INCORRECTA.getRepiteClave());

    }

    private void givenQueElUsuarioNoExiste(DatosRegistro usuario) {

    }

    private ModelAndView whenRegistroElUsuario(DatosRegistro datos){
        return controladorRegistrarme.registrarUsuario(datos);//String usuario, String clave, String repiteClave);
    }

    private void thenElRegistroEsExitoso(ModelAndView mav){
        assertThat(mav.getViewName()).isEqualTo("login");
        assertThat(mav.getModel().get("msg")).isEqualTo("Registro exitoso");
        assertThat(mav.getModel().get("email")).isEqualTo(USUARIO.getEmail());
    }
}
