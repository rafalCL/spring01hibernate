package pl.coderslab.spring01hibernate.service.security;


import pl.coderslab.spring01hibernate.entity.security.User;
import pl.coderslab.spring01hibernate.model.security.UserDto;
import pl.coderslab.spring01hibernate.validation.security.EmailExistsException;

public interface IUserService {
	User registerNewUserAccount(UserDto accountDto)
		      throws EmailExistsException;
}