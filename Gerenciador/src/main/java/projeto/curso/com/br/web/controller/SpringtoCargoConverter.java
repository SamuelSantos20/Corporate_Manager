package projeto.curso.com.br.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Cargo;
import service.CargoService;

@Component
public class SpringtoCargoConverter implements Converter<String ,Cargo> {

	@Autowired
	CargoService cargoService;
	
	
	@Override
	public Cargo convert(String source) {
	if(source.isEmpty()) {
		return null;
		
	}
	Long id = Long.valueOf(source);
	return cargoService.ListarID(id);
	}

}
