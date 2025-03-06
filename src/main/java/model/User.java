package model;

import javax.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "user")
public class User implements IModel {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @OneToOne
    @MapsId // Garante que o ID do User seja o mesmo da Pessoa
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Pessoa pessoa;

    @Column(name = "password", nullable = false)
    private String password;

    public User() {
    }

    public User(Pessoa pessoa, String password) {
        this.pessoa = pessoa;
        this.id = pessoa.getId();
        setPassword(password);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.id = pessoa.getId();
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String plainPassword) {
        this.password = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public String getPassword() {
        return password;
    }

    public boolean checkPassword(String plainPassword) {
        return BCrypt.checkpw(plainPassword, password);
    }
}
