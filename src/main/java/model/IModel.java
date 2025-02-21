package model;

import java.io.Serializable;

public interface IModel extends Serializable {
	
	/**
	 * Recupera o Id da entidade
	 * 
	 * @return id Id da entidade
	 */
	Long getId();
	
	/**
	 * Seta o Id da entidade
	 * 
	 * @param id Id da entidade
	 */
	void setId(Long id);

}

