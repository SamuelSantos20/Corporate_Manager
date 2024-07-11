package domain;


import jakarta.persistence.*;

// A anotação @Entity indica que esta classe é uma entidade JPA.
@SuppressWarnings("serial")
@Entity
// @Table especifica a tabela que esta entidade será mapeada.
@Table(name = "ENDERECO")
public class Endereco extends AbstractEntity<Long> {

    // @Column indica que este campo será mapeado para uma coluna na tabela.
    // nullable = false significa que esta coluna não pode ser nula.
    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    // @Enumerated indica que o campo será mapeado para uma coluna de enumeração.
    // EnumType.STRING indica que o valor do enum será salvo como String.
    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private UF uf;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, length = 5)
    private Integer numero;
    
    @Column(nullable =false)
    private String complemento;
    
    // Getters and Setters
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
    
    
}
