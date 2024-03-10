package com.spring.proyecto.pedido;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proyecto.cliente.Cliente;
import com.spring.proyecto.cliente.ClienteDAO;
import com.spring.proyecto.empleado.EmpleadoDAO;

import jakarta.validation.Valid;

@Controller
public class PedidoController {

	@Autowired
	ClienteDAO clienteDAO;

	@Autowired
	EmpleadoDAO empleadoDAO;

	@Autowired
	PedidoDAO pedidoDAO;

	// Saca todos los pedidos
	@GetMapping("/pedidos")
	public ModelAndView pedidos(Authentication authentication) {

		ModelAndView model = new ModelAndView();
		model.setViewName("pedidos");

		// Este codigo de authentication es para cuando me loguee en esta ruta
		// me muestre si soy admin o usuario (es la única forma que encontré,
		// otras que encontré no me funcionaban)
		String mensaje = "";
		if (authentication != null && authentication.isAuthenticated()) {
			if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
				mensaje = "¡Hola, admin!";
			} else {
				mensaje = "¡Hola, usuario!";
			}
		}
		model.addObject("mensaje", mensaje);

		List<Pedido> pedidos = (List<Pedido>) pedidoDAO.findAll();
		model.addObject("pedidos", pedidos);

		return model;
	}

	// Saca el pedido por el id
	@GetMapping("/pedido/{id}")
	public ModelAndView pedido(@PathVariable long id) {

		Pedido pedido = pedidoDAO.findById(id).get();

		ModelAndView model = new ModelAndView();
		model.setViewName("pedido");

		List<Cliente> cliente = (List<Cliente>) clienteDAO.findAll();
		model.addObject("cliente", cliente);

		model.addObject("pedido", pedido);

		return model;
	}

	// Añade un pedido junto al cliente que lo va a comprar
	@GetMapping("/pedido/add")
	public ModelAndView addPedido() {

		ModelAndView model = new ModelAndView();

		model.setViewName("formPedido");

		List<Cliente> clientes = (List<Cliente>) clienteDAO.findAll();
		model.addObject("clientes", clientes);

		List<Pedido> pedidos = (List<Pedido>) pedidoDAO.findAll();
		model.addObject("pedidos", pedidos);

		model.addObject("pedido", new Pedido());

		// Para que no me saque juegos repetidos
		Set<String> juegosUnicos = new HashSet<>();
		for (Pedido pedido : pedidos) {
			juegosUnicos.add(pedido.getNombre());
		}

		// Para que no saque compañias repetidas
		Set<String> compañiasUnicas = new HashSet<>();
		for (Pedido pedido : pedidos) {
			compañiasUnicas.add(pedido.getCompañia());
		}

		model.addObject("juegosUnicos", juegosUnicos);
		model.addObject("compañiasUnicas", compañiasUnicas);
		return model;
	}

	// Solo me deja borrar los datos vacíos, no logro borrar las filas que tienen
	// datos
	// Elimina el pedido por el id
	@GetMapping("/pedido/del/{idPedido}/{idCliente}")
	public ModelAndView delPedido(@PathVariable long idPedido, @PathVariable long idCliente) {

		pedidoDAO.deleteById(idPedido);
		clienteDAO.deleteById(idCliente);

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/pedidos");

		return model;
	}

	// Guarda el pedido añadido
	@PostMapping("/pedido/save")
	public ModelAndView formPedido(@ModelAttribute @Valid Pedido pedido, BindingResult bindingResult) {

		pedidoDAO.save(pedido);

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/pedidos");
		return model;

	}

	// Edita el pedido
	@GetMapping("/pedido/edit/{id}")
	public ModelAndView editPedido(@PathVariable long id) {

		ModelAndView model = new ModelAndView();

		Optional<Pedido> pedidoEdit = pedidoDAO.findById(id);
		if (pedidoEdit.isPresent()) {

			model.setViewName("pedido");

			model.addObject("pedido", pedidoEdit.get());

			List<Cliente> clientes = (List<Cliente>) clienteDAO.findAll();
			model.addObject("cliente", clientes);

		} else
			model.setViewName("redirect:/pedidos");

		return model;
	}

	
	// No logro que funcione, me deja editar el pedido pero cuando confirmo los
	// cambios me los guarda vacíos en la tabla, es decir,
	// me detecta el pedido que quiero actualizar pero los datos me los guarda en
	// null (comprobado desde h2)
	
	// Esta línea la escribo después de que, al añadir la validación del formulario, 
	// ya ni siquiera me añade los datos en null, directamente es como si al insertar 
	// no leyera que hay datos escritos y salta el error de validación 
	// (esto pasa cuando estoy editando el pedido y guardo cambios)
	
	// Debería guardar el pedido editado
	@PostMapping("/pedido/save/edit/{id}")
	public ModelAndView editPedido(@PathVariable("id") long id, @ModelAttribute @Valid Pedido pedido,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// Manejar errores de validación si es necesario
		}

		Optional<Pedido> optionalPedido = pedidoDAO.findById(id);
		if (optionalPedido.isPresent()) {
			Pedido existingPedido = optionalPedido.get();
			existingPedido.setNombre(pedido.getNombre());
			existingPedido.setPrecio(pedido.getPrecio());
			existingPedido.setCompañia(pedido.getCompañia());
			existingPedido.setCliente(pedido.getCliente());
			pedidoDAO.save(existingPedido);
			return new ModelAndView("redirect:/pedidos");

		} else {
			// Manejo del caso en que no se encuentre el pedido
			ModelAndView errorModel = new ModelAndView();
			errorModel.setViewName("error");
			errorModel.addObject("errorMessage", "El pedido no se encontró.");
			return errorModel;
		}
	}

}