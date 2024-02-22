package com.app.gestionemplado.service;

import java.util.Optional;

import com.app.gestionemplado.entity.empleado2;

public interface Empleado2Service {
	public Iterable<empleado2> findAll();

	public Optional<empleado2> findById(Long id);

	public empleado2 save(empleado2 empleado2);

	public void deleteById(Long id);

}
