package service;

import java.time.LocalDate;
import java.util.List;

import domain.Funcionario;

public interface FuncionarioService {

	void Salvar(Funcionario funcionario);
	
	void Edit(Funcionario fuuncionario);
	
	void Excluir (Long id);
	
	Funcionario ListarID(Long id);
	
	List<Funcionario> ListarTodos();
	
	List<Funcionario> buscarPorNome(String nome);

	List<Funcionario> BuscarPorCargo(Long id);

	List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida);
}
