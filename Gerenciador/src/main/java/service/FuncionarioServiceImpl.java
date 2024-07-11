package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.FuncionarioDao;
import domain.Funcionario;

@Service @Transactional(readOnly = false)
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	FuncionarioDao funcionarios;
	@Override 
	public void Salvar(Funcionario funcionario) {
	
           funcionarios.save(funcionario);
	}

	@Override
	public void Edit(Funcionario fuuncionario) {
		funcionarios.update(fuuncionario);

	}

	@Override
	public void Excluir(Long id) {
	funcionarios.delete(id);

	}

	@Override @Transactional(readOnly = true)
	public Funcionario ListarID(Long id) {
		
		return funcionarios.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Funcionario> ListarTodos() {
	
		return funcionarios.findAll();
	}

	@Override
	public List<Funcionario> buscarPorNome(String nome) {
		
		return funcionarios.findByNome(nome);
	}

	@Override
	public List<Funcionario> BuscarPorCargo(Long id) {
	
		return funcionarios.findByCargo(id);
	}


	@Override
    public List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida) {
	    if (entrada != null && saida != null) {	    	
            return funcionarios.findByDataEntradaDataSaida(entrada, saida);
        } else if (entrada != null) {        	
	        return funcionarios.findByDataEntrada(entrada);
        } else if (saida != null) {        	
	        return funcionarios.findByDataSaida(saida);
        } else {
        	return new ArrayList<>();
        }
    }
	
	

}
