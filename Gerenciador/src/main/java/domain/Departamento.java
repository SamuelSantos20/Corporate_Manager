package domain;

import java.util.List;
import jakarta.persistence.*;

// A anotação @Entity indica que esta classe é uma entidade JPA.
@SuppressWarnings("serial")
@Entity
// @Table especifica a tabela que esta entidade será mapeada.
@Table(name = "DEPARTAMENTO")
public class Departamento extends AbstractEntity<Long> {

    // @Column indica que este campo será mapeado para uma coluna na tabela.
    // nullable = false significa que esta coluna não pode ser nula.
    // unique = true significa que o valor desta coluna deve ser único na tabela.
    // length = 200 define o tamanho máximo da coluna.
    @Column(name = "nome", nullable = false, unique = true, length = 200)
    private String nome;

    // @OneToMany indica um relacionamento um-para-muitos com a entidade Cargo.
    // mappedBy = "departamento" indica que o relacionamento é bidirecional e
    // é mapeado pelo atributo "departamento" na entidade Cargo.
    @OneToMany(mappedBy = "departamento")
    private List<Cargo> cargos;

    // Getters and Setters
    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
