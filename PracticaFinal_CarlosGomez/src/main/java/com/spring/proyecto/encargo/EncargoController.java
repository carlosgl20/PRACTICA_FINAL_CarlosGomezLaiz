package com.spring.proyecto.encargo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proyecto.empleado.EmpleadoDAO;
import com.spring.proyecto.pedido.PedidoDAO;

@Controller
public class EncargoController {

	@Autowired
	EncargoDAO encargoDAO;
	
	@Autowired
	EmpleadoDAO empleadoDAO;
	
	@Autowired
	PedidoDAO pedidoDAO;
	
	@GetMapping("/encargo")
	public ModelAndView encargos() {
			
		ModelAndView respuesta = new ModelAndView();
		respuesta.addObject("encargaciones", encargoDAO.findAll());
		respuesta.setViewName("encargo");
		
		return respuesta;
	}
	
	@GetMapping("/encargo/add")
	public ModelAndView encargoAdd() {
			
		ModelAndView respuesta = new ModelAndView();
		respuesta.addObject("encargo", new Encargo());
		respuesta.addObject("pedidos", pedidoDAO.findAll());
		respuesta.addObject("empleados", empleadoDAO.findAll());
		
		respuesta.setViewName("formEncargo");
		
		return respuesta;
	}
	
	@PostMapping("/encargo/save")
	public ModelAndView formEncargo(@ModelAttribute Encargo encargo) {
	
		
		EncargoKey key = new EncargoKey();
		
		key.setIdPedido(encargo.getPedido().getId());
		key.setIdEmpleado(encargo.getEmpleado().getId());
		
		encargo.setId(key);
		
		encargoDAO.save(encargo);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/pedido/"+encargo.getPedido().getId());	
		
		return model;
	}	
	
	@GetMapping("/encargo/del/{idPedido}/{idEmpleado}")
	public ModelAndView delPlan(@PathVariable long idPedido, @PathVariable long idEmpleado) {
				
		
		EncargoKey key = new EncargoKey();
		
		key.setIdPedido(idPedido);
		key.setIdEmpleado(idEmpleado);
		

		encargoDAO.deleteById(key);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/encargo");
		
		return model;
	}	
}