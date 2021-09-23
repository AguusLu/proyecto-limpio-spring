package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class ControladorRegistrarme {

    private ServicioLogin servicioLogin;
    @Autowired
    public ControladorRegistrarme(ServicioLogin servicioLogin){
        this.servicioLogin = servicioLogin;
    }

    //@RequestParam es para ?nombre=valr&nombre2=valor2
    //@RequestMapping(method = RequestMethod.GET, path = "/ir-a-registrarme/{nombre}")
    //public ModelAndView irARegistrarme(@PathVariable("nombre")String nombreVariable){
    //http://localhost:8080/proyecto_limpio_spring_war_exploded/ir-a-registrarme/Agus
    //public ModelAndView irARegistrarme(@RequestParam("nombre")String nombreVariable,
      //                                 @RequestParam("apellido")String apellidoVariable){
        //http://localhost:8080/proyecto_limpio_spring_war_exploded/ir-a-registrarme?nombre=Agus&apellido=Gi
    @RequestMapping(method = RequestMethod.GET, path = "/ir-a-registrarme")
    public ModelAndView irARegistrarme(){//@ModelAttribute("datos") DatosRegistro datos){
        ModelMap model = new ModelMap();
        DatosRegistro datos = new DatosRegistro();
        datos.setEmail("ingrese mail...");
        model.put("datos", datos);
       // model.put("datos", new DatosRegistro());
       // model.put("parametro", nombreVariable.toUpperCase());
       // model.put("parametro", nombreVariable.toUpperCase() + apellidoVariable.toUpperCase());
        return new ModelAndView("registro-usuario", model);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/registrarme")
    public ModelAndView registrarUsuario(@ModelAttribute("datos") DatosRegistro datos) {
        ModelMap model = new ModelMap();
        if(!datos.getClave().equals(datos.getRepiteClave())){
            model.put("msg", "Registro fallido por claves incorrectas");
            return new ModelAndView("registro-usuario", model);
        }
        if(esValido(datos.getEmail())) {

            try {
                servicioLogin.registrar(datos.getEmail(), datos.getClave());
            } catch (Exception e) {
                model.put("msg", "El usuario ya existe");
                return new ModelAndView("registro-usuario", model);
            }

            model.put("email", datos.getEmail());
            model.put("msg", "Registro Existoso");

            DatosLogin datosLogin = new DatosLogin();
            datosLogin.setEmail(datos.getEmail());
            model.put("datosLogin", datosLogin);
            return new ModelAndView("redirect:/login", model);
        }
            model.put("msg", "Registro fallido por mail incorrecto");
            return new ModelAndView("registro-usuario", model);
    }

    private boolean esValido(String email){
        return email.endsWith(".com") && email.contains("@");
    }

    /*    public ModelAndView registrarUsuario(DatosRegistro datos) {
        ModelMap model = new ModelMap();
        model.put("email", datos.getEmail());
        model.put("msg", "Registro Existoso");
        // TODO ver el redirect al action login.
        return new ModelAndView("login", model);
    }*/
}
