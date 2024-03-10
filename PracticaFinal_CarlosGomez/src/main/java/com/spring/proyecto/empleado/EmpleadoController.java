package com.spring.proyecto.empleado;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proyecto.pedido.PedidoDAO;

import jakarta.validation.Valid;

@Controller
public class EmpleadoController {

	@Autowired
	EmpleadoDAO empleadoDAO;

	@Autowired
	PedidoDAO pedidoDAO;

	// Saca todos los empleados
	@GetMapping("/empleados")
	public ModelAndView empleados() {

		ModelAndView model = new ModelAndView();
		model.setViewName("empleados");

		List<Empleado> empleados = (List<Empleado>) empleadoDAO.findAll();
		model.addObject("empleados", empleados);

		return model;
	}

	// Saca el empleado por el id
	@GetMapping("/empleado/{id}")
	public ModelAndView empleado(@PathVariable long id) {

		Empleado empleado = empleadoDAO.findById(id).get();

		ModelAndView model = new ModelAndView();
		model.setViewName("empleado");

		model.addObject("empleado", empleado);

		return model;
	}

	// Añade un empleado junto al rol
	@GetMapping("/empleado/add")
	public ModelAndView addEmpleado() {

		ModelAndView model = new ModelAndView();

		model.setViewName("formEmpleado");

		List<Empleado> empleados = (List<Empleado>) empleadoDAO.findAll();
		model.addObject("empleados", empleados);

		// Para que no me saque roles repetidos
		Set<String> rolesUnicos = new HashSet<>();
		for (Empleado empleado : empleados) {
			rolesUnicos.add(empleado.getRol());
		}

		// Para que no saque horarios repetidos
		Set<String> horariosUnicos = new HashSet<>();
		for (Empleado empleado : empleados) {
			horariosUnicos.add(empleado.getHorario());
		}

		// Para que no saque nombres repetidos
		Set<String> nombresUnicos = new HashSet<>();
		for (Empleado empleado : empleados) {
			nombresUnicos.add(empleado.getNombre());
		}

		model.addObject("rolesUnicos", rolesUnicos);
		model.addObject("horariosUnicos", horariosUnicos);
		model.addObject("nombresUnicos", nombresUnicos);
		model.addObject("empleado", new Empleado());
		return model;
	}

	// Guarda el empleado añadido
	@PostMapping("/empleado/save")
	public ModelAndView formEmpleado(@ModelAttribute @Valid Empleado empleado, BindingResult bindingResult) {

		empleadoDAO.save(empleado);
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/empleados");
		return model;

	}
	
	// Aquí sí me deja borrar los empleados pero solo los que están recién añadidos (aunque tengan id y demas datos como el resto),
	// los que estaban por defecto en la base de datos me da error de restricción y no logré solucionarlo
	// Elimina el empleado por el id
	@GetMapping("/empleado/del/{id}")
	public ModelAndView delEmpleado(@PathVariable long id) {

		empleadoDAO.deleteById(id);

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/empleados");

		return model;
	}

}