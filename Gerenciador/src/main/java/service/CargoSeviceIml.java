package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import dao.CargoDao;
import domain.Cargo;

@Service @Transactional(readOnly = false)
public class CargoSeviceIml implements CargoService {

	@Autowired
	CargoDao cargos;
	@Override
	public void Salvar(Cargo cargo) {
		cargos.save(cargo);

	}

	@Override
	public void Edit(Cargo cargo) {
	    cargos.update(cargo);  

	}

	@Override
	public void Excluir(Long id) {
		cargos.delete(id);

	}

	@Override @Transactional(readOnly = true)
	public Cargo ListarID(Long id) {
		
		return cargos.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Cargo> Listartudo() {
		
		return cargos.findAll();
	}

	@Override
	public boolean temFuncionario(Long id) {
		ModelAndView mv = new ModelAndView();
		
		if(ListarID(id).getFuncionarios().isEmpty()) {
	
		return false;
		
	}
	
	return true;
	}

}
