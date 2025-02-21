package model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dono")
public class Dono extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "dono", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Smartphone> smartphones;

    public Dono() {
        super();
    }

    public Dono(Long id, List<Smartphone> smartphones) {
        super();
        this.smartphones = smartphones;
    }

    public List<Smartphone> getSmartphones() {
        return smartphones;
    }

    public void setSmartphones(List<Smartphone> smartphones) {
        this.smartphones = smartphones;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), smartphones);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Dono dono = (Dono) obj;
        return super.equals(obj) && Objects.equals(smartphones, dono.smartphones);
    }

    public void addSmartphone(Smartphone smartphone) {
        this.smartphones.add(smartphone);
    }
}
