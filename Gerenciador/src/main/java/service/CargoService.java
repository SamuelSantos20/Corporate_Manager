package service;

import java.util.List;

import domain.Cargo;

public interface CargoService {

	void Salvar(Cargo cargo);
	
	void Edit(Cargo cargo );
	
	void Excluir(Long id);
	
	Cargo ListarID(Long id);
	
	List<Cargo> Listartudo();

	boolean temFuncionario(Long id);
	
}
