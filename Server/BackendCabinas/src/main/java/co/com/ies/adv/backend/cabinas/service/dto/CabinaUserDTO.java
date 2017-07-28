package co.com.ies.adv.backend.cabinas.service.dto;

/**
 * Cabina que tiene un usuario asociado, se usa para la creacion de las cabinas
 * @author root
 *
 */
public class CabinaUserDTO extends CabinaDTO {

	UserDTO userDTO;

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	
	
}
