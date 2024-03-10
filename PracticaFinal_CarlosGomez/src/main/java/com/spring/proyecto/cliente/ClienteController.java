package com.spring.proyecto.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.proyecto.pedido.PedidoDAO;
import com.spring.proyecto.reseña.ReseñaDAO;

import jakarta.validation.Valid;

@Controller
public class ClienteController {

	@Autowired
	ClienteDAO clienteDAO;
	
	@Autowired
	PedidoDAO pedidoDAO;
	
	@Autowired
	ReseñaDAO reseñaDAO;
	
	//Saca todos los clientes y su información
	@GetMapping("/clientes")
	public ModelAndView clientes() {
	
		ModelAndView model = new ModelAndView();
		model.setViewName("clientes");
		
		List<Cliente> clientes = (List<Cliente>) clienteDAO.findAll();
		model.addObject("clientes", clientes);
		
		return model;
	}
	
	@GetMapping("/cliente/{id}")
	public ModelAndView cliente(@PathVariable long id) {
		
		Cliente cliente = clienteDAO.findById(id).get();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("cliente");
		
		model.addObject("cliente", cliente);
		
		return model;
	}
	
	@PostMapping("/cliente/add")
    public ModelAndView crearCliente(@ModelAttribute Cliente cliente) {
        Cliente nuevoCliente = clienteDAO.save(cliente);
        ModelAndView modelAndView = new ModelAndView("formCliente");
        modelAndView.addObject("cliente", nuevoCliente);
        return modelAndView;
    }
	
//	@GetMapping("/cliente/add")
//	public ModelAndView addCliente() {
//				
//		ModelAndView model = new ModelAndView();
//		model.setViewName("formcliente");
//		model.addObject("cliente", new Cliente());
//		model.addObject("pedidos", pedidoDAO.getPedidosNoEnlazados());
//		model.addObject("reseñas", reseñaDAO.findAll());
//	
//		return model;
//	}	
//	
	
//	//Saca todos los clientes y su información (reseña que ha escrito y videojuego que ha comprado)
//	@GetMapping("/cliente/info")
//	public List<Cliente> getCliente() {
//
//		clienteDAO.findAll();
//
//		return (List<Cliente>) clienteDAO.findAll();
//	}
//	
//	//Saca la persona por el id (junto a sus relaciones, que son videojuego y reseña)
//	@GetMapping("/cliente/{id}")
//	public Optional<Cliente> getClientePorId(@PathVariable Long id) {
//		return this.clienteService.getById(id);
//	}
//	
//	//Saca el videojuego por el id
//	@GetMapping("/videojuego/{id}")
//	public Optional<Pedido> getVideojuegoPorId(@PathVariable Long id) {
//		return this.videojuegoService.getById(id);
//	}
//	
//	//Saca la reseña por el id
//	@GetMapping("/reseña/{id}")
//	public Optional<Reseña> getReseñaPorId(@PathVariable Long id) {
//		return this.reseñaService.getById(id);
//	}
//	
//	//Añade clientes a la BDDD (pudiendo añadirle el nombre, la ciudad, la edad, etc.)
//	@PostMapping("/cliente")
//	public ResponseEntity<Cliente> postCliente(@RequestBody Cliente cliente) {
//
//		List<Pedido> videojuegos = cliente.getVideojuegos();
//		for(Videojuego v : videojuegos) {
//			
//			videojuegoDAO.save(v);
//		}
//		
//		clienteDAO.save(cliente);
//		
//		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
//	}
//
//	//Borra clientes por su id (toda la informacion de ellos, como tablas relacionadas de videojuegos y reseñas)
//	@DeleteMapping("/cliente/{id}")
//	public ResponseEntity<Cliente> deleteCliente(@PathVariable long id) {
//
//		Optional<Cliente> cliente = clienteDAO.findById(id);
//		if (cliente.isPresent()) {
//			clienteDAO.delete(cliente.get());
//			return ResponseEntity.status(HttpStatus.FOUND).body(cliente.get());
//		}
//
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//	}
//	
//	//Actualiza clientes (pudiendo cambiarle cualquier dato)
//	@PutMapping("/cliente/{id}")
//	public ResponseEntity<Cliente> putCliente(@RequestBody Cliente cliente, @PathVariable long id) {
//
//		Optional<Cliente> clienteAlmacenado = clienteDAO.findById(id);
//
//		if (clienteAlmacenado.isPresent()) {
//
//			clienteDAO.delete(clienteAlmacenado.get());
//			clienteDAO.save(cliente);
//
//			return ResponseEntity.status(HttpStatus.ACCEPTED).body(cliente);
//		}
//
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//	}
}