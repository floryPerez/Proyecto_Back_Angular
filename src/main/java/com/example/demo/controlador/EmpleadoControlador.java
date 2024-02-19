package com.example.demo.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exepciones.ResourceNotFoundException;
import com.example.demo.modelo.Empleado;
import com.example.demo.repositorio.EmpleadoRepositorio;

@RestController
@RequestMapping("/api/v1/")

@CrossOrigin(origins = "http://localhost:4200")

public class EmpleadoControlador {
	@Autowired
	//cmdprivate EmpleadoService service;
	private EmpleadoRepositorio repositorio;

	// este metodo sirve para listar todos los empleados
	@GetMapping("/empleados")
	public List<Empleado> ListarTodosLosEmpleador() {
		return repositorio.findAll();
	}

	// este método sirve para guardar el empleado
	@PostMapping("/empleados")
	public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
		return repositorio.save(empleado);
	}
	
	//Este método sirve para buscar un empleado
	//por id
	@GetMapping("/empleados/{id}")
	public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id){
	Empleado empleado= repositorio.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException("No existe el empleado con el id: "+id));
		return ResponseEntity.ok(empleado);
	}
	
	
	//metodo para actualizar el empleado
	@PutMapping("/empleados/{id}")
	public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id,@RequestBody Empleado detallesEmpleado){
		Empleado empleado= repositorio.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("No existe el empleado con el id "+id));
		
		empleado.setNombre(detallesEmpleado.getNombre());
		empleado.setApellido(detallesEmpleado.getApellido());	
		empleado.setEmail(detallesEmpleado.getEmail());	
		
		Empleado empleadoActualizado=repositorio.save(empleado);
		

		return ResponseEntity.ok(empleadoActualizado);
		}
	@DeleteMapping("empleados/{id}")
	public ResponseEntity < Map <String,Boolean>>eliminarEmpleado(@PathVariable Long id){
		Empleado empleado= repositorio.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("No existe el empleado con el id "+id));
		repositorio.delete(empleado);
		Map <String, Boolean> response=new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
