package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class ControladorRegistrarme {

    public ModelAndView registrarUsuario(DatosRegistro datos) {
        ModelMap model = new ModelMap();
        if(!datos.getClave().equals(datos.getRepiteClave())){
            model.put("msg", "Registro fallido por claves incorrectas");
            return new ModelAndView("registrarme", model);
        }
        if(datos.getEmail().endsWith(".com") && datos.getEmail().contains("@")) {
            model.put("email", datos.getEmail());
            model.put("msg", "Registro Existoso");
            return new ModelAndView("login", model);
        }
            model.put("msg", "Registro fallido por mail incorrecto");
            return new ModelAndView("registrarme", model);

       /* if(datos.getEmail().endsWith(".com") && datos.getEmail().contains("@")){
            if(!datos.getClave().equals("123") && !datos.getRepiteClave().equals("123")) {
                model.put("msg", "Registro fallido por claves incorrectas");
                return new ModelAndView("registrarme", model);
            }
            model.put("email", datos.getEmail());
            model.put("clave", datos.getClave());
            model.put("repite clave", datos.getRepiteClave());
            model.put("msg", "Registro Existoso");
            return new ModelAndView("login", model);
        }else {
            model.put("msg", "Registro fallido por mail incorrecto");
            return new ModelAndView("registrarme", model);
        }*/
    }

    /*    public ModelAndView registrarUsuario(DatosRegistro datos) {
        ModelMap model = new ModelMap();
        model.put("email", datos.getEmail());
        model.put("msg", "Registro Existoso");
        // TODO ver el redirect al action login.
        return new ModelAndView("login", model);
    }*/
}
