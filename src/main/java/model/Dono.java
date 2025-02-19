package model;

import java.util.List;
import java.util.Objects;

public class Dono extends Pessoa {
	private Long id;
	private List<Smartphone> smartphones;

	public Dono(Long id, List<Smartphone> smartphones) {
		super();
		this.id = id;
		this.smartphones = smartphones;
	}

	public Dono() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Smartphone> getSmartphones() {
		return smartphones;
	}

	public void setSmartphones(List<Smartphone> smartphones) {
		this.smartphones = smartphones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dono other = (Dono) obj;
		return Objects.equals(id, other.id);
	}

	public void addSmartphone(Smartphone smartphone) {
		this.smartphones.add(smartphone);
	}
}
