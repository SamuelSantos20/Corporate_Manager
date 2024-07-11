package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.DepartamentoDao;
import domain.Departamento;

@Service
@Transactional(readOnly = false)
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoDao departamentoDao;

    @Override
    public void salvar(Departamento departamento) {
        departamentoDao.save(departamento);
    }

    @Override
    public void editar(Departamento departamento) {
        departamentoDao.update(departamento);
    }

    @Override
    public void excluir(Long id) {
        departamentoDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Departamento listarPorId(Long id) {
        return departamentoDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Departamento> listarTodos() {
        return departamentoDao.findAll();
    }

	@Override
	public boolean departamentoTemCargos(Long id) {
		if(listarPorId(id).getCargos().isEmpty()){
			
			return false;
		}
		return true;
	}
}
