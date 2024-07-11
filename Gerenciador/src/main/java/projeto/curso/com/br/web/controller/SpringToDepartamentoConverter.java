package projeto.curso.com.br.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Departamento;
import service.DepartamentoService;

@Component
public class SpringToDepartamentoConverter implements Converter<String, Departamento> {

	@Autowired
	private DepartamentoService service;
	@Override
	public Departamento convert(String source) {
		if(source.isEmpty()) {
			return null;
			
		}
		Long id = Long.valueOf(source);
		return service.listarPorId(id) ;
	}
	

}
