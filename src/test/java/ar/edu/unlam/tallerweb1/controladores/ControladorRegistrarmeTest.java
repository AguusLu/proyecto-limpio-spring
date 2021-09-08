package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;

public class ControladorRegistrarmeTest {

    private ControladorRegistrarme controladorRegistrarme = new ControladorRegistrarme();
    public static final DatosRegistro USUARIO_CON_MAIL_INCORRECTO
            = new DatosRegistro("agus.com", "123", "123");
    public static final DatosRegistro USUARIO_CON_CLAVE_INCORRECTA
             = new DatosRegistro("agus.luu@gmail.com", "1243", "12345");
    public static final DatosRegistro USUARIO
            = new DatosRegistro("agus.luu@gmail.com", "123", "123");

    @Test
    public void puedoRegistrarmeConUsuarioNuevoYClaveCorrecta(){
        givenQueElUsuarioNoExiste(USUARIO);
        ModelAndView mav = whenRegistroElUsuario(USUARIO);
        thenElRegistroEsExitoso(mav);
    }

    @Test
    public void noPuedoRegistrarmeConClavesQueNoCoinciden() {
        ModelAndView mav = whenRegistroElUsuario(USUARIO_CON_CLAVE_INCORRECTA);
        thenElRegistroFallaPorClave(mav);
    }

    @Test
    public void noPuedoRegistrarmeConMailDeUsuarioIncorrecto() {
        ModelAndView mav = whenRegistroElUsuarioEmail(USUARIO_CON_MAIL_INCORRECTO);
        thenElRegistroFallaPorEmail(mav);
    }

    private void thenElRegistroFallaPorEmail(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("registrarme");
        assertThat(mav.getModel().get("msg")).isEqualTo("Registro fallido por mail incorrecto");
        //assertThat(mav.getModel().get("email")).isEqualTo(USUARIO.getEmail());
     }

    private void thenElRegistroFallaPorClave(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("registrarme");
        assertThat(mav.getModel().get("msg")).isEqualTo("Registro fallido por claves incorrectas");
        //assertThat(mav.getModel().get("clave")).isEqualTo(USUARIO.getClave());
        //assertThat(mav.getModel().get("repiteClave")).isEqualTo(USUARIO.getRepiteClave());
    }

    private void givenQueElUsuarioNoExiste(DatosRegistro usuario) {

    }

    private ModelAndView whenRegistroElUsuarioEmail(DatosRegistro datos){
        return controladorRegistrarme.registrarUsuario(datos);//String usuario, String clave, String repiteClave);
    }

    private ModelAndView whenRegistroElUsuario(DatosRegistro datos){
        return controladorRegistrarme.registrarUsuario(datos);//String usuario, String clave, String repiteClave);
    }

    private void thenElRegistroEsExitoso(ModelAndView mav){
        assertThat(mav.getViewName()).isEqualTo("login");
        assertThat(mav.getModel().get("msg")).isEqualTo("Registro Existoso");
        assertThat(mav.getModel().get("email")).isEqualTo(USUARIO.getEmail());
    }
}
