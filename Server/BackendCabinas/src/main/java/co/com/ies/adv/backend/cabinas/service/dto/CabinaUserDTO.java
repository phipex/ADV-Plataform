package co.com.ies.adv.backend.cabinas.service.dto;

import java.io.Serializable;

import co.com.ies.adv.backend.cabinas.service.dto.CabinaDTO;
import co.com.ies.adv.backend.cabinas.service.dto.UserDTO;

/**
 * Cabina que tiene un usuario asociado, se usa para la creacion de las cabinas
 * @author root
 *
 */
public class CabinaUserDTO extends CabinaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	UserDTO userDTO;

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((userDTO == null) ? 0 : userDTO.hashCode());
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
		CabinaUserDTO other = (CabinaUserDTO) obj;
		if (userDTO == null) {
			if (other.userDTO != null)
				return false;
		} else if (!userDTO.equals(other.userDTO))
			return false;
		return true;
	}
	
	
	
}
