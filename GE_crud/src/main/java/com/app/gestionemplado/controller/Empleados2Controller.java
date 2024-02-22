package com.app.gestionemplado.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

import com.app.gestionemplado.entity.empleado2;
import com.app.gestionemplado.exepciones.ResourceNotFoundException;
import com.app.gestionemplado.service.Empleado2Service;

@RestController
@RequestMapping("/app")
@CrossOrigin("*")


public class Empleados2Controller {
	@Autowired
	private Empleado2Service servicio;

	// este método sirve para guardar el empleado
	@PostMapping("/empleados")
	public empleado2 guardarEmpleado(@RequestBody empleado2 empleado2) {
		return servicio.save(empleado2);
	}
	
	
	@GetMapping("/empleados")

	public List<empleado2> readAll() {
		List<empleado2> empleado2 = StreamSupport.stream(servicio.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return empleado2;
	}
	
	
	@GetMapping("/empleados/{id}")
	public ResponseEntity<empleado2> obtenerEmpleadoPorId(@PathVariable Long id){
		empleado2 empleado2= servicio.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException("No existe el empleado con el id: "+id));
		return ResponseEntity.ok(empleado2);
	}
	
	
	//metodo para actualizar el empleado
	@PutMapping("/empleados/{id}")
	public ResponseEntity<empleado2> actualizarEmpleado(@PathVariable Long id,@RequestBody empleado2 detallesEmpleado2){
		empleado2 empleado2= servicio.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("No existe el empleado con el id "+id));
		
		empleado2.setNombre(detallesEmpleado2.getNombre());
		empleado2.setApellido(detallesEmpleado2.getApellido());	
		empleado2.setEmail(detallesEmpleado2.getEmail());	
		
		empleado2 empleadoActualizado=servicio.save(empleado2);
		

		return ResponseEntity.ok(empleadoActualizado);
		}
	@DeleteMapping("empleados/{id}")
	public ResponseEntity < Map <String,Boolean>>eliminarEmpleado(@PathVariable Long id){
		empleado2 empleado2= servicio.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("No existe el empleado con el id "+id));
		servicio.deleteById(id);
		Map <String, Boolean> response=new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}