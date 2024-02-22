package com.app.gestionemplado.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.gestionemplado.entity.empleado2;
import com.app.gestionemplado.repository.Empleados2Repository;

@Service
public class Empleado2ServiceImpl implements Empleado2Service {
	@Autowired
	private Empleados2Repository empleadorepository;

	@Override
	public Iterable<empleado2> findAll() {
		// TODO Auto-generated method stub
		return empleadorepository.findAll();
	}

	@Override
	public Optional<empleado2> findById(Long id) {
		// TODO Auto-generated method stub
		return empleadorepository.findById(id);
	}

	@Override
	public empleado2 save(empleado2 empleado2) {
		// TODO Auto-generated method stub
		return empleadorepository.save(empleado2);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		empleadorepository.deleteById(id);
	}

}
